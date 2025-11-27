package com.enviodecorreo.demo.service;

import com.enviodecorreo.demo.model.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PagameSimulator {

    private final Random random = new Random();

    public PaymentStatus procesarPago() {
        int pick = random.nextInt(100);
        if (pick < 60) {
            return PaymentStatus.APROBADO;
        } else if (pick < 80) {
            return PaymentStatus.EN_PROCESO;
        }
        return PaymentStatus.RECHAZADO;
    }
}
