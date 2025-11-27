package com.enviodecorreo.demo.service;

import com.enviodecorreo.demo.model.TravelPackage;
import com.enviodecorreo.demo.repository.TravelPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelPackageService {

    private final TravelPackageRepository travelPackageRepository;

    public List<TravelPackage> listar() {
        return travelPackageRepository.findAll();
    }

    public TravelPackage obtener(Long id) {
        return travelPackageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paquete no encontrado: " + id));
    }

    @Transactional
    public TravelPackage guardar(TravelPackage travelPackage) {
        travelPackage.recalcularPrecio();
        return travelPackageRepository.save(travelPackage);
    }
}
