package com.enviodecorreo.demo.controller;

import com.enviodecorreo.demo.model.Customer;
import com.enviodecorreo.demo.service.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/registro")
    public Customer registrar(@RequestBody RegistroRequest request) {
        return customerService.registrar(request.getNombre(), request.getCorreo(), request.getTelefono());
    }

    @GetMapping("/{correo}")
    public Customer obtener(@PathVariable String correo) {
        return customerService.obtenerPorCorreo(correo);
    }

    @DeleteMapping("/{correo}")
    public void eliminar(@PathVariable String correo) {
        customerService.eliminarPorCorreo(correo);
    }

    @Data
    public static class RegistroRequest {
        private String nombre;
        private String correo;
        private String telefono;
    }
}
