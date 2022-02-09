package se.astrom.complexjava.dto;

import java.io.Serializable;
import java.util.Objects;

public class ApplicationUserPostDto implements Serializable {
    private final String username;
    private final String password;

    public ApplicationUserPostDto(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUserPostDto entity = (ApplicationUserPostDto) o;
        return Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password);

    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "username = " + username + ", " +
                "password = " + password;
    }
}
