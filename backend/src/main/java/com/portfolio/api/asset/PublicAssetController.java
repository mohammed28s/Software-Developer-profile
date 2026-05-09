package com.portfolio.api.asset;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assets")
public class PublicAssetController {

    private final ProfileAssetService profileAssetService;

    public PublicAssetController(ProfileAssetService profileAssetService) {
        this.profileAssetService = profileAssetService;
    }

    @GetMapping("/cv")
    public AssetResponse getCvMetadata() {
        return profileAssetService.getAsset(AssetType.CV);
    }

    @GetMapping("/profile-image")
    public AssetResponse getProfileImageMetadata() {
        return profileAssetService.getAsset(AssetType.PROFILE_IMAGE);
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getAssetFile(@PathVariable String filename) {
        Resource resource = profileAssetService.loadFile(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
