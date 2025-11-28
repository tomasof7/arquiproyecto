package com.enviodecorreo.demo.service;

import com.enviodecorreo.demo.model.Customer;
import com.enviodecorreo.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer registrar(String nombre, String correo, String telefono) {
        if (customerRepository.existsByCorreo(correo)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya está registrado: " + correo);
        }
        Customer customer = Customer.builder()
                .nombre(nombre)
                .correo(correo)
                .telefono(telefono)
                .build();
        return customerRepository.save(customer);
    }

    public Customer obtenerPorCorreo(String correo) {
        return customerRepository.findByCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no registrado: " + correo));
    }

    @Transactional
    public void eliminarPorCorreo(String correo) {
        customerRepository.findByCorreo(correo)
                .ifPresentOrElse(
                        customerRepository::delete,
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no registrado: " + correo);
                        });
    }
}
