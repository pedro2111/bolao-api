package br.com.alura.bolao.controller;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.controller.dto.BolaoCriterioDto;
import br.com.alura.bolao.controller.form.BolaoCriterioFormDto;
import br.com.alura.bolao.modelo.BolaoCriterio;
import br.com.alura.bolao.repository.BolaoCriterioRepository;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.CriterioRepository;
import org.springframework.cache.annotation.Cacheable;

@RestController
@RequestMapping("/boloes-criterios")
public class BolaoCriterioController {
	
	@Autowired
	BolaoRepository bolaoRepo;
	
	@Autowired
	BolaoCriterioRepository bcRepo;
	
	@Autowired
	CriterioRepository criterioRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@PostMapping
	public ResponseEntity<BolaoCriterio> cadastrar (@RequestBody BolaoCriterioFormDto bcForm){
		
		BolaoCriterio bc = bcForm.convertToBolaoCriterio(bolaoRepo,criterioRepo,bcRepo);
		
		bcRepo.save(bc);
		
		return ResponseEntity.ok(bc);
	}
	
	@GetMapping
	//@Cacheable(value = "criterios", key = "#bolao")
	public List<BolaoCriterioDto> listarByBolao (@RequestParam Long bolao) {
		
		List<BolaoCriterio> bc = bcRepo.findByBolaoId(bolao);
		
		return BolaoCriterioDto.convertToListBolaoDto(bc, modelMapper);
	}

}
