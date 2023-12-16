package teck.me.license.model.dto;

import teck.me.license.model.License;
import teck.me.license.model.Project;

import java.util.List;

public class ListCryptoKeyDto {

    private String description;

    private Project project;

    public ListCryptoKeyDto(String description, Project project) {
        this.description = description;
        this.project = project;
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

}
