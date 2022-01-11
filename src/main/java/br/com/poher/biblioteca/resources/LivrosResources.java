package br.com.poher.biblioteca.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.poher.biblioteca.domains.Livro;
import br.com.poher.biblioteca.resources.dto.LivroDTO;
import br.com.poher.biblioteca.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResources {
	
	@Autowired
	LivroService livroService;
	
	@GetMapping
	public ResponseEntity<?> listarLivros(@RequestParam(required = false, value = "titulo") String titulo){
		
		List<Livro> listaLivros = new ArrayList<Livro>();
		
		if( titulo != null) {
			listaLivros = livroService.findByTituloLike(titulo);
		}else {
			listaLivros = livroService.findAll(); 
		}
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED).ok(listaLivros);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscaUmLivro(@PathVariable String id){
		
		
		
		Optional<Livro> livro = livroService.findById(Integer.valueOf(id));
		
		if( livro == null) {
			throw new IllegalArgumentException();
		}
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED).ok(livro.get());	
	}
	
	@PostMapping(value = "/cadastro")
	public ResponseEntity<?> cadastraLivro(@RequestBody LivroDTO livroDTO){
		
		Livro livro = livroDTO.criaLivro();
		
		livroService.saveAll(Arrays.asList(livro));
		
		return new ResponseEntity<Livro>(HttpStatus.CREATED).ok(livro);
	}
	
	
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<?> atualizaLivro(@PathVariable(name = "id") String id, @RequestBody LivroDTO livroDTO){
		
		Optional<Livro> obj = livroService.findById(Integer.valueOf(id));
		Livro livro = obj.get();
		
		livro.setTitulo(livroDTO.getTitulo());
		livro.setDescricao(livroDTO.getDescricao());
		livro.setIsbn(livroDTO.getIsbn());
		
		
		livroService.saveAll(Arrays.asList(livro));
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED).ok(livro);
		
	}
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> apagaLivro(@PathVariable String id){
		
		livroService.deleteById(Integer.valueOf(id));
		
		return ResponseEntity.noContent().build();
	}
	
}
