package teck.me.license.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.me.license.model.Project;
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

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable long id) {
        ProjectDto project = projectService.getProjectById(id);
        if (project != null) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProjectDto> saveProject(@RequestBody ProjectDto projectDto) {
        ProjectDto savedProject = projectService.saveProject(projectDto);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable long id, @RequestBody ProjectDto updatedProject) {
        try {
            Project project = projectService.updateProject(id, updatedProject);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
