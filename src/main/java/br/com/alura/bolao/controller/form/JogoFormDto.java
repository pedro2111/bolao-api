package br.com.alura.bolao.controller.form;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.bolao.modelo.Campeonato;
import br.com.alura.bolao.modelo.Jogo;
import br.com.alura.bolao.modelo.StatusJogo;
import br.com.alura.bolao.modelo.Time;
import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.CampeonatoRepository;
import br.com.alura.bolao.repository.JogoRepository;
import br.com.alura.bolao.repository.TimeRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JogoFormDto {
	
	private static final Logger logger = LoggerFactory.getLogger(JogoFormDto.class);

	
	private Long time1_id;
	private Long time2_id;
	private Long campeonato_id;
	private Long criador_id;
	private String local;
	private String rodada;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dtJogo;
	private Integer placarTime1;
	private Integer placarTime2;
	private StatusJogo status;
	
	public Jogo convertToJogo (TimeRepository timeRepo, UsuarioRepository userRepo, CampeonatoRepository campRepo) {
		
		Time time1 = timeRepo.getOne(time1_id);
		Time time2 = timeRepo.getOne(time2_id);
		Usuario criador = userRepo.getOne(criador_id);
		Campeonato campeonato = campRepo.getOne(campeonato_id);
		
		return new Jogo(campeonato,time1, time2, dtJogo, criador, rodada,local);
		
	}
	
	public Jogo atualizar(Long id, JogoRepository jogoRepo) {
		
	
		Jogo jogo = jogoRepo.getOne(id);
		
		jogo.setPlacarTime1(placarTime1);
		jogo.setPlacarTime2(placarTime2);
		jogo.setStatus(status);
		
		return jogo;
		
	}

}
