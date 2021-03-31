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
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Palpite {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Bolao bolao;
	
	@ManyToOne
	private Jogo jogo;
	
	@ManyToOne
	private Usuario usuario;
	
	
	private Integer placarTime1;
	private Integer placarTime2;
	private Integer pontosGanho = 0;
	private LocalDate dtCriacao = LocalDate.now();	
	
	
	public Palpite(Bolao bolao,Jogo jogo,Usuario usuario,int placarTime1,int placarTime2) {
		
		this.bolao = bolao;
		this.jogo = jogo;
		this.usuario = usuario;
		this.placarTime1 = placarTime1;
		this.placarTime2 = placarTime2;
		
		
	}
	
	
}
