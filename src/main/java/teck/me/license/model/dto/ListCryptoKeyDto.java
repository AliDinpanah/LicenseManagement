package teck.me.license.model.dto;



public class ListCryptoKeyDto {

    private final String description;

    private final String projectName;

    public ListCryptoKeyDto(String description, String projectName) {
        this.description = description;
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getProject() {
        return projectName;
    }

}
