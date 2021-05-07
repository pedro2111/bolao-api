package br.com.alura.bolao.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.controller.dto.BolaoParticipantesDto;
import br.com.alura.bolao.controller.form.BolaoParticipantesFormDto;
import br.com.alura.bolao.modelo.BolaoParticipantes;
import br.com.alura.bolao.modelo.StatusParticipante;
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
	
	@Autowired
	ModelMapper modelMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(BolaoParticipantesController.class); 
	
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
	@PutMapping("/ativar-toggle/participante/{id}")
	public ResponseEntity<?> atualizarStatusParticipante (@PathVariable Long id){
		
		BolaoParticipantes bp =  bpRepo.getOne(id);
		
		if(bp.getStatus().equals(StatusParticipante.ATIVO) ) {
						
			bp.setStatus(StatusParticipante.INATIVO);
			
		}else {
			bp.setStatus(StatusParticipante.ATIVO);
			
			
		}
		
		bpRepo.save(bp);
		
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/{id}")
	public List<BolaoParticipantesDto> listarParticipantesBolao (@PathVariable Long id) {
		
				
		List<BolaoParticipantes> bp = bpRepo.findParticipantesByBolao(id);
		
		return BolaoParticipantesDto.convertToListBolaoParticipantesDto(bp, modelMapper);
	}

}














