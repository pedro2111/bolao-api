package br.com.alura.bolao.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.com.alura.bolao.modelo.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeDto {

	private Long id;
	private String nome;
	private String nomeUsuario;
	private LocalDate dtCriacao;
	private String url;

	public TimeDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public static List<TimeDto> toTimesDto(List<Time> times, ModelMapper modelMapper) {

		return times.stream().map(t -> modelMapper.map(t, TimeDto.class)).collect(Collectors.toList());
	}
	
	public static TimeDto convertToTimeDto (Time time,ModelMapper modelMapper) {
		
		return modelMapper.map(time,TimeDto.class);
	}

	public static TimeDto montaDto(Object[] o) {

		TimeDto dto = new TimeDto();
		
		dto.setId(Long.parseLong(o[0].toString()));
		dto.setNome(o[1].toString());

		return dto;
	}

}
