package br.com.poher.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poher.biblioteca.domains.Livro;
import br.com.poher.biblioteca.domains.Usuario;
import br.com.poher.biblioteca.dto.LivroDTO;
import br.com.poher.biblioteca.repositorys.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	LivroRepository livroRepository;
	
	public List<Livro> findByTituloLike(String titulo){
		return livroRepository.findByTituloContaining(titulo);
	}
	
	public List<Livro> findAll() {
		List<Livro> listaLivros = livroRepository.findAll();
		
		return listaLivros;
	}
	
	public void saveAll(List<Livro> listaLivros ) {
		
		livroRepository.saveAll(listaLivros);
		
	}
	
	public Optional<Livro> findById(Integer id) {
		return livroRepository.findById(id);
	}
	
	public void deleteById(Integer id) {
		
		livroRepository.deleteById(id);
	}
	public void deleteAll() {
		livroRepository.deleteAll();
	}
	
	public Livro criaLivro(LivroDTO livroDTO, Usuario usuario) {
		return new Livro(null, livroDTO.getTitulo(), livroDTO.getDescricao() , livroDTO.getIsbn(), usuario);
	}
	
	public Livro atualizaLivro( Usuario usuario,Livro livro, LivroDTO livroDTO) {
		livro.setTitulo(livroDTO.getTitulo());
		livro.setDescricao(livroDTO.getDescricao());
		livro.setIsbn(livroDTO.getIsbn());
		livro.setUsuario(usuario);
		
		return livro;
		
	}
	
}
