package se.astrom.complexjava.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class AzureUser {

    @Id
    private String AzureObjectId;
    private String displayName;
    private String userPrincipalName;
    private String email;
    private String mobilePhone;
    @ManyToMany
    private List<AzureGroup> azureGroups;

    public void addToGroup(AzureGroup group){
        this.azureGroups.add(group);
        group.getAzureUsers().add(this);
    }

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

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<AzureGroup> getAzureGroups() {
        return azureGroups;
    }

    public void setAzureGroups(List<AzureGroup> azureGroups) {
        this.azureGroups = azureGroups;
    }
}
