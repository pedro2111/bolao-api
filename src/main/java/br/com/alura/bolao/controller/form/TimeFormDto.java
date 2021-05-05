package br.com.alura.bolao.controller.form;

import br.com.alura.bolao.modelo.Time;
import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.UsuarioRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeFormDto {


	private String nome;
	private Long usuario_id;
	private String url;
	private String public_id;
	

	
	public Time convertToTime (UsuarioRepository usuarioRepo) {
		
		Usuario usuario = usuarioRepo.getOne(usuario_id);
		
		return new Time(nome,usuario,url,public_id);
	}

}


