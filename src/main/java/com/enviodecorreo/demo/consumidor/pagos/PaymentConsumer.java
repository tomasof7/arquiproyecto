package com.enviodecorreo.demo.consumidor.pagos;

import com.enviodecorreo.demo.model.PaymentMessage;
import com.enviodecorreo.demo.model.PaymentStatus;
import com.enviodecorreo.demo.service.NotificationService;
import com.enviodecorreo.demo.service.PagameSimulator;
import com.enviodecorreo.demo.service.PurchaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PurchaseService purchaseService;
    private final NotificationService notificationService;
    private final PagameSimulator pagameSimulator;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${app.rabbitmq.pagos.queue}")
    public void recibirPago(String payload) {
        try {
            PaymentMessage paymentMessage = objectMapper.readValue(payload, PaymentMessage.class);
            PaymentStatus status = pagameSimulator.procesarPago();

            purchaseService.actualizarEstado(paymentMessage.getCompraId(), status);
            notificationService.enviarAvisoPago(paymentMessage, status);
        } catch (JsonProcessingException e) {
            System.out.println("Error al parsear PaymentMessage: " + e.getMessage());
        }
    }
}
