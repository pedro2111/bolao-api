package br.com.alura.bolao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.bolao.modelo.Criterio;
import br.com.alura.bolao.modelo.TipoCriterio;

public interface CriterioRepository extends JpaRepository<Criterio, Long>{

	Optional<Criterio> findByNome(TipoCriterio nome);
	
	

}
