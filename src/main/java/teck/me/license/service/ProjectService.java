package teck.me.license.service;

import teck.me.license.model.Project;
import teck.me.license.model.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAllProjects();

    ProjectDto getProjectById(long id);

    ProjectDto saveProject(ProjectDto projectDto);

    Project updateProject(long id, ProjectDto updatedProjectDto);

    void deleteProject(long id);

}
