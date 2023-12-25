package teck.me.license.model.dto;

import teck.me.license.model.Customer;
import teck.me.license.model.License;

import java.util.List;

public class CustomerDto {
    private String name;

    private String email;

    private String phoneNumber;

    private String description;

    private String address;

    private List<License> licenses;


    public CustomerDto(Customer customer) {
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        this.licenses = customer.getLicenses();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public List<License> getLicenses() {
        return licenses;
    }
}
