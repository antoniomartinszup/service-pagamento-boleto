package br.com.zup.edu.pagamentoboleto.antonio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GeradorDeBoletos {

    public Boleto geraBoleto(String codigoDeBarras){

        Long numero;
        BigDecimal juroDeMoraDiario;
        Boolean permitePagametnoEmAtraso;
        LocalDate dataVencimento;
        LocalDate dataPagamento;
        BigDecimal diasEmAtraso;
        BigDecimal valorIncial;
        BigDecimal valorDosJuros;
        BigDecimal valorComJuros;

        //só pra teste
        //codigoDeBarras = "12345678901231250320210000023450";

        //Para não ter que chamar o método length toda hora
        Integer tamanhoDaString = codigoDeBarras.length();

        //Para compor o modelo de boleto
        numero = getNumeroBoleto(codigoDeBarras, tamanhoDaString);
        juroDeMoraDiario = juroDeMoraDiario(codigoDeBarras, tamanhoDaString);
        permitePagametnoEmAtraso = permitePagametnoEmAtraso(codigoDeBarras, tamanhoDaString);
        valorIncial = getValorIncial(codigoDeBarras, tamanhoDaString);
        dataVencimento = getDataVencimento(codigoDeBarras, tamanhoDaString);
        diasEmAtraso = getDiasEmAtraso(dataVencimento);
        valorDosJuros = getValorDosJuros(diasEmAtraso, juroDeMoraDiario);
        valorComJuros = gatvalorComJuros(valorDosJuros, valorIncial);

        Boleto boleto = new Boleto(numero,
            juroDeMoraDiario,
            permitePagametnoEmAtraso,
            dataVencimento,
            null,
            diasEmAtraso,
            valorIncial,
            valorDosJuros,
            valorComJuros);

        //contando de trás pra frente
        //10 char - Valor, usando 2 últimos char para centavos. Completa com zeros à esquerda
        // ex. 0000023450 = 234,50

        //6 char  - Data de vencimento formato dd/MM/yyyyy (sem as barras)
        //ex. 25032022 = 25/03/2022

        //1 char - aceita gamaneto apos o vencimento. 1 indica sim e 0 indica não

        //4 char - juro de mora diário, podendo

        return boleto;
    }

    private BigDecimal gatvalorComJuros(BigDecimal valorDosJuros, BigDecimal valorIncial) {
        return valorDosJuros.add(valorIncial);
    }

    private Long getNumeroBoleto (String codigoDeBarras, Integer tamanhoDaString){
        Long numero =  Long.parseLong(codigoDeBarras.substring(0, tamanhoDaString - 23));
        return numero;
    }

    private BigDecimal  juroDeMoraDiario (String codigoDeBarras, Integer tamanhoDaString){
        String parteInteira = codigoDeBarras.substring(tamanhoDaString - 23, tamanhoDaString - 21);
        String parteFracionada = codigoDeBarras.substring(tamanhoDaString - 21, tamanhoDaString - 19);
        BigDecimal numero = new BigDecimal(parteInteira + "." + parteFracionada);
        return numero;
    }

    private BigDecimal getDiasEmAtraso(LocalDate dataVencimento){
        long diff = ChronoUnit.DAYS.between(dataVencimento, LocalDate.now());
        return new BigDecimal(diff);
    }

    private BigDecimal getValorDosJuros(BigDecimal diasEmAtraso, BigDecimal JurosDeMoraDiario){
        BigDecimal valorDosJuros = diasEmAtraso.multiply(JurosDeMoraDiario);
        return  valorDosJuros;
    }

    private Boolean  permitePagametnoEmAtraso (String codigoDeBarras, Integer tamanhoDaString){
        String p = codigoDeBarras.substring(tamanhoDaString - 19, tamanhoDaString - 18);
        Boolean permite = p.equals("1")? true: false;
        return permite;
    }

    private BigDecimal  getValorIncial (String codigoDeBarras, Integer tamanhoDaString){
        return new BigDecimal(codigoDeBarras.substring(tamanhoDaString - 10, tamanhoDaString - 2) +
                "." +
                codigoDeBarras.substring(tamanhoDaString - 2));
    }

    private LocalDate  getDataVencimento (String codigoDeBarras, Integer tamanhoDaString){
        String ano  = (codigoDeBarras.substring(tamanhoDaString - 14, tamanhoDaString - 10));
        String mes  = (codigoDeBarras.substring(tamanhoDaString - 16, tamanhoDaString - 14));
        String dia  = (codigoDeBarras.substring(tamanhoDaString - 18, tamanhoDaString - 16));
        LocalDate localDate = LocalDate.parse(ano + "-" + mes + "-" + dia);
        return localDate;
    }

}
