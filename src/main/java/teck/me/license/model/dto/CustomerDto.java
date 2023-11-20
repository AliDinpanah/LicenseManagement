package teck.me.license.model.dto;

import teck.me.license.model.Customer;
import teck.me.license.model.License;

import javax.validation.constraints.Pattern;
import java.util.List;

public class CustomerDto {

    private String name;

    private String email;


    private String phoneNumber;

    private List<License> licenses;

    public CustomerDto(Customer customer) {
        this.name=customer.getName();
        this.email=customer.getEmail();
        this.licenses=customer.getLicenses();
        this.phoneNumber=customer.getPhoneNumber();
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
}
