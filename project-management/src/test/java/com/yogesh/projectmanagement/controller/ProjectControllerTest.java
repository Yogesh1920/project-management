package com.yogesh.projectmanagement.controller;

import com.yogesh.projectmanagement.model.Project;
import com.yogesh.projectmanagement.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ProjectControllerTest {
    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProject() {
        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setStartDate("2024-03-10");
        project.setEndDate("2024-03-20");

        when(projectService.addProject(project)).thenReturn("Project created Successfully");

        ResponseEntity<String> response = projectController.addProject(project);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Project created Successfully", response.getBody());
    }

    @Test
    public void testReadAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());

        when(projectService.getAllProjects()).thenReturn(projects);

        ResponseEntity<List<Project>> response = projectController.readAllProjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testReadProjectById_ProjectFound() {
        Integer id = 1;

        Project project = new Project();
        project.setId(id);
        project.setName("Test Project");

        when(projectService.getProjectById(id)).thenReturn(project);

        ResponseEntity<Project> response = projectController.readProjectById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());

        verify(projectService, times(1)).getProjectById(id);
    }

    @Test
    public void testReadProjectById_ProjectNotFound() {
        Integer id = 2;

        when(projectService.getProjectById(id)).thenReturn(null);

        ResponseEntity<Project> response = projectController.readProjectById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(projectService, times(1)).getProjectById(id);
    }



    @Test
    public void testUpdateProjectById() {
        Integer projectId = 1;
        Project project = new Project();
        project.setId(projectId);
        project.setName("Updated Project Name");
        project.setDescription("Updated Description");
        project.setStartDate("2024-03-10");
        project.setEndDate("2024-03-20");

        when(projectService.updateProjectById(eq(projectId), any(Project.class))).thenReturn(project);

        ResponseEntity<Project> response = projectController.updateProjectById(projectId, project);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());
        verify(projectService, times(1)).updateProjectById(eq(projectId), any(Project.class));
    }

    @Test
    public void testDeleteProjectById_ProjectFound() {
        Integer id = 1;

        when(projectService.deleteProjectById(id)).thenReturn("Project Deleted Successfully");

        ResponseEntity<String> response = projectController.deleteProjectById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Project Deleted Successfully", response.getBody());

        verify(projectService, times(1)).deleteProjectById(id);
    }

    @Test
    public void testDeleteProjectById_ProjectNotFound() {
        Integer id = 2;

        when(projectService.deleteProjectById(id)).thenReturn(null);

        ResponseEntity<String> response = projectController.deleteProjectById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(projectService, times(1)).deleteProjectById(id);
    }

}
