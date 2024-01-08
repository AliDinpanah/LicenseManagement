package teck.me.license.model.dto;


import java.util.List;

public class ProjectDto {

    private String name;

    private String description;

    private List<LicenseDto> licenses;

    private List<CryptoKeyDto> cryptoKeys;

    private List<String> parameters;

    public ProjectDto(String name, String description, List<LicenseDto> licenses, List<CryptoKeyDto> cryptoKeys, List<String> parameters) {
        this.name = name;
        this.description = description;
        this.licenses = licenses;
        this.cryptoKeys = cryptoKeys;
        this.parameters = parameters;
    }

    public ProjectDto() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public List<LicenseDto> getLicenses() {
        return licenses;
    }

    public List<CryptoKeyDto> getCryptoKeys() {
        return cryptoKeys;
    }


    public List<String> getParameters() {
        return parameters;
    }
}
