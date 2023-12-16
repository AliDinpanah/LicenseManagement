package teck.me.license.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {

    @GeneratedValue
    @Id
    private long id;

    @Column(name = "name", nullable = false)
    @Size(max = 48)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_\\-\\.]*$\n")
    private String name;

    @Size(max = 255)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<License> licenses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<CryptoKey> cryptoKeys;

    @ElementCollection
    private List<String> parameters;

    public Project() {
        parameters = new ArrayList<String>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
