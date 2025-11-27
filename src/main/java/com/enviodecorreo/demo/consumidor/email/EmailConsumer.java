package com.enviodecorreo.demo.consumidor.email;

import com.enviodecorreo.demo.model.EmailMessage;
import com.enviodecorreo.demo.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(String mensajeJson) {
        try {
            EmailMessage emailMessage = objectMapper.readValue(mensajeJson, EmailMessage.class);
            emailService.enviarCorreo(
                    emailMessage.getPara(),
                    emailMessage.getAsunto(),
                    emailMessage.getCuerpo()
            );
        } catch (JsonProcessingException e) {
            System.out.println("Error al parsear mensaje JSON recibido de RabbitMQ: " + e.getMessage());
        }
    }
}
