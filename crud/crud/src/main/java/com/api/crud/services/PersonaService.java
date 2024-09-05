package com.api.crud.services;


import com.api.crud.execptions.ResponseMessage;
import com.api.crud.models.Persona;
import com.api.crud.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona obtenerPersonaPorId(Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (persona.isPresent()) {
            return persona.get();
        } else {
            throw new RuntimeException("Persona no encontrada con el ID: " + id);
        }
    }

    public Persona actualizarPersona(Long id, Persona personaActualizada) {
        Persona personaExistente = obtenerPersonaPorId(id);
        personaExistente.setNombre(personaActualizada.getNombre());
        personaExistente.setGenero(personaActualizada.getGenero());
        personaExistente.setEdad(personaActualizada.getEdad());
        personaExistente.setIdentificacion(personaActualizada.getIdentificacion());
        personaExistente.setDireccion(personaActualizada.getDireccion());
        personaExistente.setTelefono(personaActualizada.getTelefono());
        return personaRepository.save(personaExistente);
    }

    public ResponseMessage eliminarPersona(Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (persona.isPresent()) {
            personaRepository.delete(persona.get());
            return new ResponseMessage("Persona eliminada con éxito.");
        } else {
            return new ResponseMessage("Persona no encontrada.");
        }
    }
}
