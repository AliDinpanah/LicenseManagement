package teck.me.license.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class CryptoKey {

    @GeneratedValue
    @Id
    private long id;

    private String description;

    @OneToMany(mappedBy = "cryptoKey")
    public List<License> licenses;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
