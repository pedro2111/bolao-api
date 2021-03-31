package br.com.alura.bolao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.bolao.modelo.Time;

public interface TimeRepository extends JpaRepository<Time, Long> {

	List<Time> findAllByOrderByIdDesc();

	@Query("select count(t)>0 from Time t where t.nome = :nome")
	Boolean existsByNome(@Param("nome") String nome);


	List<Time> findByNomeContainingIgnoreCase(String nome);


	
	

}
