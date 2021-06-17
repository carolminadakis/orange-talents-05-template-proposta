package br.com.zup.anaminadakis.proposta.validacoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class ErroHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TratamentoDeErro> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
        Collection<String> mensagens = new ArrayList<>();
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = String.format("Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage());
            mensagens.add(message);
        });

        TratamentoDeErro tratamentoDeErro = new TratamentoDeErro(mensagens);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tratamentoDeErro);
    }

    @ExceptionHandler(ApiErroException.class)
    public ResponseEntity<TratamentoDeErro> handleApiErroException(ApiErroException apiErroException) {
        Collection<String> mensagens = new ArrayList<>();
        mensagens.add(apiErroException.getReason());

        TratamentoDeErro tratamentoDeErro = new TratamentoDeErro(mensagens);
        return ResponseEntity.status(apiErroException.getHttpStatus()).body(tratamentoDeErro);
    }

}
