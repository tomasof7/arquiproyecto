package com.enviodecorreo.demo.publicador.pagos;

import com.enviodecorreo.demo.model.PaymentMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${app.rabbitmq.pagos.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.pagos.routing-key}")
    private String routingKey;

    public void publish(PaymentMessage paymentMessage) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, objectMapper.writeValueAsString(paymentMessage));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializando PaymentMessage", e);
        }
    }
}
