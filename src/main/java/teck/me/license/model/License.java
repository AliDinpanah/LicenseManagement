package teck.me.license.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class License {

    @GeneratedValue
    @Id
    private long id;

    @Min(1)
    private Integer validityDuration;

    private Long takeEffectTime;

    @Size(max = 36)
    private String uuid;

    @Size(max = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cryptoKey_id")
    private CryptoKey cryptoKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ElementCollection
    private Map<String, String> parameters;

    public License(long id, int validityDuration, Long takeEffectTime, String uuid, String description, CryptoKey cryptoKey, Project project, Customer customer, Map<String, String> parameters) {
        this.id = id;
        this.validityDuration = validityDuration;
        this.takeEffectTime = takeEffectTime;
        this.uuid = uuid;
        this.description = description;
        this.cryptoKey = cryptoKey;
        this.project = project;
        this.customer = customer;
        this.parameters = parameters;
    }

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

    public Long getTakeEffectTime() {
        return takeEffectTime;
    }

    public void setTakeEffectTime(Long takeEffectTime) {
        this.takeEffectTime = takeEffectTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        License license = (License) o;
        return id == license.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
