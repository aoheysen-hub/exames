package com.example.exame.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TBL_ALQUILER")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaSalida;
    private LocalDate fechaEntrada;
    private String observacion;

    // Relación con TBL_EMPRESAS (N:1)
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Relación con TBL_DETALLES_ALQUILER (1:N)
    @OneToMany(mappedBy = "alquiler")
    private List<DetalleAlquiler> detallesAlquiler;

    // Getters and setters
}


