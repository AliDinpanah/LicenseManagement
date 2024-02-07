package teck.me.license.model.dto;

import javax.validation.constraints.Size;
import java.util.List;

public class CreateCryptoKeyDto {
    @Size(max = 255)
    private final String description;

    private final String projectName;

    private final List<String> licensesId;

    public CreateCryptoKeyDto(String description, String projectName, List<String> licensesId) {
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

    public List<String> getLicensesId() {
        return licensesId;
    }
}
