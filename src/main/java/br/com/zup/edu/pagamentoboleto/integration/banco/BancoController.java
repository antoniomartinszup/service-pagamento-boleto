package br.com.zup.edu.pagamentoboleto.integration.banco;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api-simulada")
public class BancoController {

    @GetMapping("/banco")
    public StatusBanco statusBancoEnum() {

        Random gerador = new Random();

        if (gerador.nextBoolean()) {
            return StatusBanco.DOWN;
        }
        return StatusBanco.UP;
    }
}
