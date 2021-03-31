package br.com.alura.bolao.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Campeonato {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
		
	@ManyToOne
	private Usuario criador;
	@ManyToOne
	private Usuario vencedor;
	
	@ManyToOne
	private Time campeao;
	
	@ManyToOne
	private Time vice;
	
	@ManyToOne
	private Time terceiro;
	
	@ManyToOne
	private Time quarto;
	
	@ManyToMany
	private List<Time> times = new ArrayList<Time>();
	
	@Enumerated(EnumType.STRING)
	private StatusCampeonato status = StatusCampeonato.CRIADO;
	
	private LocalDate dtCriacao = LocalDate.now();
	private String public_id;
	private String url;
	
	
	public Campeonato(String nome, Usuario criador) {
		this.nome = nome;
		this.criador = criador;
		
	}
	
	

	
	

}
