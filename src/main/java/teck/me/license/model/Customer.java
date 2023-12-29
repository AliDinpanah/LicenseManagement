package teck.me.license.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {

    @GeneratedValue
    @Id
    private long id;

    @Column(name = "name", nullable = false)
    @Size(max = 48)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_\\-\\.]*$")
    private String name;

    @Size(max = 255)
    private String address;

    @Email
    private String email;

    @Pattern(regexp = "^(?:\\+98|09)\\d{9}$")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    private List<License> licenses;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
