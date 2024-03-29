package teck.me.license.model.dto;

import java.util.List;

public class CustomerDto {

    private final String name;

    private final String email;

    private final String phoneNumber;

    private final String address;

    private final List<String> licensesId;


    public CustomerDto(String name, String email, String phoneNumber, String address, List<String> licensesId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.licensesId=licensesId;
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

    public List<String> getLicensesId() {
        return licensesId;
    }
}
