package br.com.alura.bolao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.com.alura.bolao.modelo.BolaoCriterio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BolaoCriterioDto {
	
	private Long id;
	private Long idBolao;
	private String nomeBolao;
	private Long idCriterio;
	private String nomeCriterio;
	private Integer pontuacao;
	
	public static List<BolaoCriterioDto> convertToListBolaoDto (List<BolaoCriterio> bc, ModelMapper modelMapper){
		
		return bc.stream().map(b -> modelMapper.map(b,BolaoCriterioDto.class)).collect(Collectors.toList());
		
	}
	

}
