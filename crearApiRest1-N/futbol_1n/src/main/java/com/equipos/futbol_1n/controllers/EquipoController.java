package com.equipos.futbol_1n.controllers;

import com.equipos.futbol_1n.persistance.models.Equipo;
import com.equipos.futbol_1n.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipo> obtenerEquipos() {
        return equipoService.obtenerEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Long id) {
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        return equipo != null ? ResponseEntity.ok(equipo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public void crearEquipo(@RequestBody Equipo equipo) {
        equipoService.crearEquipo(equipo);
    }

    @DeleteMapping("/{id}")
    public void borrarEquipo(@PathVariable Long id) {
        equipoService.borrarEquipo(id);
    }

    @PostMapping("/{idEquipo}/inscribir/{idJugador}")
    public void inscribirJugadorEnEquipo(@PathVariable Long idJugador, @PathVariable Long idEquipo) {
        equipoService.inscribirJugadorEnEquipo(idJugador, idEquipo);
    }
}
