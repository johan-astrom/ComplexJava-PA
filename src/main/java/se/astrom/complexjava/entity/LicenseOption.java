package se.astrom.complexjava.entity;

import javax.persistence.*;

@Entity
public class LicenseOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String displayName;
    private String stringIdentifier;
    private String skuId;
    @ManyToOne
    private Microsoft365License license;

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

    public Microsoft365License getLicense() {
        return license;
    }

    public void setLicense(Microsoft365License license) {
        this.license = license;
    }
}
