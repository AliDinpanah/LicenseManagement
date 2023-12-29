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
import teck.me.license.model.License;
import teck.me.license.model.Project;
import teck.me.license.model.dto.CryptoKeyDto;
import teck.me.license.model.dto.LicenseDto;
import teck.me.license.model.dto.ListProjectDto;
import teck.me.license.model.dto.ProjectDto;
import teck.me.license.repository.ProjectRepository;
import teck.me.license.service.ProjectService;
import teck.me.license.utill.Converter;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    private Converter<CryptoKey, CryptoKeyDto> convertCryptoKeyToDto;
    private Converter<CryptoKeyDto, CryptoKey> convertDtoToCryptoKey;
    private Converter<License, LicenseDto> convertLicenseToDto;
    private Converter<LicenseDto, License> convertDtoToLicense;

    public ProjectDto createProject(ProjectDto projectDto) throws IllegalAccessException {
        Project project = new Project();
        if (projectRepository.existsByName(projectDto.getName())) {
            //check unique name
            throw new ConflictException("Name already exist");
        }
        project.setDescription(projectDto.getDescription());
        project.setParameters(projectDto.getParameters());
        project.setCryptoKeys(convertDtoToCryptoKey.convertList(projectDto.getCryptoKeys(),new CryptoKey()));
        project.setName(projectDto.getName());
        project.setLicenses(convertDtoToLicense.convertList(projectDto.getLicenses(),new License()));
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


    public ProjectDto getProjectById(String name) throws IllegalAccessException {
        if (projectRepository.existsByName(name)) {
            Project project = projectRepository.findByName(name).get();
            return new ProjectDto(project.getName(),project.getDescription(),convertLicenseToDto.convertList(project.getLicenses(),new LicenseDto()),convertCryptoKeyToDto.convertList(project.getCryptoKeys(),new CryptoKeyDto()),project.getParameters());
        }
        throw new NotFoundException("No Project with this id");
    }

    public Project updateProject(String name, ProjectDto updatedProjectDto) throws IllegalAccessException {

        if (projectRepository.existsByName(name)) {
            Project existingProject = projectRepository.findByName(name).get();

            if (projectRepository.existsByName(updatedProjectDto.getName())) {
                //check unique name
                throw new ConflictException("Name already exist");
            }

            existingProject.setDescription(updatedProjectDto.getDescription());
            existingProject.setParameters(updatedProjectDto.getParameters());
            existingProject.setCryptoKeys(convertDtoToCryptoKey.convertList(updatedProjectDto.getCryptoKeys(),new CryptoKey()));
            existingProject.setName(updatedProjectDto.getName());
            existingProject.setLicenses(convertDtoToLicense.convertList(updatedProjectDto.getLicenses(),new License()));

            return projectRepository.save(existingProject);
        }
        throw new NotFoundException("No Project with this id");
    }

    public void deleteProject(String name) {
        projectRepository.deleteByName(name);
    }

    //use in other services
    public CryptoKey findKey(String uuid) {
        return projectRepository.findByCryptoKeys_Uuid(uuid).get();
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).get();
    }




}
