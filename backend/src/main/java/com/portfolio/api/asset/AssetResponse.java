package com.portfolio.api.asset;

public record AssetResponse(
        String assetType,
        String fileUrl,
        String originalFilename,
        String contentType) {
}
