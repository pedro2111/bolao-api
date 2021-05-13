package br.com.alura.bolao.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.BolaoParticipantes;

public interface BolaoParticipanteRepository extends JpaRepository<BolaoParticipantes, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE BolaoParticipantes bp SET bp.status = 'ATIVO' WHERE  bp.bolao = :bolao and bp.participante.id IN :listaIds")
	void aceitarParticipantes(@Param("bolao") Bolao bolao, @Param("listaIds") List<Long> participante_ids);

	@Query("SELECT bp FROM BolaoParticipantes bp WHERE bp.bolao.id = :bolao_id")
	List<BolaoParticipantes> findParticipantesByBolao(@Param("bolao_id") Long id);

	@Query("SELECT bp FROM BolaoParticipantes bp WHERE bp.participante.id = :participante_id")
	List<BolaoParticipantes> findBoloesByParticipante(@Param("participante_id") Long id);

}
