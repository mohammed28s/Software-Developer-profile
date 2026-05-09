package com.portfolio.api.experience;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.api.config.ResourceNotFoundException;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return experienceRepository.findAllByOrderByStartDateDescIdDesc();
    }

    @Transactional
    public Experience create(ExperienceRequest request) {
        Experience experience = new Experience();
        apply(request, experience);
        return experienceRepository.save(experience);
    }

    @Transactional
    public Experience update(Long id, ExperienceRequest request) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found: " + id));
        apply(request, experience);
        return experience;
    }

    @Transactional
    public void delete(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Experience not found: " + id);
        }
        experienceRepository.deleteById(id);
    }

    private void apply(ExperienceRequest request, Experience experience) {
        if (request.startDate() != null && request.endDate() != null && request.endDate().isBefore(request.startDate())) {
            throw new IllegalArgumentException("endDate must be after startDate");
        }
        experience.setCompanyName(request.companyName());
        experience.setJobTitle(request.jobTitle());
        experience.setStartDate(request.startDate());
        experience.setEndDate(request.endDate());
        experience.setAchievements(request.achievements());
        experience.setCurrentJob(request.currentJob() != null && request.currentJob());
    }
}
