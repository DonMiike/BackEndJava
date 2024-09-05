package com.api.crud.controllers;

import com.api.crud.models.Persona;
import com.api.crud.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> listarPersonas() {
        return personaService.listarPersonas();
    }

    @PostMapping
    public ResponseEntity<Persona> guardarPersona(@RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.guardarPersona(persona));
    }
}
