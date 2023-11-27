package teck.me.license.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class License {

    @GeneratedValue
    @Id
    private long id;

    private int validityDuration;

    private Date takeEffectTime;

    private String uuid;

    private boolean updatable;

    private String description;

    @ManyToOne
    @JoinColumn(name = "cryptoKey_id")
    private CryptoKey cryptoKey;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ElementCollection
    private Map<String, String> parameters;

    public License() {
        parameters = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
