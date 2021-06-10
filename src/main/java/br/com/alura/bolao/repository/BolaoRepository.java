package br.com.alura.bolao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.PalpiteExtra;

public interface BolaoRepository extends JpaRepository<Bolao, Long> {
	
	@Query(value= "select ROW_NUMBER () OVER (order by sum (pontos_ganho) desc, \n" + 
			"count (*) filter (where pontos_ganho = :pe) desc,\n" + 
			"count (*) filter (where pontos_ganho = :rcg) desc,\n" + 
			"count (*) filter (where pontos_ganho = :rc) desc,\n" + 
			"count (*) filter (where pontos_ganho = :ge) desc) as posicao,\n" + 
			" u.id,\n" + 
			" u.nome,\n" + 
			" u.url,\n" + 
			" sum (pontos_ganho) as pontosganho,\n" + 
			" count (*) filter (where pontos_ganho = :pe) as PE,\n" + 
			" count (*) filter (where pontos_ganho = :rcg) as RCG,\n" + 
			" count (*) filter (where pontos_ganho = :rc) as RC,\n" + 
			" count (*) filter (where pontos_ganho = :ge) as GE\n" + 
			" from public.palpite p inner join public.usuario u on p.usuario_id = u.id\n" + 
			" where bolao_id = :bolao_id group by u.nome,u.id\n" + 
			"order by pontosganho desc, PE desc, RCG desc,RC desc,GE desc", nativeQuery = true)
	List<Object[]> findRanking(@Param("bolao_id") Long id, @Param("pe") Integer pe,@Param("rc") Integer rc,@Param("rcg") Integer rcg,@Param("ge") Integer ge);

	
	@Query(value = "select ROW_NUMBER () OVER () as posicao,\n" + 
			"u.nome , \n" + 
			"sum(pontos_ganho) as pontosganho,\n" + 
			"count (*) filter (where pontos_ganho = :campeao) as campeao,\n" + 
			"count (*) filter (where pontos_ganho = :vice) as vice,\n" + 
			"count (*) filter (where pontos_ganho = :terceiro) as terceiro,\n" + 
			"count (*) filter (where pontos_ganho = :quarto) as quarto\n" + 
			"FROM public.palpite_extra p inner join public.usuario u on p.usuario_id = u.id \n" + 
			"where bolao_id = :bolao_id group by u.nome \n" + 
			"order by 2 desc, 3 desc", nativeQuery = true)
	List<Object[]> findRankingExtra(@Param("bolao_id") Long id,@Param("campeao") Integer campeao,@Param("vice") Integer vice,@Param("terceiro") Integer terceiro,@Param("quarto") Integer quarto);


	@Query("SELECT b FROM Bolao b where lower(b.nome) like lower(concat('%',:nomeBolao,'%'))") 
	Page<Bolao> findByNome(@Param("nomeBolao") String nomeBolao, Pageable paginacao);

	@Query("SELECT b FROM Bolao b where b.campeonato.id = :campeonatoId") 
	List<Bolao> findBoloesByCampeonato(@Param("campeonatoId") Long id);



}
