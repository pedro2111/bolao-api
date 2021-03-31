package br.com.alura.bolao.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
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
public class PalpiteExtra {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Bolao bolao;
	
	@ManyToOne
	private Criterio criterio;
	
	@ManyToOne
	private Time palpiteExtraTime;
	
	private Integer pontosGanho = 0;
	
	private LocalDate dtCriacao = LocalDate.now();

	public PalpiteExtra(Bolao bolao, Usuario usuario, Criterio criterio, Time palpiteExtraTime) {
		this.bolao = bolao;
		this.usuario = usuario;
		this.criterio = criterio;
		this.palpiteExtraTime = palpiteExtraTime;
	}
	
	
	

}
