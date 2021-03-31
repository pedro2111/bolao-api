package br.com.alura.bolao.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingDto {

	private int posicao;
	private String nome;
	private int totalpontosganho;
	private Integer pe;
	private Integer rcg;
	private Integer rc;
	private Integer ge;
	
	
	public RankingDto(int posicao, String nome, int totalpontosganho, Integer pe, Integer rcg, Integer rc, Integer ge) {
		this.posicao = posicao;
		this.nome = nome;
		this.totalpontosganho = totalpontosganho;
		this.pe = pe;
		this.rcg = rcg;
		this.rc = rc;
		this.ge = ge;
	}
	
	
	public static RankingDto montaDto (Object[] o) {
		
		RankingDto dto = new RankingDto();
		
		dto.setPosicao(Integer.parseInt(o[0].toString()));
		dto.setNome(o[1].toString());
		dto.setTotalpontosganho(Integer.parseInt(o[2].toString()));
		dto.setPe(Integer.parseInt(o[3].toString()));
		dto.setRcg(Integer.parseInt(o[4].toString()));
		dto.setRc(Integer.parseInt(o[5].toString()));
		dto.setGe(Integer.parseInt(o[6].toString()));
		
		return dto;
		
		
	}
	
	
}
