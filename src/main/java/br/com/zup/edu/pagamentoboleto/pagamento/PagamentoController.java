package br.com.zup.edu.pagamentoboleto.pagamento;

import br.com.zup.edu.pagamentoboleto.integration.banco.BancoClient;
import br.com.zup.edu.pagamentoboleto.integration.banco.BoletoResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BoletoResponse cadastrarPagamento(@RequestBody @Valid PagamentoRequest pagamentoRequest) {

        // consultar codigo de barras no sistema bancario
        var boleto = bancoClient.buscarBoleto(pagamentoRequest.getCodigoDeBarras());
        pagamentoRepository.save(new Pagamento(pagamentoRequest.getCodigoDeBarras(), boleto.getValorComJuros()));
        return boleto;
    }
    // persistir pagamento no banco de dados
    // retornar valor e status do pagamento para o orquestrador

    @PatchMapping("/{codigoDeBarras}/confirmar")
    public ResponseEntity<String> confirmarPagamento(@PathVariable String codigoDeBarras) {
        Pagamento pagamento = pagamentoRepository.findByCodigoDeBarras(codigoDeBarras).orElseThrow(
                () -> new IllegalArgumentException("Esse pagamento não existe."));

        pagamento.setStatusPagamento(StatusPagamento.CONFIRMADO);
        pagamentoRepository.save(pagamento);
        return ResponseEntity.ok("Pagamento confirmado.");
    }

    @GetMapping("/periodo")
    public List<PagamentoResponse> buscarPorPeriodo(@RequestParam("inicio") LocalDate inicio,
                                                   @RequestParam("termino") LocalDate  termino) {
        List<Pagamento> pagamentos = pagamentoRepository.findAllByDataPagamentoBetween(inicio, termino);
        return pagamentos.stream().map(PagamentoResponse::new).collect(Collectors.toList());
    }
}
