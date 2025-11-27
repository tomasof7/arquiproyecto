package com.enviodecorreo.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigo;

    private String nombre;
    private String descripcion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "package_destination",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "destination_id")
    )
    @Singular
    private List<Destination> destinos = new ArrayList<>();

    
    private BigDecimal precioTotal = BigDecimal.ZERO;

    public void recalcularPrecio() {
        BigDecimal total = destinos.stream()
                .map(Destination::getPrecioBase)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        precioTotal = total;
    }
}
