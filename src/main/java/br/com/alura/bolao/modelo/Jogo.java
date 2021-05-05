package br.com.alura.bolao.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Jogo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Campeonato campeonato;
	
	@ManyToOne
	private Time time1;
	
	@ManyToOne
	private Time time2;
	
	private Integer placarTime1;
	private Integer placarTime2;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dtJogo;
	
	@ManyToOne
	private Usuario criador;
	
	@Enumerated(EnumType.STRING)
	private StatusJogo status = StatusJogo.CRIADO;
	private LocalDate dtCriacao = LocalDate.now();
	private String rodada;
	private String local;
	
	public Jogo() {
		
	}
	
	public Jogo(Campeonato campeonato,Time time1, Time time2, LocalDateTime dtJogo, Usuario criador,String rodada, String local) {
		this.campeonato = campeonato;
		this.time1 = time1;
		this.time2 = time2;
		this.dtJogo = dtJogo;
		this.criador = criador;
		this.rodada = rodada;
		this.local = local;
	}
	
	
	

}
