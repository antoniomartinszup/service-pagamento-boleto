package br.com.zup.edu.pagamentoboleto.shared.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({Exception.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroDeFormularioDto handleException(Exception exception) {
        return new ErroDeFormularioDto(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ErroDeFormularioDto handleResponseStatusException(ResponseStatusException exception) {
        return new ErroDeFormularioDto(
                LocalDateTime.now(), exception.getStatus().value(),
                exception.getStatus().toString(), exception.getReason());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroDeFormularioDto handle(MethodArgumentNotValidException exception) {

        StringBuilder mensagem = new StringBuilder();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for(FieldError error: fieldErrors)
            mensagem.append(messageSource.getMessage(error, LocaleContextHolder.getLocale())).append(";");

        return new ErroDeFormularioDto(
                LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), mensagem.toString());
    }
}
