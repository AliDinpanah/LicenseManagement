package teck.me.license.model.dto;


import java.util.List;

public class ProjectDto {

    private final String name;

    private final String description;

    private final List<String> licensesId;

    private final List<String> cryptoKeysId;

    private final List<String> parameters;

    public ProjectDto(String name, String description, List<String> licensesId, List<String> cryptoKeysId, List<String> parameters) {
        this.name = name;
        this.description = description;
        this.licensesId = licensesId;
        this.cryptoKeysId = cryptoKeysId;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getLicensesId() {
        return licensesId;
    }

    public List<String> getCryptoKeysId() {
        return cryptoKeysId;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
