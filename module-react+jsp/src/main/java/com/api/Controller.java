package com.api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class Controller extends ResourceConfig {
    public Controller() {
        System.out.println("/api");
        packages("com.api");
    }
}