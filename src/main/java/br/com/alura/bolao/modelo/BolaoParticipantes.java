package br.com.alura.bolao.modelo;

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
public class BolaoParticipantes {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario participante;
	
	@ManyToOne
	private Bolao bolao;
	
	@Enumerated(EnumType.STRING)
	private StatusParticipante status;

	
	public BolaoParticipantes(Usuario participante, Bolao bolao, StatusParticipante status) {
		this.participante = participante;
		this.bolao = bolao;
		this.status = status;
	} 
	
	
}
