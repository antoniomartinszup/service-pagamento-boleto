package br.com.zup.edu.pagamentoboleto.shared.exception;


import java.time.LocalDateTime;

public class ErroDeFormularioDto {

    private final LocalDateTime momentoErro;
    private final Integer codigoStatus;
    private final String status;
    private final String mensagem;

    public ErroDeFormularioDto(LocalDateTime momentoErro, Integer codigoStatus, String status, String mensagem) {
        this.momentoErro = momentoErro;
        this.codigoStatus = codigoStatus;
        this.status = status;
        this.mensagem = mensagem;
    }

    public LocalDateTime getMomentoErro() {
        return momentoErro;
    }

    public Integer getCodigoStatus() {
        return codigoStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
