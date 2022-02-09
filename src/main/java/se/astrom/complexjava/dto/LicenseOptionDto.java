package se.astrom.complexjava.dto;

import java.io.Serializable;
import java.util.Objects;

public class LicenseOptionDto implements Serializable {
    private final Long id;
    private final String displayName;
    private final String stringIdentifier;
    private final String skuId;

    public LicenseOptionDto(Long id, String displayName, String stringIdentifier, String skuId) {
        this.id = id;
        this.displayName = displayName;
        this.stringIdentifier = stringIdentifier;
        this.skuId = skuId;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicenseOptionDto entity = (LicenseOptionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.displayName, entity.displayName) &&
                Objects.equals(this.stringIdentifier, entity.stringIdentifier) &&
                Objects.equals(this.skuId, entity.skuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, stringIdentifier, skuId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "displayName = " + displayName + ", " +
                "stringIdentifier = " + stringIdentifier + ", " +
                "skuId = " + skuId + ")";
    }
}
