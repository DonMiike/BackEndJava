package com.api.crud.controllers;

import com.api.crud.execptions.ResponseMessage;
import com.api.crud.models.Persona;
import com.api.crud.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Persona>> listarPersonas() {
        List<Persona> personas = personaService.listarPersonas();
        return ResponseEntity.ok(personas);
    }

    @PostMapping
    public ResponseEntity<Persona> guardarPersona(@RequestBody Persona persona) {
        Persona personaGuardada = personaService.guardarPersona(persona);
        return ResponseEntity.ok(personaGuardada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        try {
            Persona persona = personaService.obtenerPersonaPorId(id);
            return ResponseEntity.ok(persona);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona personaActualizada) {
        try {
            Persona persona = personaService.actualizarPersona(id, personaActualizada);
            return ResponseEntity.ok(persona);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> eliminarPersona(@PathVariable Long id) {
        ResponseMessage response = personaService.eliminarPersona(id);
        if (response.getMessage().equals("Persona eliminada con éxito.")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}