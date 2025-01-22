package com.example.conexionbd.citas.control;

import com.example.conexionbd.citas.model.Citas;
import com.example.conexionbd.citas.model.CitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitasService {

    @Autowired
    private CitasRepository citasRepository;

    public List<Citas> findAll(){
        return citasRepository.findAll();
    }

    public Citas findById(Long id){
        return citasRepository.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    public List<Citas> findByPersonalId(Long personalId){
        return citasRepository.findByPersonalId(personalId);
    }

    public List<Citas> findByPacientesId(Long pacientesId){
        return citasRepository.findByPacientesId(pacientesId);
    }

    public Citas save(Citas citas){
        return citasRepository.save(citas);
    }

    public void delete(Long id){
        citasRepository.deleteById(id);
    }
}
