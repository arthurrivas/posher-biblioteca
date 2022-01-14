package br.com.poher.biblioteca.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter  {
	
	
	private JwtUtils jwtUtils;
	private UserDetailsService userDetailService;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager ,JwtUtils jwtUtils, UserDetailsService userDetailService) {
		super(authenticationManager);
		this.jwtUtils = jwtUtils;
		this.userDetailService = userDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		
		String header = req.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(req, header.substring(7));
			
			if(auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(req, res);
		
		
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req, String token) {
		if(jwtUtils.tokenValido(token)){
			String username = jwtUtils.getUsername(token);
			UserDetails user = userDetailService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user ,null, user.getAuthorities());
		}
		return null;
	}
	
}
