package teck.me.license.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Customer {

    @GeneratedValue
    @Id
    private long id;

    private String name;

    private String email;

    @Pattern(regexp = "09/d{9}")
    private String phoneNumber;

    @OneToMany
    private List<License> licenses;
}
