package com.equipos.futbol_1n.persistance.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@NoArgsConstructor
@Entity
@Table(name = "equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idequipo;

    private String nombre;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Jugador> jugadores; // Lista de jugadores en el equipo
}