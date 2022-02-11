package se.astrom.complexjava.security.jwt;

import java.io.Serial;
import java.io.Serializable;

public class JwtResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwt;

    public JwtResponse(String token) {
        this.jwt = token;
    }

    public String getToken() {
        return jwt;
    }
}
