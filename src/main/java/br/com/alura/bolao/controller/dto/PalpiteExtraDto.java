package br.com.alura.bolao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.com.alura.bolao.modelo.PalpiteExtra;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PalpiteExtraDto {
	
	private Long id;
	private String nomeBolao;
	private String nomeUsuario;
	private String nomeCriterio;
	private Long idCriterio;
	private String nomeTime;
	private Long idTime;
	
	
	public static List<PalpiteExtraDto> convertToPaplpitesExtrasDto(List<PalpiteExtra> palpites,ModelMapper modelMapper) {
		
		return palpites.stream().map(p -> modelMapper.map(p,PalpiteExtraDto.class)).collect(Collectors.toList());
		
		
	}
	

}
