package teck.me.license.model.dto;


import teck.me.license.model.CryptoKey;
import teck.me.license.model.Customer;
import teck.me.license.model.License;
import teck.me.license.model.Project;

import java.util.Date;
import java.util.Map;

public class LicenseDto {

    private int validityDuration;

    private Date takeEffectTime;

    private CryptoKey cryptoKey;

    private Project project;

    private Customer customer;

    private Map<String, String> parameters;

    private String description;

    public LicenseDto(License license) {
        this.validityDuration = license.getValidityDuration();
        this.takeEffectTime = license.getTakeEffectTime();
        this.cryptoKey = license.getCryptoKey();
        this.project = license.getProject();
        this.customer = license.getCustomer();
        this.description = license.getDescription();
    }

    public LicenseDto(int validityDuration, Date takeEffectTime, CryptoKey cryptoKey, Project project, Customer customer) {
        this.validityDuration = validityDuration;
        this.takeEffectTime = takeEffectTime;
        this.cryptoKey = cryptoKey;
        this.project = project;
        this.customer = customer;
    }

    public LicenseDto(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public int getValidityDuration() {
        return validityDuration;
    }

    public void setValidityDuration(int validityDuration) {
        this.validityDuration = validityDuration;
    }

    public Date getTakeEffectTime() {
        return takeEffectTime;
    }

    public void setTakeEffectTime(Date takeEffectTime) {
        this.takeEffectTime = takeEffectTime;
    }

    public CryptoKey getCryptoKey() {
        return cryptoKey;
    }

    public void setCryptoKey(CryptoKey cryptoKey) {
        this.cryptoKey = cryptoKey;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
