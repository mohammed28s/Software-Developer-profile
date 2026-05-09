package com.portfolio.api.asset;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class AdminAssetController {

    private final ProfileAssetService profileAssetService;

    public AdminAssetController(ProfileAssetService profileAssetService) {
        this.profileAssetService = profileAssetService;
    }

    @PostMapping("/upload-cv")
    public AssetResponse uploadCv(@RequestParam("file") MultipartFile file) {
        return profileAssetService.uploadCv(file);
    }

    @PutMapping("/profile-image")
    public AssetResponse uploadProfileImage(@RequestParam("file") MultipartFile file) {
        return profileAssetService.uploadProfileImage(file);
    }
}
