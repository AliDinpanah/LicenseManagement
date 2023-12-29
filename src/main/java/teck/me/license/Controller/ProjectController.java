package teck.me.license.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.me.license.model.Project;
import teck.me.license.model.dto.ListProjectDto;
import teck.me.license.model.dto.ProjectDto;
import teck.me.license.service.imp.ProjectServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectServiceImp projectService;

    @Autowired
    public ProjectController(ProjectServiceImp projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) throws IllegalAccessException {
        ProjectDto projectDto1 = projectService.createProject(projectDto);
        return new ResponseEntity<>(projectDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListProjectDto>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int number) {
        List<ListProjectDto> projects = projectService.getAllProjects(page, number);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable String name) throws IllegalAccessException {
        ProjectDto project = projectService.getProjectById(name);
            return new ResponseEntity<>(project, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String name, @RequestBody ProjectDto updatedProject) throws IllegalAccessException {
            Project project = projectService.updateProject(name, updatedProject);
            return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable String name) {
        projectService.deleteProject(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

