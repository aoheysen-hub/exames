package com.example.exame.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TBL_EMPRESAS")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ruc;
    private String direccion;

    // Relación con TBL_ALQUILER (1:N)
    @OneToMany(mappedBy = "empresa")
    private List<Alquiler> alquileres;

    // Getters and setters
}


