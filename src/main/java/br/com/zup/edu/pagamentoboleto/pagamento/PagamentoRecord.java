package br.com.zup.edu.pagamentoboleto.pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoRecord {

    private String codigoDeBarras;
    private LocalDate dataPagamento;
    private BigDecimal valorTotalPago;
    private String emailDestinatario;

    public PagamentoRecord(Pagamento pagamento) {
        this.codigoDeBarras = pagamento.getCodigoDeBarras();
        this.dataPagamento = pagamento.getDataPagamento();
        this.valorTotalPago = pagamento.getValor();
        this.emailDestinatario = pagamento.getEmailDestinatario();
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

    public String getEmailDestinatario() {
        return emailDestinatario;
    }
}
