package com.ipartek.formacion.ejemplos.ipartex.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api/v1")
public class RestConfig extends Application {
    // Vacía: Jersey escanea automáticamente los recursos
}
