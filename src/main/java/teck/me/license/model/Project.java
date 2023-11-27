package teck.me.license.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @GeneratedValue
    @Id
    private long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "project")
    private List<License> licenses;

    @OneToMany(mappedBy = "project")
    private List<CryptoKey> cryptoKeys;

    @ElementCollection
    private List<String> parameters;

    public Project() {
        parameters=new ArrayList<String>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}
