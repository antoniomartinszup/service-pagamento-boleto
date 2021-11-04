package br.com.zup.edu.pagamentoboleto.pagamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoRequest {

    @NotBlank
    private String codigoDeBarras;

    @NotNull
    private Long clienteId;

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public PagamentoRequest(String codigoDeBarras, Long clienteId) {
        this.codigoDeBarras = codigoDeBarras;
        this.clienteId = clienteId;
    }

    public Long getClienteId() {
        return clienteId;
    }
}