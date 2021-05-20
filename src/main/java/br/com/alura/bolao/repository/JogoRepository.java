package br.com.alura.bolao.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.Campeonato;
import br.com.alura.bolao.modelo.Jogo;
import br.com.alura.bolao.modelo.StatusJogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {


	List<Jogo> findByOrderByIdDesc();

	@Query("SELECT j FROM Jogo j WHERE j.status = :status ORDER BY j.dtJogo DESC")
	List<Jogo> findByStatus(@Param("status") StatusJogo status);

	@Query("SELECT j FROM Jogo j WHERE j.id = :id")
	Jogo findByJogoId(@Param("id") Long id);

	@Query("SELECT j FROM Jogo j WHERE j.campeonato = :campeonato AND j.status = 'ANDAMENTO' ")
	List<Jogo> findByCampeonatoJogoAndamento(@Param("campeonato") Campeonato campeonato);

	
	@Query("SELECT j FROM Jogo j WHERE j.status = :status and j.dtJogo = :dtJogo")
	List<Jogo> findByStatusDtjogo(@Param("status") StatusJogo status,@Param("dtJogo") LocalDateTime dtJogo);

	@Query("SELECT j FROM Jogo j WHERE j.campeonato.id = :campeonato and DATE(j.dtJogo) = :dataJogo")
	List<Jogo> findByDtjogo(@Param("campeonato")Long campeonato_id, @Param("dataJogo")LocalDateTime dataJogo);

	@Query("SELECT j FROM Jogo j WHERE j.campeonato.id = :campeonato_id and j.rodada = :rodada")
	List<Jogo> findByRodada(Long campeonato_id, String rodada);

	@Query("SELECT DISTINCT j.rodada FROM Jogo j WHERE j.campeonato.id = :campeonato_id ORDER BY j.rodada DESC")
	List<?> findRodadasCampeonato(Long campeonato_id);

	@Query(value = "select rodada from public.jogo where status != 'ENCERRADO' and campeonato_id = :campeonatoId order by dt_jogo asc limit 1", nativeQuery = true)
	String findRodadaAtual(@Param("campeonatoId")Long id);


}
