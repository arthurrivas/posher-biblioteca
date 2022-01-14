package br.com.poher.biblioteca.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.poher.biblioteca.domains.Usuario;
import br.com.poher.biblioteca.dto.UsuarioDTO;
import br.com.poher.biblioteca.repositorys.UsuarioRepository;
import br.com.poher.biblioteca.security.UsuarioSS;

@Service
public class UsuarioService  {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}
	public Usuario findByEmail(String email){
		return usuarioRepository.findByEmail(email);
		
	}
	
	public Usuario criarUsuario(UsuarioDTO usuarioDTO) {
		return new Usuario(null, usuarioDTO.getEmail(), passwordEncoder.encode(usuarioDTO.getSenha()));
	}
	
	public Usuario atualizaUsuario(Integer id, UsuarioDTO usuarioDTO) {
			Optional<Usuario> obj = findById(id);
			Usuario usuario = obj.get();
			
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
			
			saveAll(Arrays.asList(usuario));
			return usuario;
		
	}

	public void saveAll(List<Usuario> usuarios) {
		usuarioRepository.saveAll(usuarios);
		
	}
	
	
	 public Usuario findUsuario(Integer id) {
		 
		 UsuarioSS usuarioSS = UserService.authenticated();
		 if(usuarioSS == null && !id.equals(usuarioSS.getId())) {
			 return null;
		 }

		 Optional<Usuario> usuario = findById(id);
		 
		 return usuario.get();

		 
	 }
	
	 public void deletaUsuario(Integer id) {
		 usuarioRepository.deleteById(id);
	 }
	
}
