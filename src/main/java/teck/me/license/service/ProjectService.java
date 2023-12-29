package teck.me.license.service;


import teck.me.license.model.Project;
import teck.me.license.model.dto.ListProjectDto;
import teck.me.license.model.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ListProjectDto> getAllProjects(int page, int number);

    ProjectDto getProjectById(String name) throws IllegalAccessException;

    Project updateProject(String name, ProjectDto updatedProjectDto) throws IllegalAccessException;

    void deleteProject(String name);

    ProjectDto createProject(ProjectDto projectDto) throws IllegalAccessException;

}
