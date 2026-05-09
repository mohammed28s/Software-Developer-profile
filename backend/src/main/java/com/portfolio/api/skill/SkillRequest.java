package com.portfolio.api.skill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SkillRequest(
        @NotBlank @Size(max = 100) String skillName,
        @Size(max = 100) String category,
        Integer proficiencyLevel) {
}
