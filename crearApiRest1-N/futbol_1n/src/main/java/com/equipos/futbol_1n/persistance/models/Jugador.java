package com.equipos.futbol_1n.persistance.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "jugadores")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idjugador;

    private String nombre;
    private int edad;

    @ManyToOne
    @JoinColumn(name = "equipos_idequipo")
    @JsonBackReference
    private Equipo equipo;
}