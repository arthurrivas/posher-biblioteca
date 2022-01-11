package br.com.poher.biblioteca.resources.dto;

import br.com.poher.biblioteca.domains.Livro;

public class LivroDTO {
	
	private String titulo;
	private String descricao;
	private String isbn;
	
	
	public LivroDTO() {
		super();
	}


	public LivroDTO(String titulo, String descricao, String isbn) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.isbn = isbn;
	}
	
	public Livro criaLivro() {
		return new Livro(null, this.titulo, this.descricao , this.isbn);
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


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
	
	
	
}
