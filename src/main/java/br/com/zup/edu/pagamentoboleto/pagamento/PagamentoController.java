package br.com.zup.edu.pagamentoboleto.pagamento;

import br.com.zup.edu.pagamentoboleto.integration.banco.BancoClient;
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
    public ResponseEntity<PagamentoResponse> consultaPagamento(@PathVariable String codigoDeBarras) {


        // consultar codigo brrs sistema bancario
        try {
            var statusBanco = bancoClient.verificaBanco(codigoDeBarras);
        } catch () {

        }


        // persistir pagamento no banco de dados
        // retornar valor e status do pagamento para o orquestrado
}


