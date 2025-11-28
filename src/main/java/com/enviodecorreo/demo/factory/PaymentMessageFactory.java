package com.enviodecorreo.demo.factory;

import com.enviodecorreo.demo.model.PaymentMessage;
import com.enviodecorreo.demo.model.Purchase;
import com.enviodecorreo.demo.model.TravelPackage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMessageFactory {

    public PaymentMessage fromPurchase(Purchase purchase) {
        List<String> nombresPaquetes = purchase.getPaquetes()
                .stream()
                .map(TravelPackage::getNombre)
                .toList();

        return PaymentMessage.builder()
                .compraId(purchase.getId())
                .usuarioTvp(purchase.getUsuarioTvp())
                .celular(purchase.getCelularContacto())
                .total(purchase.getTotal())
                .paquetes(nombresPaquetes)
                .build();
    }
}
