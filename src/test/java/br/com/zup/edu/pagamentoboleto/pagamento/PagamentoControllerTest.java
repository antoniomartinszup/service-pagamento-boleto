package br.com.zup.edu.pagamentoboleto.pagamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void clearRepository() {
        pagamentoRepository.deleteAll();
    }

    @Test
    public void deveriaCadastrarPagamentoComSucesso() throws Exception {
        PagamentoRequest request = new PagamentoRequest(
                "12345678901231250320210000023450",
                1L,
                "tales.araujo@zup.com.br");

        mockMvc.perform(MockMvcRequestBuilders.post("/pagamentos/valorTotal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(pagamentoRepository.existsByCodigoDeBarras("12345678901231250320210000023450"));
    }

    @Test
    public void deveriaConfirmarPagamentoComSucesso() throws Exception {
        Pagamento p = pagamentoRepository.save(
                new Pagamento(
                        "12345678901231250320210000023450",
                        new BigDecimal(10),
                        1L,
                        "tales.araujo@zup.com.br"));

        mockMvc.perform(MockMvcRequestBuilders.patch("/pagamentos/" + p.getCodigoDeBarras() + "/confirmar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        p = pagamentoRepository.findByCodigoDeBarras("12345678901231250320210000023450").orElseThrow(
                () -> new Exception("Falha no teste"));
        assertEquals("CONFIRMADO", p.getStatusPagamento().toString());
    }

    @Test
    public void deveriaRetonarConsultaPorPeriodo() throws Exception {
        Pagamento p = new Pagamento(
                "12345678901231250320210000023450",
                new BigDecimal(10),
                1L,
                "tales.araujo@zup.com.br");
        p.setStatusPagamento(StatusPagamento.CONFIRMADO);
        pagamentoRepository.save(p);

        LocalDate hoje = LocalDate.now();
        String dataFormatada = hoje.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String url = "/pagamentos/periodo/?clienteId=1&inicio=" + dataFormatada + "&termino=" + dataFormatada;

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].valorTotalPago").value("10.0"));
    }

    @Test
    public void deveriaDarBadRequestAoTentarConfirmarPagamentoQueNaoExiste() throws Exception {

        String path = "/pagamentos/12345678901231250320210000023450/confirmar";

        mockMvc.perform(MockMvcRequestBuilders.patch(path)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertFalse(pagamentoRepository.existsByCodigoDeBarras("12345678901231250320210000023450"));
    }
}
