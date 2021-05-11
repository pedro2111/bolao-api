package br.com.alura.bolao.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.bolao.modelo.Usuario;
import br.com.alura.bolao.repository.UsuarioRepository;

public class UsuarioDto {

	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String senha;
	
	private String url;


	public UsuarioDto() {
		
	}

	public UsuarioDto(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.url = usuario.getUrl();
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}

	public static List<UsuarioDto> converter(List<Usuario> usuarios) {

		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
	
	public Usuario converterUsuario() {
		
		return new Usuario(nome,email,senha);
		
	}

	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		return usuario;
	}

}
