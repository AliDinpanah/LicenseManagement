package teck.me.license.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateCustomerDto {

    @Size(max = 48)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_\\-.]*$")
    private final String name;

    @Email
    private final String email;

    @Pattern(regexp = "^(?:\\+98|09)\\d{9}$")
    private final String phoneNumber;

    @Size(max = 255)
    private final String address;

    private final List<String> licensesId;


    public CreateCustomerDto(String name, String email, String phoneNumber, String address, List<String> licensesId) {
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
