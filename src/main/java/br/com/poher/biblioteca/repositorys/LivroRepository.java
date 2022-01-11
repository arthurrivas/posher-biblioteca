package br.com.poher.biblioteca.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.poher.biblioteca.domains.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
	
	
	List<Livro> findByTituloContaining(String titulo);
}
