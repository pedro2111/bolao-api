package br.com.alura.bolao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.Jogo;
import br.com.alura.bolao.modelo.Palpite;

public interface PalpiteRepository extends JpaRepository<Palpite, Long> {

	@Query("SELECT p FROM Palpite p WHERE p.bolao = :bolao")
	List<Palpite> findPalpiteByBolao(@Param("bolao") Bolao bolao);

	@Query("SELECT p FROM Palpite p WHERE p.bolao  = :bolao AND p.jogo.status = 'ANDAMENTO' ")
	List<Palpite> findPalpiteByBolaoJogoAndamento(@Param("bolao") Bolao bolao);

	@Query("SELECT p FROM Palpite p WHERE p.jogo = :jogo")
	List<Palpite> findByJogoId(@Param("jogo") Jogo jogo);

	@Query("SELECT p FROM Palpite p WHERE p.bolao.id = :bolaoId AND p.usuario.id = :usuarioId")
	List<Palpite> findPalpiteByUsuarioBolao(@Param("bolaoId")Long bolao_id, @Param("usuarioId")Long usuario_id);

	
	

}
