package com.enviodecorreo.demo.controller;

import com.enviodecorreo.demo.model.TravelPackage;
import com.enviodecorreo.demo.service.TravelPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paquetes")
@RequiredArgsConstructor
public class PackageController {

    private final TravelPackageService travelPackageService;

    @GetMapping
    public List<TravelPackage> listar() {
        return travelPackageService.listar();
    }

    @GetMapping("/{id}")
    public TravelPackage detalle(@PathVariable Long id) {
        return travelPackageService.obtener(id);
    }
}
