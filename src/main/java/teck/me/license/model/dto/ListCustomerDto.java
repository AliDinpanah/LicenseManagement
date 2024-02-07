package teck.me.license.model.dto;

import teck.me.license.model.Customer;


public class ListCustomerDto {

    private final String name;

    private final String email;

    private final String phoneNumber;

    private final String address;


    public ListCustomerDto(Customer customer) {
        this.name = customer.getName();
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

    public String getAddress() {
        return address;
    }
}
