package teck.me.license.model.dto;

import teck.me.license.model.License;
import teck.me.license.model.Project;

import java.util.List;

public class CryptoKeyDto {

    private String description;

    private Project project;

    private List<License> licenses;

    public CryptoKeyDto(String description, Project project, List<License> licenses) {
        this.description = description;
        this.project = project;
        this.licenses = licenses;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }
}
