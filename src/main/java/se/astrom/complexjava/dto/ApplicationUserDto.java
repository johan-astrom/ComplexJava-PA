package se.astrom.complexjava.dto;

import java.io.Serializable;
import java.util.Objects;

public class ApplicationUserDto implements Serializable {
    private final Long id;
    private final String username;
    private final String password;

    public ApplicationUserDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public Long getId() {
        return id;
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
        ApplicationUserDto entity = (ApplicationUserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + username + ", " +
                "password = " + password;
    }
}
