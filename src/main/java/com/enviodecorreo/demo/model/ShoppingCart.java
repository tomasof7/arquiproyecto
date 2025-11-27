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
@Table(name = "shopping_cart", uniqueConstraints = @UniqueConstraint(columnNames = "usuarioTvp"))
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuario autenticado contra AD/TVP
    private String usuarioTvp;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_packages",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "package_id")
    )
    @Builder.Default
    private List<TravelPackage> paquetes = new ArrayList<>();

    public BigDecimal getTotal() {
        return paquetes.stream()
                .map(TravelPackage::getPrecioTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
