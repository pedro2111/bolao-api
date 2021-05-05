package br.com.alura.bolao.controller.form;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.Campeonato;
import br.com.alura.bolao.modelo.TipoBolao;
import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.CampeonatoRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BolaoFormDto {
	
	private Long usuario_id;
	private Long campeonato_id;
	private String tipoBolao; //pode ser livre ou privado
	private String nome;
	private String descricao;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dtLimitePalpiteExtra;
	
	
	public Bolao convertToBolao (UsuarioRepository userRepo,CampeonatoRepository campRepo) {
		
		Campeonato campeonato = campRepo.getOne(campeonato_id);
		Usuario usuario = userRepo.getOne(usuario_id);
		TipoBolao tipo = this.defineTipo(tipoBolao);
		
		return new Bolao(usuario,campeonato,tipo,nome,descricao);
		
		
	}
	
	public TipoBolao defineTipo(String tipo) {
		
		if(tipo.equals("LIVRE")) {
			return TipoBolao.LIVRE;
		}else {
			return TipoBolao.PRIVADO;
		}
		
	}

	public Bolao atualizar(Long id,BolaoRepository bolaoRepo,CampeonatoRepository campRepo) {
		
		Bolao bolao = bolaoRepo.getOne(id);
		Campeonato campeonato = campRepo.getOne(campeonato_id);
		TipoBolao tipo = this.defineTipo(tipoBolao);
		
		bolao.setCampeonato(campeonato);
		bolao.setNome(nome);
		bolao.setDescricao(descricao);
		bolao.setTipoBolao(tipo);
		
		bolaoRepo.save(bolao);
		
		return bolao;
		
	}
	public Bolao atualizarDtPalipiteExtra(Long id, BolaoRepository bolaoRepo) {
		
		Bolao bolao = bolaoRepo.getOne(id);
		
		
		bolao.setDtLimitePalpiteExtra(dtLimitePalpiteExtra);
		
		bolaoRepo.save(bolao);
		
		return bolao;
		
	}

}
