package br.com.alura.bolao.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingExtraDto {
	

	private int posicao;
	private String nome;
	private int totalpontosganho;
	private Integer campeao;
	private Integer vice;
	private Integer terceiro;
	private Integer quarto;
	
	
	public RankingExtraDto(int posicao, String nome, int totalpontosganho, Integer campeao, Integer vice, Integer terceiro, Integer quarto) {
		this.posicao = posicao;
		this.nome = nome;
		this.totalpontosganho = totalpontosganho;
		this.campeao = campeao;
		this.vice = vice;
		this.terceiro = terceiro;
		this.quarto = quarto;
	}
	
	
	public static RankingExtraDto montaDto (Object[] o) {
		
		RankingExtraDto dto = new RankingExtraDto();
		
		dto.setPosicao(Integer.parseInt(o[0].toString()));
		dto.setNome(o[1].toString());
		dto.setTotalpontosganho(Integer.parseInt(o[2].toString()));
		dto.setCampeao(Integer.parseInt(o[3].toString()));
		dto.setVice(Integer.parseInt(o[4].toString()));
		dto.setTerceiro(Integer.parseInt(o[5].toString()));
		dto.setQuarto(Integer.parseInt(o[6].toString()));
		
		return dto;
		
		
	}



}
