package br.com.alura.bolao.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.alura.bolao.controller.dto.CampeonatoDto;
import br.com.alura.bolao.controller.dto.RankingDto;
import br.com.alura.bolao.controller.dto.TimeDto;
import br.com.alura.bolao.controller.form.CampeonatoFormDto;
import br.com.alura.bolao.modelo.Campeonato;
import br.com.alura.bolao.modelo.StatusCampeonato;
import br.com.alura.bolao.repository.CampeonatoRepository;
import br.com.alura.bolao.repository.TimeRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import br.com.alura.bolao.services.CloudinaryService;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

	@Autowired
	private CampeonatoRepository campRepo;

	@Autowired
	private UsuarioRepository userRepo;

	@Autowired
	private TimeRepository timeRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	CloudinaryService cloudService;

	@GetMapping("/listar-todos-campeonatos")
	@Transactional
	public List<CampeonatoDto> listarTodos() {
		
		List<Campeonato> campeonatos = campRepo.findAllByOrderByIdDesc();
		
		return CampeonatoDto.toCampeonatoDto(campeonatos, modelMapper);
	}
	
	@GetMapping("/listar-todos-campeonatos-ativos")
	@Transactional
	public List<CampeonatoDto> listarTodosAtivos() {

		List<Campeonato> campeonatos = campRepo.findAllAtivos();

		return CampeonatoDto.toCampeonatoDto(campeonatos, modelMapper);
	}

	@GetMapping("/{id}")
	@Transactional
	public CampeonatoDto listarCampeonato(@PathVariable Long id) {

		Campeonato campeonato = campRepo.getOne(id);

		return CampeonatoDto.convertToCampeonato(campeonato, modelMapper);

	}

	@GetMapping("/listar-campeonatos")
	@Transactional
	public Page<CampeonatoDto> listarPaginado(
			@PageableDefault(sort = "dtCriacao", direction = Direction.DESC) Pageable paginacao) {

		Page<Campeonato> campeonatos = campRepo.findAll(paginacao);

		return CampeonatoDto.convertPaginacao(campeonatos);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Campeonato> cadastrar(@RequestBody CampeonatoFormDto campFormDto) {

		Campeonato campeonato = campFormDto.convertToCampeonato(userRepo);

		campeonato.setStatus(StatusCampeonato.CRIADO);

		campRepo.save(campeonato);

		return ResponseEntity.ok(campeonato);

	}

	@PostMapping("/times")
	public ResponseEntity<?> cadastrarTimes(@RequestBody CampeonatoFormDto campFormDto) {

		campFormDto.cadastrarTimes(campRepo, timeRepo);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/upload-imagem")
	@Transactional
	public ResponseEntity<?> uploadImagem(@RequestParam("imagem") MultipartFile imagem, @RequestParam("campeonato_id") Long campeonato_id, @RequestParam("acao") String acao) throws IOException {

		Campeonato campeonato = campRepo.getOne(campeonato_id);

		if (acao.equals("editar") && !campeonato.getPublic_id().equals(null)) {

			cloudService.delete(campeonato.getPublic_id());
		}

		BufferedImage bi = ImageIO.read(imagem.getInputStream());

		if (bi == null) {

			return ResponseEntity.badRequest().body("imagem inválida");
		}

		Map result = cloudService.upload(imagem, "bolao/campeonatos");

		campeonato.setPublic_id((String) result.get("public_id"));
		campeonato.setUrl((String) result.get("url"));

		campRepo.save(campeonato);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		campRepo.deleteById(id);

		return ResponseEntity.ok().build();

	}

	@PutMapping("/{id}/atualizar-palpite-extra")
	public ResponseEntity<?> atualizarPalpiteExtra(@PathVariable Long id, @RequestBody CampeonatoFormDto campForm) {

		campForm.atualizar(id, campRepo);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Campeonato> atualizar(@PathVariable Long id, @RequestBody CampeonatoFormDto campForm) {

		Campeonato campAtualizado = campForm.atualizarNome(id, campRepo);

		return ResponseEntity.ok(campAtualizado);
	}

}
