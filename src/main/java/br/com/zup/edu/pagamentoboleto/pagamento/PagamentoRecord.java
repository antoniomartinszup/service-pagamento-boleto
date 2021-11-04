package br.com.zup.edu.pagamentoboleto.pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoRecord {

    private String codigoDeBarras;
    private LocalDate dataPagamento;
    private BigDecimal valorTotalPago;

    public PagamentoRecord(Pagamento pagamento) {
        this.codigoDeBarras = pagamento.getCodigoDeBarras();
        this.dataPagamento = pagamento.getDataPagamento();
        this.valorTotalPago = pagamento.getValor();
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public BigDecimal getValorTotalPago() {
        return valorTotalPago;
    }
}
