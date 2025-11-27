package com.enviodecorreo.demo.service;

import com.enviodecorreo.demo.model.ShoppingCart;
import com.enviodecorreo.demo.model.TravelPackage;
import com.enviodecorreo.demo.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final TravelPackageService travelPackageService;

    public ShoppingCart obtenerOCrear(String usuarioTvp) {
        return shoppingCartRepository.findByUsuarioTvp(usuarioTvp)
                .orElseGet(() -> shoppingCartRepository.save(
                        ShoppingCart.builder()
                                .usuarioTvp(usuarioTvp)
                                .build()));
    }

    @Transactional
    public ShoppingCart agregarPaquete(String usuarioTvp, Long paqueteId) {
        ShoppingCart cart = obtenerOCrear(usuarioTvp);
        TravelPackage paquete = travelPackageService.obtener(paqueteId);

        if (cart.getPaquetes().stream().noneMatch(p -> p.getId().equals(paqueteId))) {
            cart.getPaquetes().add(paquete);
        }
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart obtener(String usuarioTvp) {
        return obtenerOCrear(usuarioTvp);
    }

    @Transactional
    public void limpiar(String usuarioTvp) {
        ShoppingCart cart = obtenerOCrear(usuarioTvp);
        cart.getPaquetes().clear();
        shoppingCartRepository.save(cart);
    }
}
