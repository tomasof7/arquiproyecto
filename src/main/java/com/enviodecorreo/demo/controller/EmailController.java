package com.enviodecorreo.demo.controller;

import com.enviodecorreo.demo.model.EmailMessage;
import com.enviodecorreo.demo.publicador.email.EmailProducer;
import com.enviodecorreo.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final EmailProducer emailProducer;

    // 1) Envío directo (sin RabbitMQ)
    @PostMapping("/send")
    public String enviarCorreo(
            @RequestParam String para,
            @RequestParam String asunto,
            @RequestParam String cuerpo
    ) {
        emailService.enviarCorreo(para, asunto, cuerpo);
        return "Correo enviado directamente ✔️";
    }

    // 2) Envío usando RabbitMQ (publicador -> cola -> consumidor -> correo)
    @PostMapping("/send-async")
    public String enviarCorreoAsync(
            @RequestParam String para,
            @RequestParam String asunto,
            @RequestParam String cuerpo
    ) {
        EmailMessage message = new EmailMessage(para, asunto, cuerpo);
        emailProducer.publish(message);
        return "Mensaje publicado en RabbitMQ ✔️";
    }
}
