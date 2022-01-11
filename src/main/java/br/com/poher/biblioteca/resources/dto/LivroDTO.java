package br.com.poher.biblioteca.resources.dto;

import br.com.poher.biblioteca.domains.Livro;

public class LivroDTO {
	
	private String titulo;
	private String descricao;
	
	
	public LivroDTO() {
		super();
	}


	public LivroDTO(String titulo, String descricao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Livro criaLivro() {
		return new Livro(null, this.titulo, this.descricao);
	}

	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
