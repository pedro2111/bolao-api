package br.com.alura.bolao.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.controller.dto.PalpiteExtraDto;
import br.com.alura.bolao.controller.form.PalpiteExtraFormDto;
import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.PalpiteExtra;
import br.com.alura.bolao.repository.BolaoCriterioRepository;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.CampeonatoRepository;
import br.com.alura.bolao.repository.CriterioRepository;
import br.com.alura.bolao.repository.PalpiteExtraRepository;
import br.com.alura.bolao.repository.TimeRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import br.com.alura.bolao.services.PalpiteService;

@RestController
@RequestMapping("/palpites-extras")
public class PalpiteExtraController {
	
	@Autowired
	BolaoRepository bolaoRepo;
	
	@Autowired
	UsuarioRepository userRepo;
	
	@Autowired
	CriterioRepository criterioRepo;
	
	@Autowired
	TimeRepository timeRepo;
	
	@Autowired
	PalpiteExtraRepository palpExtraRepo;
	
	@Autowired
	PalpiteService palpService;
	
	@Autowired
	BolaoCriterioRepository bcRepo;
	
	@Autowired
	CampeonatoRepository campRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody PalpiteExtraFormDto palpFormDto){
		
		PalpiteExtra palpiteExtra = palpFormDto.convertToPalpiteExtra(bolaoRepo, userRepo, criterioRepo, timeRepo);
		
		palpExtraRepo.save(palpiteExtra);
		
		return ResponseEntity.ok(palpiteExtra);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<PalpiteExtra> atualizar(@RequestBody PalpiteExtraFormDto palpFormDto){
		
		palpFormDto.atualizarPalpite(palpExtraRepo,timeRepo);
		
		
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/bolao/{id}")
	public List<PalpiteExtraDto> listarPalpitesExtrasBolao (@PathVariable Long id,@RequestParam(required = false) Long usuarioId){
		
		if(usuarioId == null) {
			List<PalpiteExtra> palpites = palpExtraRepo.findByBolao(id);
			
			return PalpiteExtraDto.convertToPaplpitesExtrasDto(palpites, modelMapper);
			
		}else {
			
			List<PalpiteExtra> palpites = palpExtraRepo.findByBolaoUsuario(id,usuarioId);
			
			return PalpiteExtraDto.convertToPaplpitesExtrasDto(palpites, modelMapper);
			
		}
		
	}
	
	@PutMapping("/calcular-pontos-extras")
	@CacheEvict(value = {"rankingExtra"}, allEntries=true, key = "#bolao_id")
	public ResponseEntity<?> calcularPontosGanhos(@RequestParam("bolao") Long bolao_id) {
		
		Bolao bolao = bolaoRepo.getOne(bolao_id);
		
		if(bolao.getCalculoPalpiteExtra() == 0 ) {
			palpService.calcularPontosExtras(bolao, bolaoRepo, palpExtraRepo, bcRepo, campRepo);
			
		}
		
		return ResponseEntity.ok().build();
		
		
		
	}

}














