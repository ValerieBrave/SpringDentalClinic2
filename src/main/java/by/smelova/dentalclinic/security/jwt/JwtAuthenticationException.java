package by.smelova.dentalclinic.security.jwt;

import org.springframework.security.core.AuthenticationException;

import javax.security.auth.message.AuthException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
