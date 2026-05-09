package com.portfolio.api.experience;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ExperienceRequest(
        @NotBlank @Size(max = 255) String companyName,
        @NotBlank @Size(max = 100) String jobTitle,
        LocalDate startDate,
        LocalDate endDate,
        String achievements,
        Boolean currentJob) {
}
