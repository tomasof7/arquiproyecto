package com.enviodecorreo.demo.service;

import com.enviodecorreo.demo.model.ShoppingCart;
import com.enviodecorreo.demo.model.TravelPackage;
import com.enviodecorreo.demo.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final TravelPackageService travelPackageService;
    private final CustomerService customerService;

    public ShoppingCart obtenerOCrear(String usuarioTvp) {
        // valida que el usuario exista antes de permitir carrito
        customerService.obtenerPorCorreo(usuarioTvp);
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
        return shoppingCartRepository.findByUsuarioTvp(usuarioTvp)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "El usuario no tiene carrito o no existe: " + usuarioTvp));
    }

    @Transactional
    public void limpiar(String usuarioTvp) {
        shoppingCartRepository.findByUsuarioTvp(usuarioTvp).ifPresent(cart -> {
            cart.getPaquetes().clear();
            shoppingCartRepository.save(cart);
        });
    }
}
