package se.astrom.complexjava.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LicenseOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String displayName;
    private String stringIdentifier;
    private String skuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStringIdentifier() {
        return stringIdentifier;
    }

    public void setStringIdentifier(String stringIdentifier) {
        this.stringIdentifier = stringIdentifier;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
