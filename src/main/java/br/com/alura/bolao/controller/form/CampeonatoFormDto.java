package br.com.alura.bolao.controller.form;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.bolao.modelo.Campeonato;
import br.com.alura.bolao.modelo.StatusCampeonato;
import br.com.alura.bolao.modelo.Time;
import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.CampeonatoRepository;
import br.com.alura.bolao.repository.TimeRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CampeonatoFormDto {
	
	private Long id;
	private String nome;
	private Long criador_id;
	private Long campeao_id;
	private Long vice_id;
	private Long terceiro_id;
	private Long quarto_id;
	private List<Long> times_id = new ArrayList<Long>();
	
	
	public Campeonato convertToCampeonato (UsuarioRepository userRepo) {
		
		Usuario criador = userRepo.getOne(criador_id);
		
		return new Campeonato(nome, criador);
		
		
	}


	public void atualizar(Long id, CampeonatoRepository campRepo) {
		
		campRepo.atualizarPalpiteExtra(id,campeao_id,vice_id,terceiro_id,quarto_id, StatusCampeonato.ENCERRADO);
		

	}
	
	public Campeonato atualizarNome(Long id, CampeonatoRepository campRepo) {
		
		Campeonato campeonato = campRepo.getOne(id);
		
		campeonato.setNome(nome);
		
		campRepo.save(campeonato);
		
		return campeonato;
		
	}
	
	public void cadastrarTimes (CampeonatoRepository campRepo, TimeRepository timeRepo) {
		
		Campeonato campeonato = campRepo.getOne(id);
		
		List<Time> times  = new ArrayList<Time>();
		
		this.times_id.forEach(time_id -> {
			Time time = timeRepo.getOne(time_id);
			times.add(time);
			});
		
		campeonato.setTimes(times);
		
		campRepo.save(campeonato);
		
	}
	

}
