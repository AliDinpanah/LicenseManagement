package teck.me.license.model.dto;

import teck.me.license.model.CryptoKey;
import teck.me.license.model.License;

import java.util.List;

public class ListProjectDto {
    private String name;

    private String description;

    private List<String> parameters;

    public ListProjectDto(String name, String description, List<String> parameters) {
        this.name = name;
        this.description = description;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}
