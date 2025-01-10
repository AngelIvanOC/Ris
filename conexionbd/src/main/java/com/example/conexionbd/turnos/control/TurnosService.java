package com.example.conexionbd.turnos.control;

import com.example.conexionbd.turnos.model.Turnos;
import com.example.conexionbd.turnos.model.TurnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TurnosService {

    @Autowired
    private TurnosRepository turnosRepository;

    public List<Turnos> findAll() {
        return turnosRepository.findAll();
    }

    public List<Turnos> findByPersonalId(Long personalId) {
        return turnosRepository.findByPersonalId(personalId);
    }

    public Turnos findById(Long id) {
        return turnosRepository.findById(id).orElseThrow(() -> new RuntimeException("Turno no encontrado"));
    }

    public Turnos save(Turnos turno) {
        return turnosRepository.save(turno);
    }

    public Turnos update(Long id, Turnos turno) {
        Turnos existingTurno = findById(id);
        existingTurno.setPersonal(turno.getPersonal());
        existingTurno.setFechaHoraLlegada(turno.getFechaHoraLlegada());
        existingTurno.setFechaHoraSalida(turno.getFechaHoraSalida());
        existingTurno.setAsistencia(turno.isAsistencia());
        return turnosRepository.save(existingTurno);
    }

    public Turnos updateAsistencia(Long id, boolean asistencia) {
        Turnos turno = findById(id);
        turno.setAsistencia(asistencia);
        return turnosRepository.save(turno);
    }

    public void delete(Long id) {
        turnosRepository.deleteById(id);
    }

    public Turnos marcarAsistencia(Long personalId) {
        // Buscar el turno del día actual para el miembro del personal
        LocalDateTime inicioDelDia = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = inicioDelDia.plusDays(1).minusSeconds(1);

        Turnos turno = turnosRepository.findByPersonalIdAndFechaHoraLlegadaBetween(personalId, inicioDelDia, finDelDia)
                .orElseThrow(() -> new RuntimeException("No hay turnos registrados para el día de hoy"));

        // Marcar asistencia
        turno.setAsistencia(true);
        return turnosRepository.save(turno);
    }
}
