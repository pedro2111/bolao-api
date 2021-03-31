package br.com.alura.bolao.controller.form;

import br.com.alura.bolao.modelo.Time;
import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.UsuarioRepository;

public class TimeFormDto {


	private String nome;
	private Long usuario_id;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	public Time convertToTime (UsuarioRepository usuarioRepo) {
		
		Usuario usuario = usuarioRepo.getOne(usuario_id);
		
		return new Time(nome,usuario);
	}

}


