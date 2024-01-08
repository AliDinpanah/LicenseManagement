package teck.me.license.model.dto;

import javax.validation.constraints.Size;
import java.util.List;

public class CryptoKeyDto {

    @Size(max = 255)
    private String description;

    private ProjectDto project;

    private List<LicenseDto> licenses;

    public CryptoKeyDto(String description, ProjectDto project, List<LicenseDto> licenses) {
        this.description = description;
        this.project = project;
        this.licenses = licenses;
    }

    public CryptoKeyDto() {
    }

    public String getDescription() {
        return description;
    }

    public ProjectDto getProject() {
        return project;
    }

    public List<LicenseDto> getLicenses() {
        return licenses;
    }

}
