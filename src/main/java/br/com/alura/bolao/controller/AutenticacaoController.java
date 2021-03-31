package br.com.alura.bolao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.config.secutiry.TokenService;
import br.com.alura.bolao.controller.dto.TokenDto;
import br.com.alura.bolao.controller.form.LoginFormDto;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationManager; //serve para desenvolver o processo de autenticaçaõ sem o form login
	@Autowired
	private TokenService tokenService;
	
	@CrossOrigin(origins ="https://restaurantedarosa.herokuapp.com")
	@PostMapping
	public ResponseEntity<TokenDto> autenticar (@RequestBody @Valid LoginFormDto form){
		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authenticate = authenticationManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(authenticate); //do authenticate conseguimos retirar o usuario logado
			
			
			return ResponseEntity.ok(new TokenDto(token,"Bearer"));
			
		} catch (AuthenticationException e) {
			
			return ResponseEntity.badRequest().build();
			
		}
		
		
	}

}
