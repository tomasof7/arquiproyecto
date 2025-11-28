package com.enviodecorreo.demo.service;

import com.enviodecorreo.demo.factory.PaymentMessageFactory;
import com.enviodecorreo.demo.model.PaymentMessage;
import com.enviodecorreo.demo.model.Purchase;
import com.enviodecorreo.demo.model.ShoppingCart;
import com.enviodecorreo.demo.publicador.pagos.PaymentPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PurchaseWorkflowService {

    private final ShoppingCartService shoppingCartService;
    private final PurchaseService purchaseService;
    private final PaymentPublisher paymentPublisher;
    private final PaymentMessageFactory paymentMessageFactory;

    @Transactional
    public Purchase iniciarPago(String usuarioTvp, String celular) {
        ShoppingCart cart = shoppingCartService.obtener(usuarioTvp);
        if (cart.getPaquetes().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El carrito está vacío para el usuario " + usuarioTvp);
        }

        Purchase purchase = purchaseService.crearDesdeCarrito(cart, celular);
        PaymentMessage message = paymentMessageFactory.fromPurchase(purchase);

        paymentPublisher.publish(message);
        shoppingCartService.limpiar(usuarioTvp);
        return purchase;
    }
}
