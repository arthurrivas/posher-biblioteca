package br.com.poher.biblioteca.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.poher.biblioteca.domains.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	
	Usuario findByEmail(String email);

	
	
}
