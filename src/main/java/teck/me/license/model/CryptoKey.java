package teck.me.license.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class CryptoKey {

    @GeneratedValue
    @Id
    private long id;

    private String uuid;

    private String description;

    @OneToMany(mappedBy = "cryptoKey")
    private List<License> licenses;

    @ManyToOne
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
}
