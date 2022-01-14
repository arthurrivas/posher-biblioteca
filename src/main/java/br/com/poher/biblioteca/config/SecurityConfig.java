package br.com.poher.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.poher.biblioteca.security.JwtAuthenticationFilter;
import br.com.poher.biblioteca.security.JwtAuthorizationFilter;
import br.com.poher.biblioteca.security.JwtUtils;
import br.com.poher.biblioteca.service.UsuarioDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/livros/**",
			"/usuarios/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/usuarios",
			"/login"
	};
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET ).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.anyRequest().authenticated();
		http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtils));
		http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtils, userDetailsService));
		http.httpBasic()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	}
	
	
	

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
	
}
