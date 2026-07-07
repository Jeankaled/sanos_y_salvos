package cl.sanosysalvos.mascota_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "cl.sanosysalvos.mascota_service") // Asegúrate de que este sea el paquete raíz
@SpringBootApplication
public class MascotaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MascotaServiceApplication.class, args);
	}

}
