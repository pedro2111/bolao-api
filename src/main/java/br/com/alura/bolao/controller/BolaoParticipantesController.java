package br.com.alura.bolao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.controller.form.BolaoParticipantesFormDto;
import br.com.alura.bolao.modelo.BolaoParticipantes;
import br.com.alura.bolao.repository.BolaoParticipanteRepository;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.UsuarioRepository;

@RestController
@RequestMapping("/bolaoparticipantes")
public class BolaoParticipantesController {
	
	@Autowired
	BolaoParticipanteRepository bpRepo;
	
	@Autowired
	BolaoRepository bolaoRepo;
	
	@Autowired
	UsuarioRepository userRepo;
	
	@PostMapping
	public ResponseEntity<BolaoParticipantes> cadastrar(@RequestBody BolaoParticipantesFormDto bpForm){
		
		BolaoParticipantes bp = bpForm.convertToBolaoParticipante(bolaoRepo, userRepo);
		
		bpRepo.save(bp);	
		
		
		return ResponseEntity.ok(bp);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar (@RequestBody BolaoParticipantesFormDto bpForm){
		
		bpForm.aceitarParticipantes(bpRepo, bolaoRepo);
		
		return ResponseEntity.ok().build();
		
	}

}
