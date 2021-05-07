package br.com.alura.bolao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.com.alura.bolao.modelo.BolaoParticipantes;
import br.com.alura.bolao.modelo.StatusParticipante;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BolaoParticipantesDto {
	
	private Long id;
	private Long idBolao;
	private Long idParticipante;
	private String nomeParticipante;
	private StatusParticipante status;
	
	
	public static List<BolaoParticipantesDto> convertToListBolaoParticipantesDto (List<BolaoParticipantes> listBp,ModelMapper modelMapper){
		
		
		return listBp.stream().map(bp -> modelMapper.map(bp,BolaoParticipantesDto.class)).collect(Collectors.toList());
	}
}
