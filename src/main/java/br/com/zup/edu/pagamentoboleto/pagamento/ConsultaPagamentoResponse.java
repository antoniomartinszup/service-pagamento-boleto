package br.com.zup.edu.pagamentoboleto.pagamento;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ConsultaPagamentoResponse {

    private LocalDate dataPagamento;
    private BigDecimal valorTotalPago;

    public ConsultaPagamentoResponse(Pagamento pagamento) {
        this.dataPagamento = pagamento.getDataPagamento();
        this.valorTotalPago = pagamento.getValor();
    }
}
