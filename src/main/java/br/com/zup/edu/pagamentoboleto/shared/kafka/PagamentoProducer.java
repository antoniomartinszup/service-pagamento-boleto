package br.com.zup.edu.pagamentoboleto.shared.kafka;

import br.com.zup.edu.pagamentoboleto.pagamento.PagamentoRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PagamentoProducer {

    private static final Logger logger = LoggerFactory.getLogger(PagamentoProducer.class);
    private final String topic;
    private final KafkaTemplate<String, PagamentoRecord> kafkaTemplate;

    public PagamentoProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, PagamentoRecord> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(PagamentoRecord pagamentoRecord){
        kafkaTemplate.send(topic, pagamentoRecord).addCallback(
                success -> logger.info("Messagem enviada para cliente = "),
                failure -> logger.info("Message não enviada para o cliente = ")
        );
    }
}
