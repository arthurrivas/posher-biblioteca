package br.com.poher.biblioteca.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.poher.biblioteca.domains.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
	
}
