package com.enviodecorreo.demo.factory;

import com.enviodecorreo.demo.model.PaymentStatus;
import com.enviodecorreo.demo.model.Purchase;
import com.enviodecorreo.demo.model.ShoppingCart;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class PurchaseFactory {

    public Purchase fromCart(ShoppingCart cart, String celularContacto) {
        return Purchase.builder()
                .usuarioTvp(cart.getUsuarioTvp())
                .celularContacto(celularContacto)
                .paquetes(new ArrayList<>(cart.getPaquetes()))
                .total(cart.getTotal())
                .estado(PaymentStatus.EN_PROCESO)
                .medioPago("Pagame")
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();
    }
}
