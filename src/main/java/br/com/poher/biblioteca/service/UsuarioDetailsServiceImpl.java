package br.com.poher.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.poher.biblioteca.domains.Usuario;
import br.com.poher.biblioteca.repositorys.UsuarioRepository;
import br.com.poher.biblioteca.security.UsuarioSS;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		
		
		return new UsuarioSS(usuario.getId(),usuario.getEmail(),usuario.getSenha(), usuario.getPerfil());
	}

}
