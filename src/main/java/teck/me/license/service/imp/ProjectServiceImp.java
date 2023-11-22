package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teck.me.license.model.Project;
import teck.me.license.model.dto.ProjectDto;
import teck.me.license.repository.ProjectRepository;
import teck.me.license.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project:projects){
            projectDtos.add(new ProjectDto(project.getName(),project.getDescription(),project.getLicenses(),project.getCryptoKeys()));
        }
        return projectDtos;
    }

    public ProjectDto getProjectById(long id) {
        Project project = projectRepository.findById(id).get();
        return new ProjectDto(project.getName(),project.getDescription(),project.getLicenses(),project.getCryptoKeys());
    }

    public ProjectDto saveProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setCryptoKeys(projectDto.getCryptoKeys());
        project.setLicenses(projectDto.getLicenses());
        project.setName(project.getName());
        project.setDescription(projectDto.getDescription());
        projectRepository.save(project);
        return projectDto;
    }

    public Project updateProject(long id, ProjectDto updatedProjectDto) {
        Project existingProject = projectRepository.findById(id).get();
        if (existingProject != null) {
            // Update fields as needed

            return projectRepository.save(existingProject);
        }
        return null; // Handle not found scenario
    }

    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

}
