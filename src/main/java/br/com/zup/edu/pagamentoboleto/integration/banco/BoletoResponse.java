package br.com.zup.edu.pagamentoboleto.integration.banco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BoletoResponse {

    private String clienteId;
    private BigDecimal valorBoleto;
    private LocalDate dataVencimento;
    private BigDecimal jurosPosVencimento;
    private AceitaPagamento aceitaPagamento;

    public BoletoResponse(String codigoDeBarras) {
        this.clienteId = gerarClientId();
        this.valorBoleto = gerarValor(codigoDeBarras);
        this.dataVencimento = gerarDataVencimento(codigoDeBarras);
        this.jurosPosVencimento = gerarJurosPosVencimento(codigoDeBarras);
        this.aceitaPagamento = aceitaPagamentoPosVecimento(codigoDeBarras);
    }

    public AceitaPagamento aceitaPagamentoPosVecimento(String codigoDeBarras) {
        if (codigoDeBarras.endsWith("1")) {
            return this.aceitaPagamento = AceitaPagamento.NAO;
        }
        if (codigoDeBarras.endsWith("2")) {
            return this.aceitaPagamento = AceitaPagamento.SIM;
        }
        return this.aceitaPagamento = AceitaPagamento.SIM;
    }

    public BigDecimal gerarJurosPosVencimento(String codigoDeBarras) {
        if (codigoDeBarras.endsWith("2")) {
            return this.jurosPosVencimento = new BigDecimal("0.30");
        }
        return this.jurosPosVencimento = BigDecimal.ZERO;
    }

    public BigDecimal gerarValor(String codigoDeBarras) {
        if (codigoDeBarras.endsWith("6")) {
            return this.valorBoleto = new BigDecimal("600.00");
        }
        if (codigoDeBarras.endsWith("1")) {
            return this.valorBoleto = new BigDecimal("400.00");
        }
        if (codigoDeBarras.endsWith("2")) {
            return this.valorBoleto = new BigDecimal("200.00");
        }
        return this.valorBoleto = new BigDecimal("100.00");
    }

    public String gerarClientId() {
        Random random = new Random();
        return this.clienteId = String.valueOf(random.nextInt(1000000));
    }

    public LocalDate gerarDataVencimento(String codigoDeBarras) {
        if (codigoDeBarras.endsWith("1")) {
            LocalDate startDate = LocalDate.of(2021, 1, 1); //start date
            long start = startDate.toEpochDay();

            LocalDate endDate = LocalDate.now(); //end date
            long end = endDate.toEpochDay();

            return this.dataVencimento = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
        }
        if (codigoDeBarras.endsWith("2")) {
            LocalDate startDate = LocalDate.of(2021, 9, 1); //start date
            long start = startDate.toEpochDay();

            LocalDate endDate = LocalDate.now(); //end date
            long end = endDate.toEpochDay();

            return this.dataVencimento = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(start, end).findAny().getAsLong());
        }
        if (codigoDeBarras.endsWith("3")) {
            LocalDate startDate = LocalDate.of(2021, 12, 30); //start date
            long start = startDate.toEpochDay();

            LocalDate endDate = LocalDate.now(); //end date
            long end = endDate.toEpochDay();

            return this.dataVencimento = LocalDate.ofEpochDay(ThreadLocalRandom.current().longs(end, start).findAny().getAsLong());
        }
        return this.dataVencimento = LocalDate.now();
    }

    public String getClienteId() {
        return clienteId;
    }

    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public BigDecimal getJurosPosVencimento() {
        return jurosPosVencimento;
    }

    public AceitaPagamento getAceitaPagamento() {
        return aceitaPagamento;
    }
}
