package br.com.alura.bolao.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.controller.dto.PalpiteDto;
import br.com.alura.bolao.controller.form.PalpiteFormDto;
import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.Palpite;
import br.com.alura.bolao.repository.BolaoCriterioRepository;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.JogoRepository;
import br.com.alura.bolao.repository.PalpiteRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import br.com.alura.bolao.services.PalpiteService;

@RestController
@RequestMapping("/palpites")
public class PalpiteController {
	
	@Autowired
	private PalpiteRepository palpRepo;
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private JogoRepository jogoRepo;
	
	@Autowired
	private BolaoRepository bolaoRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PalpiteService palpService;
	
	@Autowired
	private BolaoCriterioRepository bcRepo;
	
	
	@GetMapping("/listarPalpitesBolao")
	public List<PalpiteDto> listarPalpitesBolao (@RequestParam("bolao") Long bolao_id){
		
		Bolao bolao = bolaoRepo.getOne(bolao_id);
		
		List<Palpite> palpites = palpRepo.findPalpiteByBolao(bolao);
		
		return PalpiteDto.convertToPalpiteDto(palpites, modelMapper);
		
	}
	
	@GetMapping("/listarPalpitesUsuarioBolao")
	public List<PalpiteDto> listarPalpitesUsuarioBolao (@RequestParam("bolao") Long bolao_id,@RequestParam("usuario") Long usuario_id){
		
				
		List<Palpite> palpites = palpRepo.findPalpiteByUsuarioBolao(bolao_id,usuario_id);
		
		return PalpiteDto.convertToPalpiteDto(palpites, modelMapper);
		
	}
	
	@PostMapping
	public ResponseEntity<Palpite> cadastrar(@RequestBody PalpiteFormDto palpiteForm){
		
		Palpite palpite = palpiteForm.convertToPalpite(bolaoRepo, userRepo, jogoRepo);
		
		palpRepo.save(palpite);
		
		return ResponseEntity.ok(palpite);
		
	}
	
	@PutMapping("/calcularPontosGanhos")
	public void calcularPontosGanhos(@RequestParam("bolao") Long bolao_id) {
		
		palpService.calcularPontosGanhos(palpRepo, bolaoRepo,jogoRepo,bcRepo, bolao_id);
		
	}

}




























