package teck.me.license.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class CustomerDto {

    @Size(max = 48)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_\\-.]*$")
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^(?:\\+98|09)\\d{9}$")
    private String phoneNumber;

    @Size(max = 255)
    private String address;

    private List<LicenseDto> licenses;


    public CustomerDto(String name, String email, String phoneNumber, String address, List<LicenseDto> licenses) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.licenses = licenses;
    }

    public CustomerDto() {
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

    public List<LicenseDto> getLicenses() {
        return licenses;
    }
}
