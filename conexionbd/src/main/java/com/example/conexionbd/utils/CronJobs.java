package com.example.conexionbd.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.conexionbd.personas.control.PersonaService;
import com.example.conexionbd.personas.model.Persona;

import java.util.List;

@Component
public class CronJobs {

    private static final Logger logger = LoggerFactory.getLogger(CronJobs.class);
    private final PersonaService personaService;

    @Autowired
    public CronJobs(PersonaService personaService) {
        this.personaService = personaService;
    }

    // Este método se ejecutará cada 2 minutos
    @Scheduled(fixedRate = 120000)
    public void printLastRegisteredPersona() {
        List<Persona> personas = (List<Persona>) personaService.findAllPersonas().getBody().getData();
        if (personas != null && !personas.isEmpty()) {
            Persona lastRegistered = personas.get(personas.size() - 1);
            logger.info("Última persona registrada: {} {}", lastRegistered.getNombre(), lastRegistered.getApellidos());
        } else {
            logger.info("No hay personas registradas.");
        }
    }
}
