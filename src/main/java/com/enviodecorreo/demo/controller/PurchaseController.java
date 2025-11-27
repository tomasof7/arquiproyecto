package com.enviodecorreo.demo.controller;

import com.enviodecorreo.demo.model.PaymentMessage;
import com.enviodecorreo.demo.model.Purchase;
import com.enviodecorreo.demo.model.ShoppingCart;
import com.enviodecorreo.demo.model.TravelPackage;
import com.enviodecorreo.demo.publicador.pagos.PaymentPublisher;
import com.enviodecorreo.demo.service.PurchaseService;
import com.enviodecorreo.demo.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class PurchaseController {

    private final ShoppingCartService shoppingCartService;
    private final PurchaseService purchaseService;
    private final PaymentPublisher paymentPublisher;

    @GetMapping("/{compraId}")
    public Purchase detalle(@PathVariable Long compraId) {
        return purchaseService.obtener(compraId);
    }

    @PostMapping("/{usuarioTvp}/pagar")
    public Purchase iniciarPago(
            @PathVariable String usuarioTvp,
            @RequestParam String celular) {

        ShoppingCart cart = shoppingCartService.obtener(usuarioTvp);
        if (cart.getPaquetes().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío para el usuario " + usuarioTvp);
        }

        Purchase purchase = purchaseService.crearDesdeCarrito(cart, celular);

        List<String> nombresPaquetes = cart.getPaquetes()
                .stream()
                .map(TravelPackage::getNombre)
                .toList();

        PaymentMessage message = PaymentMessage.builder()
                .compraId(purchase.getId())
                .usuarioTvp(usuarioTvp)
                .celular(celular)
                .total(cart.getTotal())
                .paquetes(nombresPaquetes)
                .build();

        paymentPublisher.publish(message);
        shoppingCartService.limpiar(usuarioTvp);

        return purchase;
    }
}
