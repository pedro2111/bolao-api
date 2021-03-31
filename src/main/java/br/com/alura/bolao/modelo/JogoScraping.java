package br.com.alura.bolao.modelo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class JogoScraping {
	
	private String timeCasa;
	private String timeFora;
	private int placarTimeCasa;
	private int placarTimefora;
	private LocalDate dtjogo;
	
	
	public JogoScraping(String timeCasa, String timeFora, int placarTimeCasa, int placarTimefora, LocalDate dtjogo) {
		this.timeCasa = timeCasa;
		this.timeFora = timeFora;
		this.placarTimeCasa = placarTimeCasa;
		this.placarTimefora = placarTimefora;
		this.dtjogo = dtjogo;
	}

}
