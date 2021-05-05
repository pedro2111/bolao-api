package br.com.alura.bolao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.BolaoCriterio;

public interface BolaoCriterioRepository extends JpaRepository<BolaoCriterio, Long> {

	@Query("SELECT bc FROM BolaoCriterio bc WHERE bc.bolao.id = :bolao")
	List<BolaoCriterio> findByBolaoId(@Param("bolao") Long bolao);

	List<BolaoCriterio> findByBolao(Bolao bolao);

	@Query("SELECT bc FROM BolaoCriterio bc WHERE bc.bolao.id = :bolao AND bc.criterio.id = :criterio")
	Optional<BolaoCriterio> findByBolaoCriterio(@Param("bolao") Long bolao_id,@Param("criterio") Long criterio_id);

	

}
