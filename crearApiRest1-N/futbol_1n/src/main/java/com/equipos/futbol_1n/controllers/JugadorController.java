package com.equipos.futbol_1n.controllers;

import com.equipos.futbol_1n.persistance.dto.JugadorDTO;
import com.equipos.futbol_1n.persistance.models.Equipo;
import com.equipos.futbol_1n.persistance.models.Jugador;
import com.equipos.futbol_1n.services.EquipoService;
import com.equipos.futbol_1n.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;
    @Autowired
    private EquipoService equipoService;

    @GetMapping
        public List<JugadorDTO> getAllJugadores() {
        List<Jugador> jugadores = jugadorService.obtenerJugadores();
        return jugadores.stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
    }

    private JugadorDTO convertToDTO(Jugador jugador) {
        JugadorDTO dto = new JugadorDTO();
        dto.setIdjugador(jugador.getIdjugador());
        dto.setNombre(jugador.getNombre());
        dto.setEdad(jugador.getEdad());
        if (jugador.getEquipo() != null) {
            dto.setEquipoId(jugador.getEquipo().getIdequipo());
            dto.setEquipoNombre(jugador.getEquipo().getNombre()); // También puedes incluir el nombre del equipo
        }
        return dto;
    }

    @PostMapping
    public void crearJugador(@RequestBody Jugador jugador) {
        jugadorService.crearJugador(jugador);
}

    @DeleteMapping("/{id}")
    public void borrarJugador(@PathVariable Long id) {
        jugadorService.borrarJugador(id);
    }
}
