package br.com.zup.edu.pagamentoboleto.pagamento;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clienteId;

    @Column(nullable = false)
    private BigDecimal valor;

    private LocalDateTime dataPagamento = LocalDateTime.now();

    private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;

    @Deprecated
    public Pagamento() {
    }

    public Pagamento(Long clienteId, BigDecimal valor) {
        this.clienteId = clienteId;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
