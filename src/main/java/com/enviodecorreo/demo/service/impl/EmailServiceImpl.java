package com.enviodecorreo.demo.service.impl;

import com.enviodecorreo.demo.model.EmailLog;
import com.enviodecorreo.demo.repository.EmailLogRepository;
import com.enviodecorreo.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final EmailLogRepository emailLogRepository;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void enviarCorreo(String para, String asunto, String cuerpo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(para);
        message.setSubject(asunto);
        message.setText(cuerpo);

        mailSender.send(message);

        EmailLog log = EmailLog.builder()
                .destinatario(para)
                .asunto(asunto)
                .mensaje(cuerpo)
                .fechaEnvio(LocalDateTime.now().toString())
                .build();
        emailLogRepository.save(log);
    }
}
