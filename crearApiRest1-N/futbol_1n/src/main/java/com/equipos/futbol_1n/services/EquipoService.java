package com.equipos.futbol_1n.services;

import com.equipos.futbol_1n.persistance.models.Equipo;
import com.equipos.futbol_1n.persistance.models.Jugador;
import com.equipos.futbol_1n.persistance.repository.EquipoRepository;
import com.equipos.futbol_1n.persistance.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class 
EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Equipo> obtenerEquipos() {
        return equipoRepository.findAll();
    }

    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    public void crearEquipo(Equipo equipo) {
        equipoRepository.save(equipo);
    }

    public void borrarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }

    public void inscribirJugadorEnEquipo(Long idJugador, Long idEquipo) {
        Jugador jugador = jugadorRepository.findById(idJugador).orElse(null);
        Equipo equipo = equipoRepository.findById(idEquipo).orElse(null);
        if (jugador != null && equipo != null) {
            jugador.setEquipo(equipo);
            jugadorRepository.save(jugador);
        }
    }
}
