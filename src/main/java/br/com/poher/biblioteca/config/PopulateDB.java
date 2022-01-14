package br.com.poher.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.poher.biblioteca.service.LivroService;

@Configuration
public class PopulateDB implements CommandLineRunner {

	@Autowired
	LivroService livroService;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		
		
	}

	
	
	
	
}
