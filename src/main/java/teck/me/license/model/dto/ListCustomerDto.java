package teck.me.license.model.dto;

import teck.me.license.model.Customer;

import javax.validation.constraints.*;


public class ListCustomerDto {

    @Size(max = 48)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_\\-.]*$")
    private final String name;

    @Email
    private final String email;

    @Pattern(regexp = "^(?:\\+98|09)\\d{9}$")
    private final String phoneNumber;

    @Size(max = 255)
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
