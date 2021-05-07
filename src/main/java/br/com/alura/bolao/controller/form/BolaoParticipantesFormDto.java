package br.com.alura.bolao.controller.form;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.BolaoParticipantes;
import br.com.alura.bolao.modelo.StatusParticipante;
import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.BolaoParticipanteRepository;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BolaoParticipantesFormDto {
	
	private Long bolao_id;
	private List<Long> participante_ids = new ArrayList<Long>();
	
	public void aceitarParticipantes(BolaoParticipanteRepository bpRepo, BolaoRepository bolaoRepo) {
		
		Bolao bolao = bolaoRepo.getOne(bolao_id);
		
		bpRepo.aceitarParticipantes(bolao,participante_ids);
		
	}
	
	public BolaoParticipantes convertToBolaoParticipante(BolaoRepository bolaoRepo, UsuarioRepository userRepo) {
		
		Bolao bolao = bolaoRepo.getOne(bolao_id);
		Usuario participante = userRepo.getOne(participante_ids.get(0)); //aproveita a parte do array de ids. Usado quando o participante cliclar em participar bolão
		
		return new BolaoParticipantes(participante, bolao, StatusParticipante.INATIVO);
		
		
	}

}
