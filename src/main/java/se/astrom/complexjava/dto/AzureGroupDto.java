package se.astrom.complexjava.dto;

import java.io.Serializable;
import java.util.Objects;

public class AzureGroupDto implements Serializable {
    private final String azureObjectId;
    private final String displayName;
    private final String description;

    public AzureGroupDto(String displayName, String description, String azureObjectId) {
        this.azureObjectId = azureObjectId;
        this.displayName = displayName;
        this.description = description;
    }

    public String getAzureObjectId() {
        return azureObjectId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AzureGroupDto entity = (AzureGroupDto) o;
        return Objects.equals(this.displayName, entity.displayName) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.azureObjectId, entity.azureObjectId) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(azureObjectId, displayName, description);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "azureObjectId = " + azureObjectId + ", " +
                "displayName = " + displayName + ", " +
                "description = " + description + ")";
    }
}
