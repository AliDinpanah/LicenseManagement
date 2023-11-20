package teck.me.license.model;

import javax.persistence.*;

@Entity
public class License {

    @GeneratedValue
    @Id
    private long id;

    private boolean updatable;

    private String description;

    @ManyToOne
    @JoinColumn(name = "cryptoKey_id")
    private CryptoKey cryptoKey;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
