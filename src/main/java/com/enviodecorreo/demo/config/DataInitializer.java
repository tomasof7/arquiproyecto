package com.enviodecorreo.demo.config;

import com.enviodecorreo.demo.model.Destination;
import com.enviodecorreo.demo.model.TravelPackage;
import com.enviodecorreo.demo.repository.DestinationRepository;
import com.enviodecorreo.demo.service.TravelPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final DestinationRepository destinationRepository;
    private final TravelPackageService travelPackageService;

    @Bean
    public CommandLineRunner seedData() {
        return args -> {
            if (destinationRepository.count() > 0) {
                return; // ya está poblado
            }

            Destination cartagena = destinationRepository.save(Destination.builder()
                    .nombre("Cartagena histórico")
                    .ciudad("Cartagena")
                    .region("Caribe")
                    .precioBase(new BigDecimal("450000"))
                    .build());

            Destination islasRosario = destinationRepository.save(Destination.builder()
                    .nombre("Islas del Rosario")
                    .ciudad("Cartagena")
                    .region("Caribe")
                    .precioBase(new BigDecimal("350000"))
                    .build());

            Destination bogotaCity = destinationRepository.save(Destination.builder()
                    .nombre("City tour Bogotá")
                    .ciudad("Bogotá")
                    .region("Andina")
                    .precioBase(new BigDecimal("220000"))
                    .build());

            Destination monserrate = destinationRepository.save(Destination.builder()
                    .nombre("Monserrate al atardecer")
                    .ciudad("Bogotá")
                    .region("Andina")
                    .precioBase(new BigDecimal("120000"))
                    .build());

            Destination comuna13 = destinationRepository.save(Destination.builder()
                    .nombre("Comuna 13 y Graffitour")
                    .ciudad("Medellín")
                    .region("Antioquia")
                    .precioBase(new BigDecimal("180000"))
                    .build());

            Destination guatape = destinationRepository.save(Destination.builder()
                    .nombre("Guatapé y Piedra del Peñol")
                    .ciudad("Antioquia")
                    .region("Antioquia")
                    .precioBase(new BigDecimal("250000"))
                    .build());

            Destination tayrona = destinationRepository.save(Destination.builder()
                    .nombre("Parque Tayrona")
                    .ciudad("Santa Marta")
                    .region("Caribe")
                    .precioBase(new BigDecimal("400000"))
                    .build());

            Destination sierra = destinationRepository.save(Destination.builder()
                    .nombre("Sierra Nevada experiencia indígena")
                    .ciudad("Santa Marta")
                    .region("Caribe")
                    .precioBase(new BigDecimal("380000"))
                    .build());

            Destination ejeCafetero = destinationRepository.save(Destination.builder()
                    .nombre("Eje Cafetero y Salento")
                    .ciudad("Armenia")
                    .region("Cafetera")
                    .precioBase(new BigDecimal("260000"))
                    .build());

            Destination valleCocora = destinationRepository.save(Destination.builder()
                    .nombre("Valle del Cocora")
                    .ciudad("Quindío")
                    .region("Cafetera")
                    .precioBase(new BigDecimal("240000"))
                    .build());

            Destination canoCristales = destinationRepository.save(Destination.builder()
                    .nombre("Caño Cristales")
                    .ciudad("La Macarena")
                    .region("Orinoquía")
                    .precioBase(new BigDecimal("550000"))
                    .build());

            Destination sanAndres = destinationRepository.save(Destination.builder()
                    .nombre("San Andrés y Johnny Cay")
                    .ciudad("San Andrés")
                    .region("Insular")
                    .precioBase(new BigDecimal("300000"))
                    .build());

            travelPackageService.guardar(TravelPackage.builder()
                    .codigo("CAR-001")
                    .nombre("Caribe colonial")
                    .descripcion("Cartagena histórico + Islas del Rosario + Parque Tayrona")
                    .destino(cartagena)
                    .destino(islasRosario)
                    .destino(tayrona)
                    .build());

            travelPackageService.guardar(TravelPackage.builder()
                    .codigo("ANT-002")
                    .nombre("Antioquia de colores")
                    .descripcion("Comuna 13, Graffitour y Guatapé en un solo viaje")
                    .destino(comuna13)
                    .destino(guatape)
                    .build());

            travelPackageService.guardar(TravelPackage.builder()
                    .codigo("CAF-003")
                    .nombre("Bogotá + Cafetera")
                    .descripcion("City tour, Monserrate y eje cafetero completo")
                    .destino(bogotaCity)
                    .destino(monserrate)
                    .destino(ejeCafetero)
                    .destino(valleCocora)
                    .build());

            travelPackageService.guardar(TravelPackage.builder()
                    .codigo("NAT-004")
                    .nombre("Naturaleza extrema")
                    .descripcion("Caño Cristales y Sierra Nevada para amantes del ecoturismo")
                    .destino(canoCristales)
                    .destino(sierra)
                    .build());

            travelPackageService.guardar(TravelPackage.builder()
                    .codigo("INS-005")
                    .nombre("Caribe insular")
                    .descripcion("San Andrés + Islas del Rosario para descansar en playas")
                    .destino(sanAndres)
                    .destino(islasRosario)
                    .build());
        };
    }
}
