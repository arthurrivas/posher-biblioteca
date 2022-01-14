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
import org.springframework.web.bind.annotation.RestController;

import br.com.poher.biblioteca.domains.Usuario;
import br.com.poher.biblioteca.dto.UsuarioDTO;
import br.com.poher.biblioteca.security.UsuarioSS;
import br.com.poher.biblioteca.service.UserService;
import br.com.poher.biblioteca.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResources {

	@Autowired
	UsuarioService usuarioService;

	// retorna todos os usuarios e seus livros criados, permitindo fazer a consuta
	// opcional de email pela requisição
	@GetMapping()
	public ResponseEntity<?> listaUsuarios(@PathVariable(required = false, value = "email") String email) {

		if (email != null) {
			Usuario usuario = usuarioService.findByEmail(email);
			return new ResponseEntity<Usuario>(HttpStatus.OK).ok(usuario);

		}

		List<Usuario> listaUsuarios = usuarioService.findAll();

		return new ResponseEntity<Usuario>(HttpStatus.OK).ok(listaUsuarios);

	}

	// Atualiza as informações do usuario, permite atualizar apenas se o usuario
	// estiver logado
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizaUsuario(@PathVariable("id") String id, UsuarioDTO usuarioDTO) {
		Integer idInt = Integer.valueOf(id);

		UsuarioSS usuarioSS = UserService.authenticated();
		if (usuarioSS != null && usuarioSS.getId() == idInt) {

			Usuario usuario = usuarioService.atualizaUsuario(idInt, usuarioDTO);

			return new ResponseEntity<Usuario>(HttpStatus.OK).ok(usuario);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	/*
	 * Cadastra um usuario novo usuario passando os dados pelo corpo da requisição.
	 * Exemplo:{"email": "arthurrivas1@gmail.com", "senha": "1234"}
	 */
	@PostMapping
	public ResponseEntity<?> cadastraUsuario(@RequestBody UsuarioDTO usuarioDTO) {

		Usuario usuario = usuarioService.criarUsuario(usuarioDTO);

		usuarioService.saveAll(Arrays.asList(usuario));

		return new ResponseEntity<Usuario>(HttpStatus.CREATED).ok(usuario);
	}


}
