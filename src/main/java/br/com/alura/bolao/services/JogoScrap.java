package br.com.alura.bolao.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.Campeonato;
import br.com.alura.bolao.modelo.Jogo;
import br.com.alura.bolao.modelo.JogoScraping;
import br.com.alura.bolao.repository.JogoRepository;

@Service
public class JogoScrap {
	/*
	@Autowired
	private JogoRepository jogoRepo;
	
	
	public void populaResultadosJogos(Campeonato campeonato, LocalDateTime dataJogo) {
		
		List<JogoScraping> jogosScrap = this.getResultadosJogos();
		List<Jogo> jogos = jogoRepo.findByDtJogo(campeonato,dataJogo);
		
	}

	public List<JogoScraping> getResultadosJogos() {

	final String url = "https://www.cbf.com.br/selecao-brasileira/jogos/selecao-base-masculina";

	List<JogoScraping> jogos = new ArrayList<JogoScraping>();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

	try
	{
		final Document document = Jsoup.connect(url).get();
		int count = 1;
		JogoScraping jogoScraping = new JogoScraping();

		// System.out.println(document.outerHtml());

		for (Element row : document.select("div.box:nth-of-type(n) div.m-b-10, div.box:nth-of-type(n) div.text-2, div.box:nth-of-type(n) .text-3 b")) {

			if (row.text().length() > 0) {

				String elemento = row.text();
				// System.out.println(elemento);

				if (count == 1) {
					String dataJogo = elemento.substring(5, 15);
					LocalDate dtJogo = LocalDate.parse(dataJogo, formatter);

					jogoScraping.setDtjogo(dtJogo);

				} else if (count == 2) {

					jogoScraping.setTimeCasa(elemento);

				} else if (count == 3) {
					// placar time casa

					if (elemento.substring(0, 1).equals("(")) {
						jogoScraping.setPlacarTimeCasa(Integer.parseInt(elemento.substring(3, 4)));
						jogoScraping.setPlacarTimefora(Integer.parseInt(elemento.substring(7, 8)));

					} else if (elemento.substring(2, 3).equals("(")) {
						jogoScraping.setPlacarTimefora(Integer.parseInt(elemento.substring(8, 9)));
						// System.out.println(elemento);
						// System.out.println(elemento.substring(8,9));

					} else if (elemento.substring(4, 5).equals("x")) {
						jogoScraping.setPlacarTimeCasa(Integer.parseInt(elemento.substring(0, 1))
								+ Integer.parseInt(elemento.substring(2, 3)));
						jogoScraping.setPlacarTimefora(Integer.parseInt(elemento.substring(6, 7))
								+ Integer.parseInt(elemento.substring(8, 9)));

					}

					else {
						// System.out.println(elemento);
						jogoScraping.setPlacarTimeCasa(Integer.parseInt(elemento.substring(0, 1)));
						jogoScraping.setPlacarTimefora(Integer.parseInt(elemento.substring(4, 5)));
					}

				} else {
					jogoScraping.setTimeFora(elemento);
					jogos.add(jogoScraping);

				}

			}

			if (count % 4 == 0) {
				count = 1;
				jogoScraping = new JogoScraping();
			} else {
				count++;
			}

		}
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return jogos;
	


}
*/
}

