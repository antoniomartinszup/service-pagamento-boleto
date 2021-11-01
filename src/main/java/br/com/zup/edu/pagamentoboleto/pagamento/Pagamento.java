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

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Deprecated
    public Pagamento() {
    }

    public Pagamento(Long clienteId, BigDecimal valor, Tipo tipo) {
        this.clienteId = clienteId;
        this.valor = valor;
        this.tipo = tipo;
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

    public Tipo getTipo() {
        return tipo;
    }
}
