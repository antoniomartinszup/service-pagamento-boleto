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

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public Long getClienteId() {
        return clienteId;
    }
}