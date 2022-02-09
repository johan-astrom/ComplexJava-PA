package se.astrom.complexjava.dto;

import java.io.Serializable;
import java.util.Objects;

public class Microsoft365LicenseDto implements Serializable {
    private final Long id;
    private final String name;
    private final String skuId;

    public Microsoft365LicenseDto(Long id, String name, String skuId) {
        this.id = id;
        this.name = name;
        this.skuId = skuId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSkuId() {
        return skuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Microsoft365LicenseDto entity = (Microsoft365LicenseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.skuId, entity.skuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, skuId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "skuId = " + skuId + ")";
    }
}
