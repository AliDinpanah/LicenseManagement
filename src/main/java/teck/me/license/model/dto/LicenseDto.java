package teck.me.license.model.dto;

import java.util.Map;

public class LicenseDto {
    private final int validityDuration;

    private final Long takeEffectTime;

    private final String cryptoKeyUuid;

    private final String projectName;

    private final String customerName;

    private final Map<String, String> parameters;

    private final String description;


    public LicenseDto(int validityDuration, Long takeEffectTime, String cryptoKeyUuid, String projectName, String customerName, Map<String, String> parameters, String description) {
        this.validityDuration = validityDuration;
        this.takeEffectTime = takeEffectTime;
        this.cryptoKeyUuid = cryptoKeyUuid;
        this.projectName = projectName;
        this.customerName = customerName;
        this.parameters = parameters;
        this.description = description;
    }

    public int getValidityDuration() {
        return validityDuration;
    }

    public Long getTakeEffectTime() {
        return takeEffectTime;
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

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getDescription() {
        return description;
    }
}
