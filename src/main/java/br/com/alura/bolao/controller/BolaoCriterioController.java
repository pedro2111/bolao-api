package br.com.alura.bolao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.controller.form.BolaoCriterioFormDto;
import br.com.alura.bolao.modelo.BolaoCriterio;
import br.com.alura.bolao.repository.BolaoCriterioRepository;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.CriterioRepository;

@RestController
@RequestMapping("/boloes-criterios")
public class BolaoCriterioController {
	
	@Autowired
	BolaoRepository bolaoRepo;
	
	@Autowired
	BolaoCriterioRepository bcRepo;
	
	@Autowired
	CriterioRepository criterioRepo;
	
	
	@PostMapping
	public ResponseEntity<BolaoCriterio> cadastrar (@RequestBody BolaoCriterioFormDto bcForm){
		
		BolaoCriterio bc = bcForm.convertToBolaoCriterio(bolaoRepo,criterioRepo);
		
		bcRepo.save(bc);
		
		return ResponseEntity.ok(bc);
	}

}
