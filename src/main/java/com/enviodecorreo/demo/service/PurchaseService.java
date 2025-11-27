package com.enviodecorreo.demo.service;

import com.enviodecorreo.demo.model.PaymentStatus;
import com.enviodecorreo.demo.model.Purchase;
import com.enviodecorreo.demo.model.ShoppingCart;
import com.enviodecorreo.demo.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Transactional
    public Purchase crearDesdeCarrito(ShoppingCart cart, String celularContacto) {
        Purchase purchase = Purchase.builder()
                .usuarioTvp(cart.getUsuarioTvp())
                .celularContacto(celularContacto)
                .paquetes(cart.getPaquetes())
                .total(cart.getTotal())
                .estado(PaymentStatus.EN_PROCESO)
                .medioPago("Pagame")
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();
        return purchaseRepository.save(purchase);
    }

    @Transactional
    public Purchase actualizarEstado(Long compraId, PaymentStatus estado) {
        Purchase purchase = purchaseRepository.findById(compraId)
                .orElseThrow(() -> new IllegalArgumentException("Compra no encontrada: " + compraId));
        purchase.setEstado(estado);
        purchase.setFechaActualizacion(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    public Purchase obtener(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Compra no encontrada: " + id));
    }
}
