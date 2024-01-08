package teck.me.license.model.dto;



import javax.validation.constraints.*;
import java.util.Map;

public class LicenseDto {

    @Size(min = 1)
    private int validityDuration;

    private Long takeEffectTime;

    private CryptoKeyDto cryptoKey;

    private ProjectDto project;

    private CustomerDto customer;

    private Map<String, String> parameters;

    @Size(max = 255)
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
