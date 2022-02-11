package se.astrom.complexjava.dto;

import se.astrom.complexjava.entity.AzureUser;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AzureGroupMembersDto implements Serializable {

    private final String displayName;
    private final List<AzureUser> azureUsers;

    public AzureGroupMembersDto(String displayName, List<AzureUser> azureUsers) {
        this.displayName = displayName;
        this.azureUsers = azureUsers;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<AzureUser> getAzureUsers() {
        return azureUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AzureGroupMembersDto that = (AzureGroupMembersDto) o;
        return displayName.equals(that.displayName) && azureUsers.equals(that.azureUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName, azureUsers);
    }

    @Override
    public String toString() {
        return "AzureGroupMembersDto{" +
                "displayName='" + displayName + '\'' +
                ", azureUsers=" + azureUsers +
                '}';
    }
}
