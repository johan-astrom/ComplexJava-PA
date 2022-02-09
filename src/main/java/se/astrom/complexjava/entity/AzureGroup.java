package se.astrom.complexjava.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class AzureGroup {

    @Id
    private String AzureObjectId;
    private String displayName;
    private String description;
    @ManyToMany
    private List<AzureUser> azureUsers;

    public String getAzureObjectId() {
        return AzureObjectId;
    }

    public void setAzureObjectId(String azureObjectId) {
        AzureObjectId = azureObjectId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AzureUser> getAzureUsers() {
        return azureUsers;
    }

    public void setAzureUsers(List<AzureUser> azureUsers) {
        this.azureUsers = azureUsers;
    }
}
