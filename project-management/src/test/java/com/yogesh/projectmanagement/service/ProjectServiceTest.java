package com.yogesh.projectmanagement.service;

import com.yogesh.projectmanagement.model.Project;
import com.yogesh.projectmanagement.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

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

        when(projectRepository.save(project)).thenReturn(project);

        String message = projectService.addProject(project);

        assertEquals("Project created Successfully", message);
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project();
        project.setId(1);
        project.setName("Test Project");

        when(projectRepository.findById(1)).thenReturn(Optional.of(project));

        Project retrievedProject = projectService.getProjectById(1);

        assertEquals(project, retrievedProject);
    }

    @Test
    public void testGetProjectById_ProjectNotFound() {
        when(projectRepository.findById(2)).thenReturn(Optional.empty());

        Project retrievedProject = projectService.getProjectById(2);

        assertNull(retrievedProject);
    }

    @Test
    public void testGetAllProjects() {
        List<Project> mockProjects = Arrays.asList(new Project(), new Project());
        when(projectRepository.findAll()).thenReturn(mockProjects);

        List<Project> projects = projectService.getAllProjects();

        assertEquals(2, projects.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteProjectById() {
        Integer id = 1;
        doNothing().when(projectRepository).deleteById(id);

        String message = projectService.deleteProjectById(id);

        assertEquals("Project Deleted Successfully", message);
        verify(projectRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateProjectById_ProjectFound() {
        Integer id = 1;

        Project existingProject = new Project();
        existingProject.setId(id);
        existingProject.setName("Existing Project");
        existingProject.setDescription("Existing Description");
        existingProject.setStartDate("2024-03-01");
        existingProject.setEndDate("2024-03-15");

        Project updatedProject = new Project();
        updatedProject.setId(id);
        updatedProject.setName("Updated Project");
        updatedProject.setDescription("Updated Description");
        updatedProject.setStartDate("2024-03-10");
        updatedProject.setEndDate("2024-03-25");

        Optional<Project> existingProjectOptional = Optional.of(existingProject);
        when(projectRepository.findById(id)).thenReturn(existingProjectOptional);
        when(projectRepository.save(existingProject)).thenReturn(updatedProject);

        Project returnedProject = projectService.updateProjectById(id, updatedProject);

        assertEquals(updatedProject.getName(), returnedProject.getName());

        verify(projectRepository, times(1)).findById(id);
        verify(projectRepository, times(1)).save(existingProject);
    }

    @Test
    public void testUpdateProjectById_ProjectNotFound() {
        Integer id = 2;

        when(projectRepository.findById(id)).thenReturn(Optional.empty());

        Project updatedProject = new Project();
        updatedProject.setId(id);
        updatedProject.setName("Updated Project");
        updatedProject.setDescription("Updated Description");
        updatedProject.setStartDate("2024-03-10");
        updatedProject.setEndDate("2024-03-25");

        Project returnedProject = projectService.updateProjectById(id, updatedProject);

        assertNull(returnedProject);

        verify(projectRepository, times(1)).findById(id);
    }


}
