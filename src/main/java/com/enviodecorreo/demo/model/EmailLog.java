package com.enviodecorreo.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinatario;
    private String asunto;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    private String fechaEnvio;
}