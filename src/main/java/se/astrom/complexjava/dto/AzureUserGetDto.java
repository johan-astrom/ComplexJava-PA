package se.astrom.complexjava.dto;

import java.io.Serializable;
import java.util.Objects;

public class AzureUserGetDto implements Serializable {
    private final String displayName;
    private final String userPrincipalName;
    private final String email;
    private final String mobilePhone;

    public AzureUserGetDto(String displayName, String userPrincipalName, String email, String mobilePhone) {
        this.displayName = displayName;
        this.userPrincipalName = userPrincipalName;
        this.email = email;
        this.mobilePhone = mobilePhone;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AzureUserGetDto entity = (AzureUserGetDto) o;
        return Objects.equals(this.displayName, entity.displayName) &&
                Objects.equals(this.userPrincipalName, entity.userPrincipalName) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.mobilePhone, entity.mobilePhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName, userPrincipalName, email, mobilePhone);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "displayName = " + displayName + ", " +
                "userPrincipalName = " + userPrincipalName + ", " +
                "email = " + email + ", " +
                "mobilePhone = " + mobilePhone + ")";
    }
}
