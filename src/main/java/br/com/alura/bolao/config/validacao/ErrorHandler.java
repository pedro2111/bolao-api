package br.com.alura.bolao.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
	
	//O controller advice funciona como um interceptador. Qualquer chamada de qualquer controller ele vai interceptar.
	//caso de erro, ele vai tratar de acordo com o que definimos no handler
	
	@Autowired
	private MessageSource messageSource; //classe para pegar messangens de erro. Tem suporte pra tradução ...
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) //tem que passar ostatus pois com o tratamento de erro ele passaria 200 do response por default
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDto> handler(MethodArgumentNotValidException ex) {
		
		List<ErroDto> dto = new ArrayList<ErroDto>();
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDto erroDto = new ErroDto(e.getField(), mensagem);
			
			dto.add(erroDto);
		});
		
		return dto;
		
		
		
	}

}
