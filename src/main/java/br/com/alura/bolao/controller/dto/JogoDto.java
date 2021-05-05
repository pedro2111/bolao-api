package br.com.alura.bolao.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.com.alura.bolao.modelo.Jogo;
import br.com.alura.bolao.modelo.StatusJogo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JogoDto {
	
	private Long id;
	private String nomeTime1;
	private String nomeTime2;
	private String urlTime1;
	private String urlTime2;
	private Integer placarTime1;
	private Integer placarTime2;
	private LocalDateTime dtJogo;
	private String rodada;
	private StatusJogo status;
	private String local;
	
	public static List<JogoDto> convertToJogoDto (List<Jogo> jogos, ModelMapper modelMapper) {
		
		return jogos.stream().map(j -> modelMapper.map(j, JogoDto.class)).collect(Collectors.toList());		
		
	}
	
	public static JogoDto convertToUniqueJogo(Jogo jogo,ModelMapper modelMapper) {
		
		return modelMapper.map(jogo,JogoDto.class);
		
	}

}
