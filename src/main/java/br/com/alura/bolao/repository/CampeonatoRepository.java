package br.com.alura.bolao.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.bolao.modelo.Campeonato;
import br.com.alura.bolao.modelo.StatusCampeonato;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

	
	List<Campeonato> findAllByOrderByIdDesc();
	
	@Query("SELECT c FROM Campeonato c where c.status != 'ENCERRADO' ORDER BY c.id DESC ")
	List<Campeonato> findAllAtivos();


	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE Campeonato c SET c.campeao.id = :campeao_id, c.vice.id = :vice_id, c.terceiro.id = :terceiro_id, c.quarto.id = :quarto_id, c.status = :status WHERE  c.id = :id")
	void atualizarPalpiteExtra(@Param("id") Long id,@Param("campeao_id") Long campeao_id,@Param("vice_id") Long vice_id,@Param("terceiro_id") Long terceiro_id,@Param("quarto_id") Long quarto_id, @Param("status") StatusCampeonato status);


	@Query(value =  "SELECT t.id, nome\n" + 
			"FROM public.time t join public.campeonato_times ct on t.id = ct.times_id \n" + 
			"where ct.campeonato_id = :id and lower(t.nome) like lower(concat('%',:nomeTime,'%'))", nativeQuery = true)
	List<Object[]> findByNomeTime(@Param("id") Long id,@Param("nomeTime") String nomeTime);

	@Query(value =  "SELECT t.id, nome\n" + 
			"FROM public.time t join public.campeonato_times ct on t.id = ct.times_id \n" + 
			"where ct.campeonato_id = :id", nativeQuery = true)
	List<Object[]> findByNotNomeTime(@Param("id") Long id);


}
