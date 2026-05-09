package com.portfolio.api.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequest(
        @NotBlank @Size(max = 255) String title,
        String description,
        @Size(max = 500) String techStack,
        @Size(max = 255) String githubUrl,
        @Size(max = 255) String demoUrl,
        @Size(max = 255) String imageUrl) {
}
