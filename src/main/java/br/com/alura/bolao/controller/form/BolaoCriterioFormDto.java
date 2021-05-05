package br.com.alura.bolao.controller.form;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.bolao.controller.BolaoController;
import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.BolaoCriterio;
import br.com.alura.bolao.modelo.Criterio;
import br.com.alura.bolao.repository.BolaoCriterioRepository;
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
	
	private static final Logger logger = LoggerFactory.getLogger(BolaoCriterioFormDto.class);

	public BolaoCriterio convertToBolaoCriterio(BolaoRepository bolaoRepo, CriterioRepository criterioRepo, BolaoCriterioRepository bcRepo) {
		
		Bolao bolao = bolaoRepo.getOne(bolao_id);
		Criterio criterio = criterioRepo.getOne(criterio_id);
		
		Optional<BolaoCriterio> bc = bcRepo.findByBolaoCriterio(bolao_id,criterio_id);
		
		if(bc.isPresent()) {
			bc.get().setPontuacao(pontuacao);
			return bc.get();
			
		}else {
			return new BolaoCriterio(bolao,criterio,pontuacao);
		}
		
	
	}

}
