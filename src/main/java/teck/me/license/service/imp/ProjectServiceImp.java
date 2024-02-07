package teck.me.license.service.imp;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.exception.ConflictException;
import teck.me.license.exception.NotFoundException;
import teck.me.license.model.CryptoKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teck.me.license.model.Project;
import teck.me.license.model.dto.*;
import teck.me.license.repository.ProjectRepository;
import teck.me.license.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CryptoKeyServiceImp cryptoKeyServiceImp;

    public ProjectServiceImp(ProjectRepository projectRepository, CryptoKeyServiceImp cryptoKeyServiceImp) {
        this.projectRepository = projectRepository;
        this.cryptoKeyServiceImp = cryptoKeyServiceImp;
    }

    public CreateProjectDto createProject(CreateProjectDto projectDto) {
        Project project = new Project();
        if (projectRepository.existsByName(projectDto.getName())) {
            //check unique name
            throw new ConflictException("Name already exist");
        }
        project.setDescription(projectDto.getDescription());
        project.setParameters(projectDto.getParameters());
        project.setName(projectDto.getName());
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


    public ProjectDto getProjectById(String name) {
        if (projectRepository.findByName(name).isPresent()) {
            Project project = projectRepository.findByName(name).get();
            List<String> cryptoKeysId = new ArrayList<>();
            List<String> licensesId = new ArrayList<>();
            for (int i = 0; i < project.getLicenses().size(); i++) {
                licensesId.add(project.getLicenses().get(i).getUuid());
            }
            for (int i = 0; i < project.getCryptoKeys().size(); i++) {
                cryptoKeysId.add(project.getCryptoKeys().get(i).getUuid());
            }
            return new ProjectDto(project.getName(), project.getDescription(), licensesId, cryptoKeysId, project.getParameters());
        }
        throw new NotFoundException("No Project with this id");
    }

    public CreateProjectDto updateProject(String name, CreateProjectDto updatedProjectDto) {

        if (projectRepository.findByName(name).isPresent()) {
            Project existingProject = projectRepository.findByName(name).get();

            if (projectRepository.existsByName(updatedProjectDto.getName())) {
                //check unique name
                throw new ConflictException("Name already exist");
            }

            existingProject.setDescription(updatedProjectDto.getDescription());
            existingProject.setParameters(updatedProjectDto.getParameters());
            List<CryptoKey> cryptoKeys = new ArrayList<>();
            for (int i = 0; i < updatedProjectDto.getCryptoKeysId().size(); i++) {
                cryptoKeys.add(cryptoKeyServiceImp.getCryptoKey(updatedProjectDto.getCryptoKeysId().get(i)));
            }
            existingProject.setCryptoKeys(cryptoKeys);
            existingProject.setName(updatedProjectDto.getName());

            projectRepository.save(existingProject);
            return updatedProjectDto;
        }
        throw new NotFoundException("No Project with this id");
    }

    public void deleteProject(String name) {
        projectRepository.deleteByName(name);
    }

    //use in other services
    public CryptoKey findKey(String uuid) {
        if (projectRepository.findByCryptoKeys_Uuid(uuid).isPresent()) {
            return projectRepository.findByCryptoKeys_Uuid(uuid).get();
        }
        throw new NotFoundException("No Project with this key");
    }

    public Project getProject(String name) {
        if (projectRepository.findByName(name).isPresent()) {
            return projectRepository.findByName(name).get();
        }
        throw new NotFoundException("No Project with this name");
    }


}
