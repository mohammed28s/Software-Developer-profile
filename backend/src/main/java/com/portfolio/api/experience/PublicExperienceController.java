package com.portfolio.api.experience;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/experience")
public class PublicExperienceController {

    private final ExperienceService experienceService;

    public PublicExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public List<Experience> getExperience() {
        return experienceService.findAll();
    }
}
