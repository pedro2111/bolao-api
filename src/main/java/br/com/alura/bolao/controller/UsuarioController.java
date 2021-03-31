package br.com.alura.bolao.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.alura.bolao.controller.dto.UsuarioDto;
import br.com.alura.bolao.modelo.Perfil;
import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.PerfilRepository;
import br.com.alura.bolao.repository.UsuarioRepository;
import br.com.alura.bolao.services.CloudinaryService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepo;
	
	@Autowired
	CloudinaryService cloudService;
	
	
	@GetMapping
	@ResponseBody
	public List<UsuarioDto> listar(String nome){
		
		if(nome == null) {
			
			List<Usuario> usuarios = usuarioRepository.findAll();
			return UsuarioDto.converter(usuarios);
			
		}else {
			List<Usuario> usuarios = usuarioRepository.findByNome(nome);
			return UsuarioDto.converter(usuarios);
			
		}
		
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioDto usuarioDto,UriComponentsBuilder uriBuilder){
		
		Usuario usuario = usuarioDto.converterUsuario();
		usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt(12)));
		
		List<Perfil> perfis = new ArrayList<Perfil>();
		Long id = (long) 1;
		
		Perfil perfil = perfilRepo.getOne(id);
		
		perfis.add(perfil);
		
		usuario.setPerfis(perfis);

		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@PutMapping("/upload-imagem")
	@Transactional
	public ResponseEntity<?> uploadImagem(@RequestParam("imagem") MultipartFile imagem,@RequestParam("usuario_id") Long usuario_id) throws IOException{
		
		Usuario usuario = usuarioRepository.getOne(usuario_id);
		
		BufferedImage bi = ImageIO.read(imagem.getInputStream());
		
		if(bi == null) {
			
			return ResponseEntity.badRequest().body("imagem inválida");
		}
		
		Map result = cloudService.upload(imagem, "bolao/usuarios");
		
		usuario.setPublic_id((String)result.get("public_id"));
		usuario.setUrl((String)result.get("url"));
		
		usuarioRepository.save(usuario);
		
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public UsuarioDto detalhar (@PathVariable Long id) {
		
		Usuario usuario = usuarioRepository.getOne(id);
		
		return new UsuarioDto(usuario);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar (@PathVariable Long id,@RequestBody @Valid UsuarioDto usuariodto){
		
		Usuario usuario = usuariodto.atualizar(id, usuarioRepository);
		
		return ResponseEntity.ok(new UsuarioDto(usuario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar (@PathVariable Long id){
		
		usuarioRepository.deleteById(id);
		
		return ResponseEntity.ok("Resgistro deletado com sucesso");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
