package br.com.alura.bolao.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.alura.bolao.modelo.BolaoCriterio;
import br.com.alura.bolao.modelo.TipoCriterio;

@Service
public class BolaoService {
	private static final Logger logger = LoggerFactory.getLogger(BolaoService.class);

	public List<Integer> setPontuacaoCriterios(List<BolaoCriterio> criterios) {
		
		List<Integer> pontuacoesCriterios = new ArrayList();

		for (BolaoCriterio bc : criterios) {

			if (bc.getCriterio().getNome().equals(TipoCriterio.PE)) {
				pontuacoesCriterios.add(bc.getPontuacao());
			
			} else if (bc.getCriterio().getNome().equals(TipoCriterio.RC)) {
				pontuacoesCriterios.add(bc.getPontuacao());
			
			} else if (bc.getCriterio().getNome().equals(TipoCriterio.RCG)) {
				pontuacoesCriterios.add(bc.getPontuacao());
			
			} else if (bc.getCriterio().getNome().equals(TipoCriterio.GE)) {
				pontuacoesCriterios.add(bc.getPontuacao());
			}
		}
		return pontuacoesCriterios;

	}

}
