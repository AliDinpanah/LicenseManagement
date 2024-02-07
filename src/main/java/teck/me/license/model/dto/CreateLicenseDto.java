package teck.me.license.model.dto;

import javax.validation.constraints.*;
import java.util.Map;

public class CreateLicenseDto {

    @Min(1)
    private final Integer validityDuration;

    private final Long takeEffectTime;

    private final String cryptoKeyUuid;

    private final String projectName;

    private final String customerName;

    private final Map<String, String> parameters;

    @Size(max = 255)
    private final String description;

    public CreateLicenseDto(int validityDuration, Long takeEffectTime, String cryptoKeyUuid, String projectName, String customerName, Map<String, String> parameters, String description) {
        this.validityDuration = validityDuration;
        this.takeEffectTime = takeEffectTime;
        this.cryptoKeyUuid = cryptoKeyUuid;
        this.projectName = projectName;
        this.customerName = customerName;
        this.parameters = parameters;
        this.description = description;
    }

    public String getCryptoKeyUuid() {
        return cryptoKeyUuid;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getTakeEffectTime() {
        return takeEffectTime;
    }


    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getDescription() {
        return description;
    }

    public int getValidityDuration() {
        return validityDuration;
    }
}
