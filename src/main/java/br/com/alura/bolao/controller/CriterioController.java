package br.com.alura.bolao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bolao.modelo.Criterio;
import br.com.alura.bolao.repository.CriterioRepository;

@RestController
@RequestMapping("/criterios")
public class CriterioController {
	
	@Autowired
	private CriterioRepository criterioRepo;
	
	@GetMapping
	public List<Criterio> listar (){
		
		List<Criterio> criterios = criterioRepo.findAll();
		
		return criterios;
	}

}
