package com.yogesh.projectmanagement.controller;

import com.yogesh.projectmanagement.model.Project;
import com.yogesh.projectmanagement.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addProject(@RequestBody Project project) {
        String message = projectService.addProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/readAllProjects")
    public ResponseEntity<List<Project>> readAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Project> readProjectById(@PathVariable("id") Integer id) {
        Optional<Project> projectOptional = Optional.ofNullable(projectService.getProjectById(id));
        if (projectOptional.isPresent()) {
            return ResponseEntity.ok(projectOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable("id") Integer id) {
        String message = projectService.deleteProjectById(id);
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable("id") Integer id, @RequestBody Project project) {
        Project updatedProject = projectService.updateProjectById(id, project);
        return ResponseEntity.ok(updatedProject);
    }
}
