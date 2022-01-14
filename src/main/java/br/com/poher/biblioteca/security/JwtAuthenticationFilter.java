package br.com.poher.biblioteca.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.server.Cookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.poher.biblioteca.dto.CredenciaisDTO;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	private JwtUtils jwtUtils;
	
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
			
		
		Authentication auth;
		try {
			CredenciaisDTO cred = new ObjectMapper()
					.readValue(req.getInputStream(), CredenciaisDTO.class);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(cred.getEmail(),
																									cred.getSenha(),
																									new ArrayList<>());
			auth = authenticationManager.authenticate(authToken);
			
			
		
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return auth;
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		
		String username = ((UsuarioSS) auth.getPrincipal()).getUsername();
		String token =jwtUtils.generateToken(username);
		res.setHeader("Authorization", "Bearer " + token);
		req.setAttribute("Authorization", "Bearer " + token);
		
		
		
		
		
		
		
		
		
	}
}
