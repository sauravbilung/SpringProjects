package com.scopes.request;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;

import java.time.LocalDateTime;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestInfo {

    // Creates a new bean instance for every HTTP request
    // Lives only during a single request, then discarded
    // Ideal for storing request-specific or temporary data
    // Useful for logging, request tracking, or timestamps
    // Automatically destroyed after response is sent

    private final LocalDateTime timestamp;

    public RequestInfo() {
        this.timestamp = LocalDateTime.now();
        System.out.println(">> RequestInfo bean created at: " + timestamp);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
