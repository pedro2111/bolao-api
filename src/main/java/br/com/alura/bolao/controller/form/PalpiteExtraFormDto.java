package br.com.alura.bolao.controller.form;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.Criterio;
import br.com.alura.bolao.modelo.PalpiteExtra;
import br.com.alura.bolao.modelo.Time;
import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.CriterioRepository;
import br.com.alura.bolao.repository.TimeRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class PalpiteExtraFormDto {
	
	private Long bolao_id;
	private Long usuario_id;
	private Long criterio_id;
	private Long time_id;

	public PalpiteExtra convertToPalpiteExtra(BolaoRepository bolaoRepo, UsuarioRepository userRepo,CriterioRepository criterioRepo, TimeRepository timeRepo) {
		
		Bolao bolao = bolaoRepo.getOne(bolao_id);
		Usuario usuario = userRepo.getOne(usuario_id);
		Criterio criterio = criterioRepo.getOne(criterio_id);
		Time time = timeRepo.getOne(time_id);
		
		return new PalpiteExtra(bolao,usuario,criterio,time);
	}
	

}
