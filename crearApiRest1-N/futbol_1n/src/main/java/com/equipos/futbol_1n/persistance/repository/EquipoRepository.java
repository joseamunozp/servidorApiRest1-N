package com.equipos.futbol_1n.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equipos.futbol_1n.persistance.models.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}