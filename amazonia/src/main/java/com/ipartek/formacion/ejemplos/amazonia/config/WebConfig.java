package com.ipartek.formacion.ejemplos.amazonia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${app.ruta.fotos}")
    private String rutaFotos;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Esto mapea /fotos/ en la web a la carpeta física en tu disco
        registry.addResourceHandler("/fotos/**")
                .addResourceLocations("file:" + rutaFotos);
    }
}