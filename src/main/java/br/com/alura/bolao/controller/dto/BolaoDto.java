package br.com.alura.bolao.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.TipoBolao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BolaoDto {
	
	private Long id;
	private Long idCriador;
	private String nomeCriador;
	private Long idCampeonato;
	private String nomeCampeonato;
	private String urlCampeonato;
	private String nome;
	private String descricao;
	private LocalDateTime dtLimitePalpiteExtra;
	private LocalDate dtCriacao;
	private TipoBolao tipoBolao;
	private String url;
	
	
	public BolaoDto(Bolao bolao) {
		this.nomeCriador = bolao.getCriador().getNome();
		this.nomeCampeonato = bolao.getCampeonato().getNome();
		this.urlCampeonato = bolao.getCampeonato().getUrl();
		this.nome = bolao.getNome();
		this.descricao = bolao.getDescricao();
		this.dtLimitePalpiteExtra = bolao.getDtLimitePalpiteExtra();
		this.dtCriacao = bolao.getDtCriacao();
		this.tipoBolao = bolao.getTipoBolao();
		this.url = (bolao.getUrl() == null) ? "na" : bolao.getUrl();
	}
	
	public static List<BolaoDto> convertToBolaoDto (List<Bolao> boloes, ModelMapper modelMapper){
		
		return boloes.stream().map(b -> modelMapper.map(b,BolaoDto.class)).collect(Collectors.toList());
		
	}

	public static Page<BolaoDto> convertPageToBolaoDto(Page<Bolao> boloes) {
		
		return boloes.map(BolaoDto::new);
	}
	
	public static BolaoDto convertUniqueBolaoDto(Bolao bolao, ModelMapper modelMapper) {
				
		return modelMapper.map(bolao,BolaoDto.class);
	}

	
	
	

}
