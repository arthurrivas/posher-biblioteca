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
import br.com.poher.biblioteca.domains.Usuario;
import br.com.poher.biblioteca.dto.LivroDTO;
import br.com.poher.biblioteca.security.UsuarioSS;
import br.com.poher.biblioteca.service.LivroService;
import br.com.poher.biblioteca.service.UserService;
import br.com.poher.biblioteca.service.UsuarioService;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResources {
	
	@Autowired
	LivroService livroService;
	
	@Autowired
	UsuarioService usuarioService;
	
	
	// retorna todos os livros, ou então seleciona um usando o opcional parametro "titulo"
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
	
	@PostMapping
	public ResponseEntity<?> cadastraLivro(@RequestBody LivroDTO livroDTO){
		
		UsuarioSS usuarioSS = UserService.authenticated();
		
		if(usuarioSS != null ) {
			Optional<Usuario> obj = usuarioService.findById(usuarioSS.getId());
			Usuario usuario = obj.get();
			
			Livro livro = livroService.criaLivro(livroDTO, usuario);
			
			usuario.adicionaLivros(livro);
			
			usuarioService.saveAll(Arrays.asList(usuario));
			livroService.saveAll(Arrays.asList(livro));
				
			return new ResponseEntity<Livro>(HttpStatus.CREATED).ok(livro);
		}
		
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	// Atualiza o livro baseado no id passado como variavel no caminho da requisição,
	// Passe as novas informações pelo corpo da requisição
	//EX: {"tiluto":"novo titulo", "descricao":"", "isbn":"111111111"}
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizaLivro(@PathVariable(name = "id") String id, @RequestBody LivroDTO livroDTO){
		Integer idInt = Integer.valueOf(id);
		
		UsuarioSS usuarioSS = UserService.authenticated();
		if(usuarioSS != null) {
			
			Optional<Livro> objLivro = livroService.findById(idInt);
			Optional<Usuario> objUsuario = usuarioService.findById(usuarioSS.getId());
			Usuario usuario = objUsuario.get();
			
			Livro livro = livroService.atualizaLivro( usuario, objLivro.get(), livroDTO); 
			usuario.adicionaLivros(livro);
			
			
			usuarioService.saveAll(Arrays.asList(usuario));
			livroService.saveAll(Arrays.asList(livro));
			
			return new ResponseEntity<>(HttpStatus.ACCEPTED).ok(livro);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> apagaLivro(@PathVariable String id){
		
		livroService.deleteById(Integer.valueOf(id));
		
		return ResponseEntity.noContent().build(); 
	}
	
}
