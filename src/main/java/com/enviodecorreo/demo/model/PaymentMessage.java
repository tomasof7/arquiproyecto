package com.enviodecorreo.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMessage {
    private Long compraId;
    private String usuarioTvp;
    private String celular;
    private BigDecimal total;
    private List<String> paquetes;
}
