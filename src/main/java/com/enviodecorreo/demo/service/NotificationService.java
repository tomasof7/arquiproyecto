package com.enviodecorreo.demo.service;

import com.enviodecorreo.demo.model.EmailMessage;
import com.enviodecorreo.demo.model.PaymentMessage;
import com.enviodecorreo.demo.model.PaymentStatus;
import com.enviodecorreo.demo.publicador.email.EmailProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailProducer emailProducer;

    @Value("${pagame.sms.remitente}")
    private String remitenteSms;

    public void enviarAvisoPago(PaymentMessage paymentMessage, PaymentStatus estado) {
        String resumenPaquetes = String.join(", ", paymentMessage.getPaquetes());
        String asunto = "Pago " + estado + " - Paquetes de viaje";
        String cuerpo = """
                Hola %s,
                
                Estado de tu pago en Págame: %s
                Paquetes: %s
                Total: %s
                
                Gracias por comprar en TVP Turismo.
                """.formatted(paymentMessage.getUsuarioTvp(), estado, resumenPaquetes, paymentMessage.getTotal());

        // Enviamos por el canal de correo (cola)
        emailProducer.publish(new EmailMessage(paymentMessage.getUsuarioTvp(), asunto, cuerpo));

        // Simulación de SMS al celular
        System.out.printf("[SMS - %s] A %s: Pago %s por %s. Total %s%n",
                remitenteSms,
                paymentMessage.getCelular(),
                estado,
                resumenPaquetes,
                paymentMessage.getTotal());
    }
}
