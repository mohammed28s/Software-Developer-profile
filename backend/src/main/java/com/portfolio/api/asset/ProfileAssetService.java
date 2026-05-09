package com.portfolio.api.asset;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.api.config.ResourceNotFoundException;

@Service
public class ProfileAssetService {

    private final Path uploadRoot;

    public ProfileAssetService(@Value("${portfolio.storage.upload-dir}") String uploadDir) {
        this.uploadRoot = Path.of(uploadDir).toAbsolutePath().normalize();
    }

    public AssetResponse uploadCv(MultipartFile file) {
        validateCv(file);
        return save(AssetType.CV, file);
    }

    public AssetResponse uploadProfileImage(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.toLowerCase(Locale.ROOT).startsWith("image/")) {
            throw new IllegalArgumentException("Profile image must be an image file");
        }
        return save(AssetType.PROFILE_IMAGE, file);
    }

    public AssetResponse getAsset(AssetType assetType) {
        Path path = findLatest(assetType)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found: " + assetType.name()));
        return new AssetResponse(assetType.name(), "/api/assets/" + path.getFileName(), path.getFileName().toString(), null);
    }

    public Resource loadFile(String filename) {
        try {
            Path file = uploadRoot.resolve(filename).normalize();
            if (!file.startsWith(uploadRoot)) {
                throw new IllegalArgumentException("Invalid file path");
            }
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new ResourceNotFoundException("File not found: " + filename);
            }
            return resource;
        } catch (IOException exception) {
            throw new ResourceNotFoundException("File not found: " + filename);
        }
    }

    private AssetResponse save(AssetType assetType, MultipartFile file) {
        try {
            Files.createDirectories(uploadRoot);
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename() == null
                    ? assetType.name().toLowerCase(Locale.ROOT)
                    : file.getOriginalFilename());
            String storedFilename = assetType.name().toLowerCase(Locale.ROOT)
                    + "-"
                    + UUID.randomUUID()
                    + extension(originalFilename);
            Path destination = uploadRoot.resolve(storedFilename).normalize();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            }

            return new AssetResponse(
                    assetType.name(),
                    "/api/assets/" + storedFilename,
                    originalFilename,
                    file.getContentType());
        } catch (IOException exception) {
            throw new IllegalArgumentException("Could not store uploaded file");
        }
    }

    private Optional<Path> findLatest(AssetType assetType) {
        if (!Files.isDirectory(uploadRoot)) {
            return Optional.empty();
        }
        String prefix = assetType.name().toLowerCase(Locale.ROOT) + "-";
        try (var files = Files.list(uploadRoot)) {
            return files
                    .filter(path -> path.getFileName().toString().startsWith(prefix))
                    .max(Comparator.comparing(path -> path.toFile().lastModified()));
        } catch (IOException exception) {
            return Optional.empty();
        }
    }

    private void validateCv(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        if (!"application/pdf".equalsIgnoreCase(file.getContentType())) {
            throw new IllegalArgumentException("CV must be a PDF file");
        }
    }

    private String extension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index < 0) {
            return "";
        }
        return filename.substring(index).toLowerCase(Locale.ROOT);
    }
}
