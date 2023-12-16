package teck.me.license.model.dto;

import teck.me.license.model.Customer;

public class ListCustomerDto {

    private String name;

    private String email;

    private String phoneNumber;

    private String description;

    private String address;


    public ListCustomerDto(Customer customer) {
        this.name = customer.getName();
        this.description = customer.getDescription();
        this.address = customer.getAddress();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
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
}
