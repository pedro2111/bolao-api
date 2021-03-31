package br.com.alura.bolao.controller.form;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.BolaoCriterio;
import br.com.alura.bolao.modelo.Criterio;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.CriterioRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BolaoCriterioFormDto {
	
	private Long bolao_id;
	private Long criterio_id;
	private Integer pontuacao;

	public BolaoCriterio convertToBolaoCriterio(BolaoRepository bolaoRepo, CriterioRepository criterioRepo) {
		
		Bolao bolao = bolaoRepo.getOne(bolao_id);
		Criterio criterio = criterioRepo.getOne(criterio_id);
		
		return new BolaoCriterio(bolao,criterio,pontuacao);
	}

}
