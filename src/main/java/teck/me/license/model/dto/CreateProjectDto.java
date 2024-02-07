package teck.me.license.model.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateProjectDto {

    @Size(max = 48)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_\\-.]*$")
    private final String name;

    @Size(max = 255)
    private final String description;

    private final List<String> parameters;

    private final List<String> cryptoKeysId;

    public CreateProjectDto(String name, String description, List<String> parameters, List<String> cryptoKeysId) {
        this.name = name;
        this.description = description;
        this.parameters = parameters;
        this.cryptoKeysId = cryptoKeysId;
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

    public List<String> getCryptoKeysId() {
        return cryptoKeysId;
    }
}
