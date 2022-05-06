package br.com.alura.bolao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.com.alura.bolao.modelo.Palpite;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PalpiteDto {
	
	private Long id;
	private Long idJogo;
	private Long idBolao;
	private Long idUsuario;
	private String nomeTime1Jogo;
	private String urlTime1Jogo;
	private String nomeTime2Jogo;
	private String urlTime2Jogo;
	private String nomeUsuario;
	private Integer placarTime1;
	private Integer placarTime1Jogo;
	private Integer placarTime2;
	private Integer placarTime2Jogo;
	private Integer pontosGanho = 0;
	
	
	public static List<PalpiteDto> convertToPalpiteDto (List<Palpite> palpites, ModelMapper modelMapper){
		
		return palpites.stream().map(p -> modelMapper.map(p, PalpiteDto.class)).collect(Collectors.toList());		
		
	}

}
