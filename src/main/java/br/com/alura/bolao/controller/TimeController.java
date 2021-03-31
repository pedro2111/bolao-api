package br.com.alura.bolao.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.controller.dto.TimeDto;
import br.com.alura.bolao.controller.form.TimeFormDto;
import br.com.alura.bolao.modelo.Time;
import br.com.alura.bolao.repository.TimeRepository;
import br.com.alura.bolao.repository.UsuarioRepository;

@RestController
@RequestMapping("/times")
public class TimeController {

	@Autowired
	private TimeRepository timeRepo;

	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(Time.class);

	@GetMapping("/listarTodosTimes")
	@Transactional
	public List<TimeDto> listar(@RequestParam(required = false) String nome) {
		
		if(nome == null) { 
			List<Time> times = timeRepo.findAllByOrderByIdDesc();

			return TimeDto.toTimesDto(times, modelMapper); 

			
		}else {
			List<Time> times = timeRepo.findByNomeContainingIgnoreCase(nome);

			return TimeDto.toTimesDto(times, modelMapper);

			
		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<Time> cadastrar(@RequestBody TimeFormDto timeFormDto) {

		Time time = timeFormDto.convertToTime(usuarioRepo);

		Boolean existsTime = timeRepo.existsByNome(time.getNome());

		if (!existsTime) {

			timeRepo.save(time);

			return ResponseEntity.ok(time);

		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		timeRepo.deleteById(id);

		return ResponseEntity.ok().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Time> atualizar(@PathVariable Long id, @RequestBody TimeFormDto timeForm) {

		Time time = timeRepo.getOne(id);

		time.setNome(timeForm.getNome());

		return ResponseEntity.ok(time);
	}

}
