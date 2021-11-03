package br.com.zup.edu.pagamentoboleto.pagamento;

import br.com.zup.edu.pagamentoboleto.integration.banco.BancoClient;
import br.com.zup.edu.pagamentoboleto.integration.banco.BoletoResponse;
import feign.FeignException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/{codigoDeBarras}")
    public BoletoResponse cadastraPagamento(@RequestBody @Valid PagamentoRequest pagamentoRequest) {

        bancoClient = Mockito.mock(BancoClient.class);

        // consultar codigo brrs sistema bancario
        try {
            var statusBanco = bancoClient.buscaDadosBoleto(pagamentoRequest.getCodigoDeBarras());
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Serviço do Banco fora do ar");
        }
        return new BoletoResponse(pagamentoRequest.getCodigoDeBarras());
    }
    // persistir pagamento no banco de dados
    // retornar valor e status do pagamento para o orquestrado

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<String> confirmarPagamento(@PathVariable Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Esse pagamento não existe."));

        pagamento.setStatusPagamento(StatusPagamento.CONFIRMADO);
        pagamentoRepository.save(pagamento);
        return ResponseEntity.ok("Pagamento confirmado.");
    }

    @GetMapping("/periodo")
    public List<PagamentoResponse> buscaPorPeriodo(@RequestParam("inicio") LocalDate inicio,
                                                   @RequestParam("termino") LocalDate  termino) {
        List<Pagamento> pagamentos = pagamentoRepository.findAllByDataPagamentoBetween(inicio, termino);
        return pagamentos.stream().map(PagamentoResponse::new).collect(Collectors.toList());
    }
}
