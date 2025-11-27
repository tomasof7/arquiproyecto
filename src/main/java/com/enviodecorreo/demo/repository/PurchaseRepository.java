package com.enviodecorreo.demo.repository;

import com.enviodecorreo.demo.model.PaymentStatus;
import com.enviodecorreo.demo.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByEstado(PaymentStatus estado);
}
