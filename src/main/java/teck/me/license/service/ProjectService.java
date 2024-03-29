package teck.me.license.service;


import teck.me.license.model.dto.CreateProjectDto;
import teck.me.license.model.dto.ListProjectDto;
import teck.me.license.model.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ListProjectDto> getAllProjects(int page, int number);

    ProjectDto getProjectById(String name);

    CreateProjectDto updateProject(String name, CreateProjectDto updatedProjectDto);

    void deleteProject(String name);

    CreateProjectDto createProject(CreateProjectDto projectDto);

}
