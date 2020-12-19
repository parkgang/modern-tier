package com.api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class Controller extends ResourceConfig {
    public Controller() {
        System.out.println("Jersey REST API Server가 활성화되었습니다. (URL: /api)");
        packages("com.api");
    }
}