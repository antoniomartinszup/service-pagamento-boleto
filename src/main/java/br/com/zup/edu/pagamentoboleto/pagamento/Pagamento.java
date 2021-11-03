package br.com.zup.edu.pagamentoboleto.pagamento;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String codigoDeBarras;

    @Column(nullable = false)
    @Positive
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataPagamento = LocalDateTime.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;

    @Deprecated
    public Pagamento() {
    }

    public Pagamento(String codigoDeBarras, BigDecimal valor) {
        this.codigoDeBarras = codigoDeBarras;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
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
