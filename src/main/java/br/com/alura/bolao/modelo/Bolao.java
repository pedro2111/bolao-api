package br.com.alura.bolao.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bolao {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario criador;
	@ManyToOne
	private Campeonato campeonato;
	
			
	@Enumerated(EnumType.STRING)
	private TipoBolao tipoBolao;
	
	private String nome;
	private String descricao;
	private LocalDateTime dtLimitePalpiteExtra;
	private LocalDate dtCriacao = LocalDate.now();
	@Value("0") 
	private Integer calculoPalpiteExtra = 0;
	private String public_id;
	private String url;
	
	public Bolao(Usuario criador, Campeonato campeonato, TipoBolao tipoBolao, String nome, String descricao, LocalDateTime dtLimitePalpiteExtra) {
		this.criador = criador;
		this.campeonato = campeonato;
		this.tipoBolao = tipoBolao;
		this.nome = nome;
		this.descricao = descricao;
		this.dtLimitePalpiteExtra = dtLimitePalpiteExtra;
	}
	
	public Bolao(Usuario criador, Campeonato campeonato, TipoBolao tipoBolao, String nome, String descricao) {
		this.criador = criador;
		this.campeonato = campeonato;
		this.tipoBolao = tipoBolao;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	
	
	
	

}


//id, Campeonato, Usuario(criador), Participantes(manyToMany), nome, descricao, tipo(privado,publico), 
//DtLimitePalpiteExtra, Criterios(ManyToMany)