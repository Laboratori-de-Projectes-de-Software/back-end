package jaumesitos.backend.demo.infrastructure.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    public static String getCurrentUserEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
