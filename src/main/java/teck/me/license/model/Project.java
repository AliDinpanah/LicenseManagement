package teck.me.license.model;

import javax.persistence.*;
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

}
