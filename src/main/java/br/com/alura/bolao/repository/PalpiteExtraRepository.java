package br.com.alura.bolao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.bolao.modelo.PalpiteExtra;

public interface PalpiteExtraRepository extends JpaRepository<PalpiteExtra,Long> {

	@Query("SELECT p FROM PalpiteExtra p where p.bolao.id = :bolao_id order by p.usuario.nome asc ")
	List<PalpiteExtra> findByBolao(@Param("bolao_id") Long id);

	@Query("SELECT p FROM PalpiteExtra p where p.bolao.id = :bolao_id AND p.usuario.id = :usuarioId order by p.criterio.id asc ")
	List<PalpiteExtra> findByBolaoUsuario(@Param("bolao_id") Long id,@Param("usuarioId") Long usuarioId);

}
