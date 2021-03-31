package br.com.alura.bolao.controller.form;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.Jogo;
import br.com.alura.bolao.modelo.Palpite;
import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.JogoRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PalpiteFormDto {
	
	private Long bolao_id;
	private Long jogo_id;
	private Long usuario_id;
	private Integer placarTime1;
	private Integer placarTime2;
	private Integer pontosGanho;
	
	
	public Palpite convertToPalpite (BolaoRepository bolaoRepo, UsuarioRepository userRepo, JogoRepository jogoRepo) {
		
		Bolao bolao  = bolaoRepo.getOne(bolao_id);
		Usuario usuario = userRepo.getOne(usuario_id);
		Jogo jogo = jogoRepo.getOne(jogo_id);
		
		return new Palpite(bolao,jogo,usuario,placarTime1,placarTime2);
	}
	

}
