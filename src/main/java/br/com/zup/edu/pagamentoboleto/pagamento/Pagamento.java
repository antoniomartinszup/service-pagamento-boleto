package br.com.zup.edu.pagamentoboleto.pagamento;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long clienteId;

    @Email
    private String emailDestinatario;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String codigoDeBarras;

    @Column(nullable = false)
    @Positive
    private BigDecimal valor;

    @NotNull
    private LocalDate dataPagamento = LocalDate.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;

    @Deprecated
    public Pagamento() {
    }

    public Pagamento(String codigoDeBarras, BigDecimal valor, Long clienteId, String emailDestinatario) {
        this.codigoDeBarras = codigoDeBarras;
        this.valor = valor;
        this.clienteId = clienteId;
        this.emailDestinatario = emailDestinatario;
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

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }
}
