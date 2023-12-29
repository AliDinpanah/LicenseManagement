package teck.me.license.model.dto;

import java.util.List;

public class ListProjectDto {
    private final String name;

    private final String description;

    private final List<String> parameters;

    public ListProjectDto(String name, String description, List<String> parameters) {
        this.name = name;
        this.description = description;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getParameters() {
        return parameters;
    }

}
