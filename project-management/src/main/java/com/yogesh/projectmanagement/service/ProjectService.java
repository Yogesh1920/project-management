package com.yogesh.projectmanagement.service;

import com.yogesh.projectmanagement.model.Project;
import com.yogesh.projectmanagement.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProjectService {

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    private  final ProjectRepository projectRepository;
    public String addProject(Project project) {
        log.info("Adding project: {}", project);
        projectRepository.save(project);
        log.info("Project created Successfully");
        return "Project created Successfully";
    }

    public List<Project> getAllProjects() {
        log.info("Getting all projects");
        List<Project> projects = projectRepository.findAll();
        log.info("Found {} projects", projects.size());
        return projects;
    }

    public Project getProjectById(Integer id) {
        log.info("Getting project by ID: {}", id);
        Optional<Project> existProject = projectRepository.findById(id);
        Project project = existProject.orElse(null);
        if (project != null) {
            log.info("Found project: {}", project);
        } else {
            log.info("Project with ID {} not found", id);
        }
        return project;
    }

    public String deleteProjectById(Integer id) {
        log.info("Deleting project with ID: {}", id);
        projectRepository.deleteById(id);
        log.info("Project Deleted Successfully");
        return "Project Deleted Successfully";
    }

    public Project updateProjectById(Integer id, Project project) {
        log.info("Updating project with ID: {}", id);
        Optional<Project> existingProjectOptional = projectRepository.findById(id);

        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();

            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            existingProject.setStartDate(project.getStartDate());
            existingProject.setEndDate(project.getEndDate());

            Project updatedProject = projectRepository.save(existingProject);
            log.info("Updated project: {}", updatedProject);
            return updatedProject;
        } else {
            log.info("Project with ID {} not found", id);
            return null;
        }
    }
}

