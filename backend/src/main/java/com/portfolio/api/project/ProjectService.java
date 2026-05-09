package com.portfolio.api.project;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.api.config.ResourceNotFoundException;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepository.findAllByOrderByCreatedAtDescIdDesc();
    }

    @Transactional
    public Project create(ProjectRequest request) {
        Project project = new Project();
        apply(request, project);
        return projectRepository.save(project);
    }

    @Transactional
    public Project update(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found: " + id));
        apply(request, project);
        return project;
    }

    @Transactional
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found: " + id);
        }
        projectRepository.deleteById(id);
    }

    private void apply(ProjectRequest request, Project project) {
        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setTechStack(request.techStack());
        project.setGithubUrl(request.githubUrl());
        project.setDemoUrl(request.demoUrl());
        project.setImageUrl(request.imageUrl());
    }
}
