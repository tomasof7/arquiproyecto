package com.enviodecorreo.demo.controller;

import com.enviodecorreo.demo.model.ShoppingCart;
import com.enviodecorreo.demo.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/{usuarioTvp}/agregar")
    public ShoppingCart agregar(@PathVariable String usuarioTvp, @RequestParam Long paqueteId) {
        return shoppingCartService.agregarPaquete(usuarioTvp, paqueteId);
    }

    @GetMapping("/{usuarioTvp}")
    public ShoppingCart ver(@PathVariable String usuarioTvp) {
        return shoppingCartService.obtener(usuarioTvp);
    }

    @DeleteMapping("/{usuarioTvp}")
    public void limpiar(@PathVariable String usuarioTvp) {
        shoppingCartService.limpiar(usuarioTvp);
    }
}
