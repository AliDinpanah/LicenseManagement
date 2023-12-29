package teck.me.license.model.dto;


import teck.me.license.model.CryptoKey;
import teck.me.license.model.Customer;
import teck.me.license.model.License;
import teck.me.license.model.Project;

import java.util.Map;

public class LicenseDto {

    private int validityDuration;

    private Long takeEffectTime;

    private CryptoKeyDto cryptoKey;

    private ProjectDto project;

    private CustomerDto customer;

    private Map<String, String> parameters;

    private String description;

    public LicenseDto(int validityDuration, Long takeEffectTime, CryptoKeyDto cryptoKey, ProjectDto project, CustomerDto customer, Map<String, String> parameters, String description) {
        this.validityDuration = validityDuration;
        this.takeEffectTime = takeEffectTime;
        this.cryptoKey = cryptoKey;
        this.project = project;
        this.customer = customer;
        this.parameters = parameters;
        this.description = description;
    }

    public LicenseDto() {
    }

    public Long getTakeEffectTime() {
        return takeEffectTime;
    }

    public CryptoKeyDto getCryptoKey() {
        return cryptoKey;
    }

    public ProjectDto getProject() {
        return project;
    }

    public CustomerDto getCustomer() {
        return customer;
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
