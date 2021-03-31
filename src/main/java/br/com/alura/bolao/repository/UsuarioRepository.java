package br.com.alura.bolao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.bolao.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByNome(String nome);
	
	
	Optional<Usuario> findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
	Usuario listarPorNomeUsuario(String nome);
	
	

}
