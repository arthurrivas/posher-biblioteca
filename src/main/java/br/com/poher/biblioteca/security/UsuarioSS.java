package br.com.poher.biblioteca.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioSS implements UserDetails {

	private Integer id;
	private String email;
	private String senha;
	private GrantedAuthority authoritys;
	
	public UsuarioSS() {
	}
	
	public UsuarioSS(Integer id, String email, String senha, String	 perfil) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authoritys = new SimpleGrantedAuthority(perfil);
	}


	public Integer getId() {
		return this.id;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
