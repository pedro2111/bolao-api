package br.com.alura.bolao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.BolaoCriterio;

public interface BolaoCriterioRepository extends JpaRepository<BolaoCriterio, Long> {

	List<BolaoCriterio> findByBolao(Bolao bolao);

	

}
