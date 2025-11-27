package com.enviodecorreo.demo.repository;

import com.enviodecorreo.demo.model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
    Optional<TravelPackage> findByCodigo(String codigo);
}
