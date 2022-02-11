package se.astrom.complexjava.dto;

import se.astrom.complexjava.entity.Microsoft365License;

import java.io.Serializable;
import java.util.Objects;

public class LicenseOptionDto implements Serializable {
    private final String displayName;
    private final String stringIdentifier;
    private final String skuId;
    private final Microsoft365License license;

    public LicenseOptionDto(String displayName, String stringIdentifier, String skuId, Microsoft365License license) {
        this.displayName = displayName;
        this.stringIdentifier = stringIdentifier;
        this.skuId = skuId;
        this.license = license;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getStringIdentifier() {
        return stringIdentifier;
    }

    public String getSkuId() {
        return skuId;
    }

    public Microsoft365License getLicense() {
        return license;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicenseOptionDto entity = (LicenseOptionDto) o;
        return Objects.equals(this.license, entity.license) &&
                Objects.equals(this.displayName, entity.displayName) &&
                Objects.equals(this.stringIdentifier, entity.stringIdentifier) &&
                Objects.equals(this.skuId, entity.skuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName, stringIdentifier, skuId, license);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "licenseId = " + license.getId() + ", " +
                "displayName = " + displayName + ", " +
                "stringIdentifier = " + stringIdentifier + ", " +
                "skuId = " + skuId + ")";
    }
}
