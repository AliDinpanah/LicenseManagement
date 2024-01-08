package teck.me.license.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
public class CryptoKey {

    @GeneratedValue
    @Id
    private long id;

    @Size(max = 36)
    private String uuid;

    @Size(max = 255)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cryptoKey")
    private List<License> licenses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoKey cryptoKey = (CryptoKey) o;
        return id == cryptoKey.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
