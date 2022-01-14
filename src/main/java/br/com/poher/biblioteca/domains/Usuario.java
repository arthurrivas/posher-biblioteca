package br.com.poher.biblioteca.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.poher.biblioteca.domains.enums.PerfilEnum;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String email;
	
	@JsonIgnore
	private String senha;
	@JsonIgnore
	private String perfil;
	
	
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Livro> livrosCadastrados= new ArrayList<Livro>();
	
	public Usuario() {
		this.setPerfil(PerfilEnum.ROLE_USUARIO.toString());
	}

	public Usuario(Integer id, String email, String senha) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.setPerfil(PerfilEnum.ROLE_USUARIO.toString());
	}
	
	
	public void adicionaLivros(Livro livro) {
		this.livrosCadastrados.add(livro);
	}
	public List<Livro> getListaLivros(){
		return this.livrosCadastrados;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", perfil=" + perfil
				+ ", livrosCadastrados=" + livrosCadastrados + "]";
	}
	
	
	
	
	
	
}
