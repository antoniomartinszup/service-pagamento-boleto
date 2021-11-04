package br.com.zup.edu.pagamentoboleto.pagamento;

import javax.validation.constraints.NotBlank;

public class PagamentoRequest {

    @NotBlank
    private String codigoDeBarras;

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }
}