package br.com.poher.biblioteca.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.poher.biblioteca.domains.Livro;
import br.com.poher.biblioteca.service.LivroService;

@Configuration
public class PopulateDB implements CommandLineRunner {

	@Autowired
	LivroService livroService;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		livroService.deleteAll();
		
		
	
		
		Livro livro1 = new Livro(null, "Carrie - a estranha", "Menina com poderes telecineticos");
		Livro livro2 = new Livro(null, "1984", "versao distopica de George Orwell");
		
		
		
		
		
		
		livroService.saveAll(Arrays.asList(livro1, livro2));
		
		
		
	}

	
	
	
	
}
