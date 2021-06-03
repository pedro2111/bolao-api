package br.com.alura.bolao.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.BolaoCriterio;
import br.com.alura.bolao.modelo.Campeonato;
import br.com.alura.bolao.modelo.Jogo;
import br.com.alura.bolao.modelo.Palpite;
import br.com.alura.bolao.modelo.PalpiteExtra;
import br.com.alura.bolao.modelo.StatusCampeonato;
import br.com.alura.bolao.modelo.TipoCriterio;
import br.com.alura.bolao.repository.BolaoCriterioRepository;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.CampeonatoRepository;
import br.com.alura.bolao.repository.JogoRepository;
import br.com.alura.bolao.repository.PalpiteExtraRepository;
import br.com.alura.bolao.repository.PalpiteRepository;

@Service
public class PalpiteService {

	private static final Logger logger = LoggerFactory.getLogger(PalpiteService.class);

	// 10 pontos para em cheio e 5 pontos para vencedor

	public void calcularPontosGanhos( PalpiteRepository palpRepo,  BolaoRepository bolaoRepo, JogoRepository jogoRepo, BolaoCriterioRepository bcRepo, Long bolao_id) {

		List<BolaoCriterio> criterios = null;
		Bolao bolao = null;
		
		bolao = bolaoRepo.getOne(bolao_id);
		logger.info("bolao id = " + bolao_id);

		criterios = bcRepo.findByBolao(bolao);

		List<Jogo> jogos = jogoRepo.findByCampeonatoJogoAndamento(bolao.getCampeonato());

		for (Jogo jogo : jogos) {
			logger.info("jogo id = " + jogo.getId());

			List<Palpite> palpitesJogo = palpRepo.findByJogoId(jogo,bolao);

			for (Palpite pj : palpitesJogo) {
				pj.setPontosGanho(0);

				for (BolaoCriterio bc : criterios) {

					if (bc.getCriterio().getNome().equals(TipoCriterio.PE)) { // placar exato - na lata
						if (pj.getPlacarTime1() == jogo.getPlacarTime1()
								&& pj.getPlacarTime2() == jogo.getPlacarTime2()) {
							pj.setPontosGanho(bc.getPontuacao());
							logger.info("PE = " + bc.getPontuacao());

						}

					} else if (bc.getCriterio().getNome().equals(TipoCriterio.RCG)) {// RESULTADO CERTO GOL - ACERTOU O
																						// VENCEDOR E UM DOS PLACARES
						if (!pe(jogo, pj) && rc(jogo, pj) && (jogo.getPlacarTime1() == pj.getPlacarTime1()
								|| jogo.getPlacarTime2() == pj.getPlacarTime2())) {
							pj.setPontosGanho(bc.getPontuacao());
							logger.info("RCG = " + bc.getPontuacao());

						}

					} else if (bc.getCriterio().getNome().equals(TipoCriterio.RC)) { //// RESULTADO CERTO - ACERTOU O
																						//// VENCEDOR OU EMPATE, MAS COM
																						//// PLACAR ERRADO
						if (!rcg(jogo, pj) && !pe(jogo, pj) && jogo.getPlacarTime1() > jogo.getPlacarTime2()
								&& pj.getPlacarTime1() > pj.getPlacarTime2()) {
							pj.setPontosGanho(bc.getPontuacao());
							logger.info("RC = " + bc.getPontuacao());

						} else if (!rcg(jogo, pj) && !pe(jogo, pj) && jogo.getPlacarTime2() > jogo.getPlacarTime1()
								&& pj.getPlacarTime2() > pj.getPlacarTime1()) {
							pj.setPontosGanho(bc.getPontuacao());
							logger.info("RC = " + bc.getPontuacao());

						} else if (!rcg(jogo, pj) && !pe(jogo, pj) && jogo.getPlacarTime1() == jogo.getPlacarTime2()
								&& pj.getPlacarTime1() == pj.getPlacarTime2()
								&& jogo.getPlacarTime1() != pj.getPlacarTime1()) { // acertou empate mas sem o placar
							pj.setPontosGanho(bc.getPontuacao());
							logger.info("RC = " + bc.getPontuacao());

						}

					} else if (bc.getCriterio().getNome().equals(TipoCriterio.GE)) { // gol errado - não vencedor mas
																						// acertou um dos placares
						if (!rcg(jogo, pj) && !rc(jogo, pj) && !pe(jogo, pj)
								&& (jogo.getPlacarTime1() == pj.getPlacarTime1()
										|| jogo.getPlacarTime2() == pj.getPlacarTime2())) {
							pj.setPontosGanho(bc.getPontuacao());
							logger.info("GE = " + bc.getPontuacao());

						}

					}
				}
				if (!rcg(jogo, pj) && !pe(jogo, pj) && !ge(jogo, pj) && !rc(jogo, pj)) {
					pj.setPontosGanho(0);

				}

				palpRepo.save(pj);

			}

		}

	}

