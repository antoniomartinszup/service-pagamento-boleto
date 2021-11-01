package br.com.zup.edu.pagamentoboleto.integration.banco;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "simula-api", url = "${feign.client.api-simulada}")
public interface BancoClient {

    @GetMapping(value = "/banco")
    StatusBanco verificaBanco();
}
