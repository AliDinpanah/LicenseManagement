package teck.me.license.service;

import teck.me.license.model.Project;
import teck.me.license.model.dto.ListProjectDto;
import teck.me.license.model.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ListProjectDto> getAllProjects(int page, int number);

    ListProjectDto getProjectById(long id);

    ProjectDto saveProject(ProjectDto projectDto);

    Project updateProject(long id, ProjectDto updatedProjectDto);

    void deleteProject(long id);

    ProjectDto createProject(ProjectDto projectDto);

}
