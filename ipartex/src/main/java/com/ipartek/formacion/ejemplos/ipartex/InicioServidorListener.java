package com.ipartek.formacion.ejemplos.ipartex;

import com.ipartek.formacion.ejemplos.ipartex.pruebas.DatosRelleno;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.java.Log;

@Log
@WebListener
public class InicioServidorListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	log.info("RELLENADO DE DATOS DE PRUEBA INICIALES");
    	
		DatosRelleno.rellenar();
    }
}
