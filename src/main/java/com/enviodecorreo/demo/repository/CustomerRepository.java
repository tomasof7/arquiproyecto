package com.enviodecorreo.demo.repository;

import com.enviodecorreo.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
