package teck.me.license.model.dto;

import teck.me.license.model.CryptoKey;
import teck.me.license.model.License;

import javax.persistence.OneToMany;
import java.util.List;

public class ProjectDto {

    private String name;

    private String description;

    private List<License> licenses;

    private List<CryptoKey> cryptoKeys;

    public ProjectDto(String name, String description, List<License> licenses, List<CryptoKey> cryptoKeys) {
        this.name = name;
        this.description = description;
        this.licenses = licenses;
        this.cryptoKeys = cryptoKeys;
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

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    public List<CryptoKey> getCryptoKeys() {
        return cryptoKeys;
    }

    public void setCryptoKeys(List<CryptoKey> cryptoKeys) {
        this.cryptoKeys = cryptoKeys;
    }
}
