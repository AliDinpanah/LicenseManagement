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

    private final List<CryptoKeyDto> cryptoKeys;

    public CreateProjectDto(String name, String description, List<String> parameters, List<CryptoKeyDto> cryptoKeys) {
        this.name = name;
        this.description = description;
        this.parameters = parameters;
        this.cryptoKeys = cryptoKeys;
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

    public List<CryptoKeyDto> getCryptoKeys() {
        return cryptoKeys;
    }
}
