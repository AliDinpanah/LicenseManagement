package teck.me.license.model.dto;

import teck.me.license.model.License;
import teck.me.license.model.Project;

import javax.validation.constraints.Size;
import java.util.List;

public class ListCryptoKeyDto {

    @Size(max = 255)
    private final String description;

    private final Project project;

    public ListCryptoKeyDto(String description, Project project) {
        this.description = description;
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }

}
