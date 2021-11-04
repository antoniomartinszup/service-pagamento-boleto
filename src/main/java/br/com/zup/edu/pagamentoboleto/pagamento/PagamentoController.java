package br.com.zup.edu.pagamentoboleto.pagamento;

import br.com.zup.edu.pagamentoboleto.integration.banco.BancoClient;
import br.com.zup.edu.pagamentoboleto.integration.banco.BoletoResponse;
import br.com.zup.edu.pagamentoboleto.shared.kafka.PagamentoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private BancoClient bancoClient;

    @Autowired
    private PagamentoProducer pagamentoProducer;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/valorTotal")
    public BoletoResponse cadastrarPagamento(@RequestBody @Valid PagamentoRequest pagamentoRequest) {

        // consultar codigo de barras no sistema bancario
        var boleto = bancoClient.buscarBoleto(pagamentoRequest.getCodigoDeBarras());
        pagamentoRepository.save(new Pagamento(pagamentoRequest.getCodigoDeBarras(), boleto.getValorComJuros()));
        return boleto;
    }

    @PatchMapping("/{codigoDeBarras}/confirmar")
    public ResponseEntity<String> confirmarPagamento(@PathVariable String codigoDeBarras) {
        Pagamento pagamento = pagamentoRepository.findByCodigoDeBarras(codigoDeBarras).orElseThrow(
                () -> new IllegalArgumentException("Esse pagamento não existe."));

        pagamento.setStatusPagamento(StatusPagamento.CONFIRMADO);
        pagamentoRepository.save(pagamento);
        pagamentoProducer.send(new PagamentoRecord(pagamento));
        return ResponseEntity.ok("Pagamento confirmado.");
    }

    @GetMapping("/periodo")
    public List<ConsultaPagamentoResponse> buscarPorPeriodo(
            @RequestParam(name = "inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
            @RequestParam(name = "termino") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate termino) {

        List<Pagamento> pagamentos = pagamentoRepository.findAllByDataPagamentoBetween(inicio, termino);
        return pagamentos.stream().map(ConsultaPagamentoResponse::new).collect(Collectors.toList());
    }
}
