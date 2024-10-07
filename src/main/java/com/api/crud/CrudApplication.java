package com.api.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
		// Abrir el navegador con la URL específica al iniciar la aplicación
		//openSwaggerUi();
	}


	private static void openSwaggerUi() {
		try {
			String url = "http://localhost:4000/swagger-ui/index.html";
			// Verifica si Desktop es compatible con el sistema
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				// Intenta abrir el navegador por defecto
				desktop.browse(new URI(url));
			} else {
				System.out.println("El navegador no es compatible en este sistema.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}



