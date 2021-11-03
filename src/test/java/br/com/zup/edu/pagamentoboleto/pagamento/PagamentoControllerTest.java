//package br.com.zup.edu.pagamentoboleto.pagamento;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.junit.BeforeClass;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//class PagamentoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private static ObjectMapper mapper;
//
//    @Autowired
//    private PagamentoRepository pagamentoRepository;
//
//    @BeforeClass
//    public static void setUp() {
//        mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//    }
//
//    @Test
//    public void deveriaCadastrarPagamentoComSucesso() throws Exception {
//        PagamentoRequest request = new PagamentoRequest("12345678901231250320210000023450");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/pagamentos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(request))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        assertTrue(pagamentoRepository.existsById(1L));
//    }
//
//    @Test
//    public void deveriaConfirmarPagamentoComSucesso() throws Exception {
//        Pagamento p = pagamentoRepository.save(new Pagamento(1L, new BigDecimal(10)));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/pagamentos/" + p.getId() + "/confirmar")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertEquals("CONFIRMADO", p.getStatusPagamento().toString());
//    }
//
//}