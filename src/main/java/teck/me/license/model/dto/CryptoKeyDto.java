package teck.me.license.model.dto;

import java.util.List;

public class CryptoKeyDto {

    private final String description;

    private final String projectName;

    private final List<String> licensesId;

    public CryptoKeyDto(String description, String projectName, List<String> licensesId) {
        this.description = description;
        this.projectName = projectName;
        this.licensesId = licensesId;
    }


    public String getDescription() {
        return description;
    }

    public String getProject() {
        return projectName;
    }


}
