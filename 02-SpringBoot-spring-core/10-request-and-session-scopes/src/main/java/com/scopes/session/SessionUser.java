package com.scopes.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS) // 👈 This tells Spring to inject a proxy instead of the real bean directly.
public class SessionUser {

    // This avoids errors when the app starts and no user session exists yet.
    // The real object will be created only when a user makes a request.
    private String username;

    public SessionUser() {
        System.out.println(">> SessionUser bean created");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
