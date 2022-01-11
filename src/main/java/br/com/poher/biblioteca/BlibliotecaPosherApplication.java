	package br.com.poher.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class BlibliotecaPosherApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlibliotecaPosherApplication.class, args);
	}

}
