package br.com.zup.edu.pagamentoboleto.pagamento;

import br.com.zup.edu.pagamentoboleto.integration.banco.BancoClient;
import br.com.zup.edu.pagamentoboleto.integration.banco.BoletoResponse;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private BancoClient bancoClient;

    @GetMapping("/{codigoDeBarras}")
    public ResponseEntity<BoletoResponse> consultaPagamento(@PathVariable String codigoDeBarras) {

        bancoClient = Mockito.mock(BancoClient.class);

        // consultar codigo brrs sistema bancario
        try {
            var statusBanco = bancoClient.buscaDadosBoleto(codigoDeBarras);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().body(new BoletoResponse(codigoDeBarras));
    }
    // persistir pagamento no banco de dados
    // retornar valor e status do pagamento para o orquestrado
}

// Usar o wireMock para emular um objeto que tenha no corpo clienteId, valor, dataVencimento, juros, se é possivel
// pagar fora do prazo de vencimento
