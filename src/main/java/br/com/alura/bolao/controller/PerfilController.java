package br.com.alura.bolao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.modelo.Perfil;
import br.com.alura.bolao.repository.PerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PerfilRepository perfilRepo;
	
	@PostMapping
	public ResponseEntity<Perfil> cadastrar (@RequestBody @Valid Perfil perfil){
		
		perfilRepo.save(perfil);
		
		return ResponseEntity.ok(perfil);
		
	}

}
