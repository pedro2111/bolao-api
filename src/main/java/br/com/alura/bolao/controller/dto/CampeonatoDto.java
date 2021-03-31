package br.com.alura.bolao.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import br.com.alura.bolao.modelo.Campeonato;
import br.com.alura.bolao.modelo.StatusCampeonato;
import br.com.alura.bolao.modelo.Time;
import br.com.alura.bolao.repository.CampeonatoRepository;
import br.com.alura.bolao.repository.TimeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CampeonatoDto {
	
	Long id;
	String nome;
	Long idCriador;
	String nomeCriador;
	Long idCampeao;
	String nomeCampeao;
	Long idVice;
	String nomeVice;
	Long idTerceiro;
	String nomeTerceiro;
	Long idQuarto;
	String nomeQuarto;
	String url;
	StatusCampeonato status;
	List<Time> times = new ArrayList<Time>();
	
	
	
	
	public CampeonatoDto(Campeonato camp) {
		this.id = camp.getId();
		this.nome = camp.getNome();
		this.idCriador = camp.getCriador().getId() ;
		this.nomeCriador = camp.getCriador().getNome();
		this.idCampeao = camp.getCampeao().getId();
		this.nomeCampeao = (camp.getCampeao() == null) ? "na" : camp.getCampeao().getNome();
		this.idVice = camp.getVice().getId();
		this.nomeVice = (camp.getVice() == null) ? "na" : camp.getVice().getNome();
		this.idTerceiro = camp.getTerceiro().getId();
		this.nomeTerceiro = (camp.getTerceiro() == null) ? "na" : camp.getTerceiro().getNome();
		this.idQuarto = camp.getQuarto().getId();
		this.nomeQuarto = (camp.getQuarto() == null) ? "na" : camp.getQuarto().getNome();
		this.url = (camp.getUrl() == null) ? "na" : camp.getUrl();
		this.status = camp.getStatus();
		this.times  = camp.getTimes();
		
		
	}
	

	public static List<CampeonatoDto> toCampeonatoDto (List<Campeonato> campeonatos, ModelMapper modelMapper){
		
		return campeonatos.stream().map(camp -> modelMapper.map(camp,CampeonatoDto.class)).collect(Collectors.toList());
	}


	public static Page<CampeonatoDto> convertPaginacao(Page<Campeonato> campeonatos ) {
		
		
		return campeonatos.map(CampeonatoDto::new);
	}
	
	public static CampeonatoDto convertToCampeonato (Campeonato campeonato,ModelMapper modelMapper) {
		
		return modelMapper.map(campeonato,CampeonatoDto.class);
	}
	
	

}


