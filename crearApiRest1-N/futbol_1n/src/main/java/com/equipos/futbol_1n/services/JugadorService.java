package com.equipos.futbol_1n.services;

import com.equipos.futbol_1n.persistance.models.Jugador;
import com.equipos.futbol_1n.persistance.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Jugador> obtenerJugadores() {
        return jugadorRepository.findAll();
    }

    public void crearJugador(Jugador jugador) {
        jugadorRepository.save(jugador);
    }

    public void borrarJugador(Long id) {
        jugadorRepository.deleteById(id);
    }
}
