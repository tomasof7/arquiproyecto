package com.enviodecorreo.demo.controller;

import com.enviodecorreo.demo.model.Purchase;
import com.enviodecorreo.demo.service.PurchaseService;
import com.enviodecorreo.demo.service.PurchaseWorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PurchaseWorkflowService purchaseWorkflowService;

    @GetMapping("/{compraId}")
    public Purchase detalle(@PathVariable Long compraId) {
        return purchaseService.obtener(compraId);
    }

    @PostMapping("/{usuarioTvp}/pagar")
    public Purchase iniciarPago(
            @PathVariable String usuarioTvp,
            @RequestParam String celular) {

        return purchaseWorkflowService.iniciarPago(usuarioTvp, celular);
    }
}
