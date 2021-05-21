package br.com.alura.bolao.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.alura.bolao.controller.dto.BolaoDto;
import br.com.alura.bolao.controller.dto.RankingDto;
import br.com.alura.bolao.controller.dto.RankingExtraDto;
import br.com.alura.bolao.controller.form.BolaoFormDto;
import br.com.alura.bolao.controller.form.TimeFormDto;
import br.com.alura.bolao.modelo.Bolao;
import br.com.alura.bolao.modelo.BolaoCriterio;
import br.com.alura.bolao.modelo.BolaoParticipantes;
import br.com.alura.bolao.modelo.Criterio;
import br.com.alura.bolao.modelo.StatusParticipante;
import br.com.alura.bolao.modelo.Time;
import br.com.alura.bolao.modelo.TipoCriterio;
import br.com.alura.bolao.repository.BolaoCriterioRepository;
import br.com.alura.bolao.repository.BolaoParticipanteRepository;
import br.com.alura.bolao.repository.BolaoRepository;
import br.com.alura.bolao.repository.CampeonatoRepository;
import br.com.alura.bolao.repository.CriterioRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import br.com.alura.bolao.services.BolaoService;
import br.com.alura.bolao.services.CloudinaryService;

@RestController
@RequestMapping("/boloes")
public class BolaoController {
	
	@Autowired
	private CampeonatoRepository campRepo;
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private BolaoRepository bolaoRepo;
	
	@Autowired
	private CriterioRepository criterioRepo;
	
	@Autowired
	private BolaoCriterioRepository bcRepo;
	
	@Autowired
	private BolaoParticipanteRepository bpRepo;
	
	@Autowired
	private BolaoService bolaoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CloudinaryService cloudService;
	
	private Integer pe = -1;
	private Integer rc = -1;
	private Integer rcg = -1;
	private Integer ge = -1;
	
	private static final Logger logger = LoggerFactory.getLogger(BolaoController.class); 
	
	@PostMapping
	@Transactional
	public ResponseEntity<Bolao> cadastrar (@RequestBody BolaoFormDto bolaoForm){
		
		Bolao bolao = bolaoForm.convertToBolao(userRepo, campRepo);
		
		BolaoParticipantes bp = new BolaoParticipantes(bolao.getCriador(), bolao, StatusParticipante.ATIVO);
		
		
		Optional<Criterio> pe = criterioRepo.findByNome(TipoCriterio.PE);
		Optional<Criterio> rc = criterioRepo.findByNome(TipoCriterio.RC);
		
		BolaoCriterio placarExato = new BolaoCriterio(bolao, pe.get(), 3);
		BolaoCriterio resultadoCerto = new BolaoCriterio(bolao, rc.get(), 1);
		
		bolaoRepo.save(bolao);
		
		bcRepo.save(placarExato);
		bcRepo.save(resultadoCerto);
		
		bpRepo.save(bp);		
		
		return ResponseEntity.ok(bolao);
			
	}
	
	@PutMapping("/upload-imagem")
	@Transactional
	public ResponseEntity<?> uploadImagem(@RequestParam("imagem") MultipartFile imagem,@RequestParam("bolao_id") Long bolao_id,@RequestParam("acao") String acao) throws IOException{
		
		Bolao bolao = bolaoRepo.getOne(bolao_id);
		
		if (acao.equals("editar") && !bolao.getPublic_id().equals(null)) {

			cloudService.delete(bolao.getPublic_id());
		} 
		
		BufferedImage bi = ImageIO.read(imagem.getInputStream());
		
		if(bi == null) {
			
			return ResponseEntity.badRequest().body("imagem inválida");
		}
		
		Map result = cloudService.upload(imagem, "bolao/boloes");
		
		bolao.setPublic_id((String)result.get("public_id"));
		bolao.setUrl((String)result.get("url"));
		
		bolaoRepo.save(bolao);
		
		return ResponseEntity.ok().build();
	}
		
	
	@GetMapping("listar-sem-paginacao")
	public List<BolaoDto> listarSemPaginacao (){
		
		List<Bolao> boloes = bolaoRepo.findAll();
		
		
		return BolaoDto.convertToBolaoDto(boloes, modelMapper);
	}
	
