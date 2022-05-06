package br.com.alura.bolao.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.controller.dto.JogoDto;
import br.com.alura.bolao.controller.form.JogoFormDto;
import br.com.alura.bolao.modelo.Jogo;
import br.com.alura.bolao.modelo.StatusJogo;
import br.com.alura.bolao.repository.CampeonatoRepository;
import br.com.alura.bolao.repository.JogoRepository;
import br.com.alura.bolao.repository.TimeRepository;
import br.com.alura.bolao.repository.UsuarioRepository;

@RestController
@RequestMapping("/jogos")
public class JogoController {
	
	@Autowired
	private JogoRepository jogoRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TimeRepository timeRepo;
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private CampeonatoRepository campRepo;
	
	
	private static final Logger logger = LoggerFactory.getLogger(Jogo.class);
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	
	@GetMapping("/listar-data")
	@Transactional
	public  List<JogoDto> listarPorData (@RequestParam("campeonato") Long campeonato_id ,@RequestParam("data") String dtJogo){
		
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		LocalDateTime dataJogo = LocalDateTime.parse(dtJogo+" 00:00",formatter);
		
		
		if(dtJogo != null && campeonato_id !=null) {
			jogos = jogoRepo.findByDtjogo(campeonato_id,dataJogo);
		}
		
		return JogoDto.convertToJogoDto(jogos, modelMapper);
		
	}
	@GetMapping("/listar-rodadas-campeonato")
	@Transactional
	public  List<?> listarRodadasCampeonato (@RequestParam("campeonato") Long campeonato_id){
		
		List<?> rodadas = new ArrayList<Jogo>();
		
		
		if(campeonato_id !=null) {
			rodadas = jogoRepo.findRodadasCampeonato(campeonato_id);
		}
		
		return rodadas;
		
	}
	@GetMapping("/listar-rodada")
	@Transactional
	public  List<JogoDto> listarPorRodada (@RequestParam("campeonato") Long campeonato_id ,@RequestParam("rodada") String rodada){
		
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		
		if(rodada != null && campeonato_id !=null) {
			jogos = jogoRepo.findByRodada(campeonato_id,rodada);
		}
		
		return JogoDto.convertToJogoDto(jogos, modelMapper);
		
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Jogo> cadastrar(@RequestBody JogoFormDto jogoForm){
		
		Jogo jogo = jogoForm.convertToJogo(timeRepo, userRepo, campRepo);
				
		jogoRepo.save(jogo);
		
		return ResponseEntity.ok(jogo);
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id){
		
		jogoRepo.deleteById(id);
		
		return ResponseEntity.ok().build();
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<JogoDto> atualizar(@PathVariable Long id, @RequestBody JogoFormDto formJogo ){
		
		logger.info("id = " + id);
		
		Jogo jogoAtualizado = formJogo.atualizar(id, jogoRepo);
		
		jogoRepo.save(jogoAtualizado);
		
	
		
		return ResponseEntity.ok(JogoDto.convertToUniqueJogo(jogoAtualizado, modelMapper));
	} 
	
	@GetMapping("/campeonato/{id}/rodada-atual")
	public String listarRodadaAtual (@PathVariable Long id) {
		
		String rodadaAtual = jogoRepo.findRodadaAtual(id);
		
		return rodadaAtual;
	}
	
	@GetMapping("/campeonato/{id}/ultimo-jogo")
	public Long listarUltimoJogo(@PathVariable Long id) {
		
		Long idJogo = jogoRepo.findUltimoJogo(id);
		
		return idJogo;
		
	}	
	

}
