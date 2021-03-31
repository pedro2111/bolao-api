package br.com.alura.bolao.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BolaoCriterio {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Bolao bolao;
	
	@ManyToOne
	private Criterio criterio;
	
	private Integer pontuacao;	
	private LocalDate dtCriacao = LocalDate.now();
	
	
	public BolaoCriterio(Bolao bolao, Criterio criterio, Integer pontuacao) {
		this.bolao = bolao;
		this.criterio = criterio;
		this.pontuacao = pontuacao;
	}
	
	
	
	

}
