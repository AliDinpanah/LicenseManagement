package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.model.CryptoKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teck.me.license.model.Project;
import teck.me.license.model.dto.ListProjectDto;
import teck.me.license.model.dto.ProjectDto;
import teck.me.license.repository.ProjectRepository;
import teck.me.license.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = new Project();
        if (projectRepository.existsByName(projectDto.getName())) {
            //check unique name
            throw new RuntimeException();
        }
        project.setDescription(projectDto.getDescription());
        project.setLicenses(projectDto.getLicenses());
        project.setName(projectDto.getName());
        project.setCryptoKeys(projectDto.getCryptoKeys());
        project.setParameters(projectDto.getParameters());
        projectRepository.save(project);

        return projectDto;
    }

    public List<ListProjectDto> getAllProjects(int page, int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<Project> projectPage = projectRepository.findAll(pageable);
        List<ListProjectDto> projectDtos = new ArrayList<>();

        for (Project project : projectPage.getContent()) {
            projectDtos.add(new ListProjectDto(project.getName(), project.getDescription(), project.getParameters()));
        }

        return projectDtos;
    }


    public ListProjectDto getProjectById(long id) {
        Project project = projectRepository.findById(id).get();
        return new ListProjectDto(project.getName(), project.getDescription(), project.getParameters());
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

        if (projectRepository.existsByName(updatedProjectDto.getName())) {
            //check unique name
            throw new RuntimeException();
        }

        existingProject.setParameters(updatedProjectDto.getParameters());
        existingProject.setParameters(updatedProjectDto.getParameters());
        existingProject.setCryptoKeys(updatedProjectDto.getCryptoKeys());
        existingProject.setName(updatedProjectDto.getName());

        return projectRepository.save(existingProject);

    }

    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    //use in other services
    public CryptoKey findKey(String uuid) {
        return projectRepository.findByCryptoKeys_Uuid(uuid).get();
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).get();
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

}
