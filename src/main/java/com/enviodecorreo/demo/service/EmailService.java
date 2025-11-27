package com.enviodecorreo.demo.service;

public interface EmailService {

    void enviarCorreo(String para, String asunto, String cuerpo);
}