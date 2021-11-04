package br.com.zup.edu.pagamentoboleto.pagamento;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoRequest {

    @NotBlank
    private String codigoDeBarras;

    @NotNull
    private Long clienteId;

    @Email
    private String emailDestinatario;

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public PagamentoRequest(String codigoDeBarras, Long clienteId, String emailDestinatario) {
        this.codigoDeBarras = codigoDeBarras;
        this.clienteId = clienteId;
        this.emailDestinatario = emailDestinatario;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }
}