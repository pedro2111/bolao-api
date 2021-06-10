package br.com.alura.bolao.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingDto {

	private int posicao;
	private Long id;
	private String nome;
	private String url;
	private int totalpontosganho;
	private Integer pe;
	private Integer rcg;
	private Integer rc;
	private Integer ge;
	
	
	public RankingDto(int posicao, Long id, String nome,String url, int totalpontosganho, Integer pe, Integer rcg, Integer rc, Integer ge) {
		this.posicao = posicao;
		this.id = id;
		this.nome = nome;
		this.url = url;
		this.totalpontosganho = totalpontosganho;
		this.pe = pe;
		this.rcg = rcg;
		this.rc = rc;
		this.ge = ge;
	}
	
	
	public static RankingDto montaDto (Object[] o) {
		
		RankingDto dto = new RankingDto();
		
		dto.setPosicao(Integer.parseInt(o[0].toString()));
		dto.setId(Long.parseLong(o[1].toString()));
		dto.setNome(o[2].toString());
		if(o[3] != null) {
			dto.setUrl(o[3].toString());
			
		}else {
			dto.setUrl("na");
			
		}
		 
		
		dto.setTotalpontosganho(Integer.parseInt(o[4].toString()));
		dto.setPe(Integer.parseInt(o[5].toString()));
		dto.setRcg(Integer.parseInt(o[6].toString()));
		dto.setRc(Integer.parseInt(o[7].toString()));
		dto.setGe(Integer.parseInt(o[8].toString()));
		
		return dto;
		
		
	}
	
	
}
