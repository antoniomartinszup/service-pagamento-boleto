package br.com.zup.edu.pagamentoboleto.shared.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({Exception.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroDto handleException(Exception exception) {
        return new ErroDto(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());
    }

    @ExceptionHandler(PagamentoNaoExistenteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroDto handlePagamentoNaoExistenteException(PagamentoNaoExistenteException exception) {
        return new ErroDto(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.toString(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErroDto handle(MethodArgumentNotValidException exception) {

        StringBuilder mensagem = new StringBuilder();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors)
            mensagem.append(messageSource.getMessage(error, LocaleContextHolder.getLocale())).append(";");

        return new ErroDto(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), mensagem.toString());
    }
}
