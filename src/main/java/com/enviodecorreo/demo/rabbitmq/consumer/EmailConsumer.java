package com.enviodecorreo.demo.rabbitmq.consumer;

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
            // Convertimos el JSON que llega desde RabbitMQ a un objeto Java
            EmailMessage emailMessage =
                    objectMapper.readValue(mensajeJson, EmailMessage.class);

            // Llamamos al servicio que envía el correo y guarda en BD
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