	public boolean pe(Jogo jogo, Palpite pj) {
		if (pj.getPlacarTime1() == jogo.getPlacarTime1() && pj.getPlacarTime2() == jogo.getPlacarTime2()) {
			return true;

		} else {
			return false;
		}
	}

	public boolean rcg(Jogo jogo, Palpite pj) {
		if (!pe(jogo, pj) && rc(jogo, pj)
				&& (jogo.getPlacarTime1() == pj.getPlacarTime1() || jogo.getPlacarTime2() == pj.getPlacarTime2())) {
			return true;

		} else {
			return false;
		}
	}

	public boolean rc(Jogo jogo, Palpite pj) {
		if (jogo.getPlacarTime1() > jogo.getPlacarTime2() && pj.getPlacarTime1() > pj.getPlacarTime2()) {
			return true;

		} else if (jogo.getPlacarTime2() > jogo.getPlacarTime1() && pj.getPlacarTime2() > pj.getPlacarTime1()) {
			return true;

		} else if (jogo.getPlacarTime1() == jogo.getPlacarTime2() && pj.getPlacarTime1() == pj.getPlacarTime2()
				&& jogo.getPlacarTime1() != pj.getPlacarTime1()) { // acertou empate mas sem o placar
			return true;

		} else {
			return false;
		}

	}

	public boolean ge(Jogo jogo, Palpite pj) {
		if (!rcg(jogo, pj) && !rc(jogo, pj) && !pe(jogo, pj)
				&& (jogo.getPlacarTime1() == pj.getPlacarTime1() || jogo.getPlacarTime2() == pj.getPlacarTime2())) {
			return true;

		} else {
			return false;
		}
	}

	public void calcularPontosExtras(Bolao bolao, BolaoRepository bolaoRepo, PalpiteExtraRepository palpExtraRepo,BolaoCriterioRepository bcRepo, CampeonatoRepository campRepo) {

		Campeonato camp = campRepo.getOne(bolao.getCampeonato().getId());

		List<PalpiteExtra> palpites = palpExtraRepo.findByBolao(bolao.getId());

		List<BolaoCriterio> criterios = bcRepo.findByBolao(bolao);

		for (PalpiteExtra palp : palpites) {

			for (BolaoCriterio bc : criterios) {

				if (palp.getCriterio() == bc.getCriterio() && palp.getPalpiteExtraTime() == camp.getCampeao()) {
					palp.setPontosGanho(bc.getPontuacao());

				} else if (palp.getCriterio() == bc.getCriterio() && palp.getPalpiteExtraTime() == camp.getVice()) {
					palp.setPontosGanho(bc.getPontuacao());

				} else if (palp.getCriterio() == bc.getCriterio() && palp.getPalpiteExtraTime() == camp.getTerceiro()) {
					palp.setPontosGanho(bc.getPontuacao());

				} else if (palp.getCriterio() == bc.getCriterio() && palp.getPalpiteExtraTime() == camp.getQuarto()) {
					palp.setPontosGanho(bc.getPontuacao());

				}

			}

			palpExtraRepo.save(palp);

		}
		bolao.setCalculoPalpiteExtra(1); //flag para calcular apenas uma vez o palpite extra
		bolaoRepo.save(bolao);

	}

}