	@GetMapping
	public Page<BolaoDto> listar (@RequestParam(required = false) String nomeBolao, @PageableDefault(sort = "nome", direction = Direction.ASC) Pageable paginacao){
		
		if(nomeBolao == null) {
			Page<Bolao> boloes = bolaoRepo.findAll(paginacao);	
			return BolaoDto.convertPageToBolaoDto(boloes);
		
		}else {
			Page<Bolao> boloes = bolaoRepo.findByNome(nomeBolao,paginacao);	
			return BolaoDto.convertPageToBolaoDto(boloes);
			
		}		
		
	}
	
	@GetMapping("/{id}/ranking")
	@Cacheable(value = "ranking", key = "#id")
	public ResponseEntity<?> ranking (@PathVariable Long id){
		
		Bolao bolao = bolaoRepo.getOne(id);
		
		List<BolaoCriterio> criterios =  bcRepo.findByBolao(bolao);
		
		for (BolaoCriterio bc : criterios) {

			if (bc.getCriterio().getNome().equals(TipoCriterio.PE)) {
				pe = bc.getPontuacao();
			
			} else if (bc.getCriterio().getNome().equals(TipoCriterio.RC)) {
				rc = bc.getPontuacao();
			
			} else if (bc.getCriterio().getNome().equals(TipoCriterio.RCG)) {
				rcg = bc.getPontuacao();
			
			} else if (bc.getCriterio().getNome().equals(TipoCriterio.GE)) {
				ge = bc.getPontuacao();
			}
		}	
		
		List<RankingDto> rankingList = new ArrayList<>();
		
		List<Object[]> ranking  = bolaoRepo.findRanking(id,pe,rc,rcg,ge);
		
		ranking.forEach(r -> rankingList.add(RankingDto.montaDto(r)));
		
		return ResponseEntity.ok(rankingList);
	}
	
	@GetMapping("/{id}/ranking-extra")
	@Cacheable(value = "rankingExtra", key = "#id")
	public ResponseEntity<?> rankingExtra (@PathVariable Long id){
		
		Integer campeao = 0;
	    Integer vice = 0;
		Integer terceiro = 0;
		Integer quarto = 0;
		
		Bolao bolao = bolaoRepo.getOne(id);
		
		List<BolaoCriterio> criterios =  bcRepo.findByBolao(bolao);
		
		for (BolaoCriterio bc : criterios) {

			if (bc.getCriterio().getNome().equals(TipoCriterio.CAMPEAO)) {
				campeao = bc.getPontuacao();
			
			} else if (bc.getCriterio().getNome().equals(TipoCriterio.VICE)) {
				vice = bc.getPontuacao();
			
			} else if (bc.getCriterio().getNome().equals(TipoCriterio.TERCEIRO)) {
				terceiro = bc.getPontuacao();
			
			} else if (bc.getCriterio().getNome().equals(TipoCriterio.QUARTO)) {
				quarto = bc.getPontuacao();
			}
		}	
		List<RankingExtraDto> rankingList = new ArrayList<>();
		
		List<Object[]> rankingExtra  = bolaoRepo.findRankingExtra(id,campeao,vice,terceiro,quarto);
		
		rankingExtra.forEach(r -> rankingList.add(RankingExtraDto.montaDto(r)));
		
		return ResponseEntity.ok(rankingList);
	}
	
	@GetMapping("/{id}")
	@Transactional
	public BolaoDto listarBolaoById (@PathVariable Long id) {
		
		Bolao bolao = bolaoRepo.getOne(id);
		
		return BolaoDto.convertUniqueBolaoDto(bolao, modelMapper);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Bolao> atualizar(@PathVariable Long id, @RequestBody BolaoFormDto bolaoForm) {
		
		Bolao bolaoAtualizado =  bolaoForm.atualizar(id,bolaoRepo,campRepo);
		
		return ResponseEntity.ok(bolaoAtualizado);
	}
	@PutMapping("/dtpalpiteextra/{id}")
	@Transactional
	public ResponseEntity<Bolao> atualizarDtPalpiteExtra(@PathVariable Long id, @RequestBody BolaoFormDto bolaoForm) {

		Bolao bolaoAtualizado =  bolaoForm.atualizarDtPalipiteExtra(id, bolaoRepo);

		return ResponseEntity.ok(bolaoAtualizado);
	}
	

}






















