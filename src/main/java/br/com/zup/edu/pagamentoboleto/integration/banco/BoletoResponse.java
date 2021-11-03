package br.com.zup.edu.pagamentoboleto.integration.banco;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BoletoResponse {

    private Long id;
    private Long numero;
    private BigDecimal juroDeMoraDiario;
    private Boolean permitePagametnoEmAtraso;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento = null;
    private BigDecimal diasEmAtraso = new BigDecimal(0.0);
    private BigDecimal valorIncial;
    private BigDecimal valorDosJuros = new BigDecimal(0.0);
    private BigDecimal valorComJuros;

    public BoletoResponse(Long numero,
                          BigDecimal juroDeMoraDiario,
                          Boolean permitePagametnoEmAtraso,
                          LocalDate dataVencimento,
                          LocalDate dataPagamento,
                          BigDecimal diasEmAtraso,
                          BigDecimal valorIncial,
                          BigDecimal valorDosJuros,
                          BigDecimal valorComJuros) {
        this.numero = numero;
        this.juroDeMoraDiario = juroDeMoraDiario;
        this.permitePagametnoEmAtraso = permitePagametnoEmAtraso;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.diasEmAtraso = diasEmAtraso;
        this.valorIncial = valorIncial;
        this.valorDosJuros = valorDosJuros;
        this.valorComJuros = valorComJuros;
    }

    @Override
    public String toString() {
        return "Boleto{" +
                "id=" + id +
                ", numero=" + numero +
                ", juroDeMoraDiario=" + juroDeMoraDiario +
                ", permitePagametnoEmAtraso=" + permitePagametnoEmAtraso +
                ", dataVencimento=" + dataVencimento +
                ", dataPagamento=" + dataPagamento +
                ", diasEmAtraso=" + diasEmAtraso +
                ", valorIncial=" + valorIncial +
                ", valorDosJuros=" + valorDosJuros +
                ", valorComJuros=" + valorComJuros +
                '}';
    }

    public BigDecimal getValorComJuros() {
        return valorComJuros;
    }
}