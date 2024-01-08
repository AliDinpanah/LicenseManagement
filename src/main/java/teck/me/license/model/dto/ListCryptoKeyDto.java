package teck.me.license.model.dto;



import javax.validation.constraints.Size;


public class ListCryptoKeyDto {

    @Size(max = 255)
    private final String description;

    private final ProjectDto project;

    public ListCryptoKeyDto(String description, ProjectDto project) {
        this.description = description;
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public ProjectDto getProject() {
        return project;
    }

}
