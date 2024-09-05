package com.api.crud.services;


import com.api.crud.models.Persona;
import com.api.crud.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }
}

