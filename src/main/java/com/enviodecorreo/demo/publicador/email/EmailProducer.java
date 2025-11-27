package com.enviodecorreo.demo.publicador.email;

import com.enviodecorreo.demo.model.EmailMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    public void publish(EmailMessage emailMessage) {
        try {
            String json = objectMapper.writeValueAsString(emailMessage);
            rabbitTemplate.convertAndSend(exchange, routingKey, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error convirtiendo EmailMessage a JSON", e);
        }
    }
}
