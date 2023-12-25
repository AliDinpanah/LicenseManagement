package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.exception.ConflictException;
import teck.me.license.exception.DataLogicException;
import teck.me.license.exception.NotFoundException;
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
        if (projectDto.getDescription().length() > 255 || projectDto.getName().length() > 48) {
            throw new DataLogicException("Not match");
        }
        if (!projectDto.getName().matches("^[a-zA-Z][a-zA-Z0-9_\\-\\.]*$\n")) {
            throw new DataLogicException("Not match");
        }
        Project project = new Project();
        if (projectRepository.existsByName(projectDto.getName())) {
            //check unique name
            throw new ConflictException("Name already exist");
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
        if (projectRepository.existsById(id)) {
            Project project = projectRepository.findById(id).get();
            return new ListProjectDto(project.getName(), project.getDescription(), project.getParameters());
        }
        throw new NotFoundException("No Project with this id");
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

        if (updatedProjectDto.getDescription().length() > 255 || updatedProjectDto.getName().length() > 48) {
            throw new DataLogicException("Not match");
        }
        if (!updatedProjectDto.getName().matches("^[a-zA-Z][a-zA-Z0-9_\\-\\.]*$\n")) {
            throw new DataLogicException("Not match");
        }
        if (projectRepository.existsById(id)) {
            Project existingProject = projectRepository.findById(id).get();

            if (projectRepository.existsByName(updatedProjectDto.getName())) {
                //check unique name
                throw new ConflictException("Name already exist");
            }

            existingProject.setParameters(updatedProjectDto.getParameters());
            existingProject.setParameters(updatedProjectDto.getParameters());
            existingProject.setCryptoKeys(updatedProjectDto.getCryptoKeys());
            existingProject.setName(updatedProjectDto.getName());

            return projectRepository.save(existingProject);
        }
        throw new NotFoundException("No Project with this id");
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

}
