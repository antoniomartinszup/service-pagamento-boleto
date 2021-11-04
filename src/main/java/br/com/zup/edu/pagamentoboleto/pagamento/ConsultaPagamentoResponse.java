package br.com.zup.edu.pagamentoboleto.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ConsultaPagamentoResponse {

    private LocalDateTime dataPagamento;
    private BigDecimal valorTotalPago;

    public ConsultaPagamentoResponse(Pagamento pagamento) {
        this.dataPagamento = pagamento.getDataPagamento();
        this.valorTotalPago = pagamento.getValor();
    }
}