package br.com.alura.bolao.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Time {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
		
	@ManyToOne
	private Usuario usuario;
	private LocalDate dtCriacao = LocalDate.now();
	
	
	public Time() {
		
	}
	
	public Time(String nome, Usuario usuario) {
		
		this.nome = nome;
		this.usuario = usuario;
	}

	
	
	

}
