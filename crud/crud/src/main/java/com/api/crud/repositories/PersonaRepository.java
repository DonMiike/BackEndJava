package com.api.crud.repositories;

import com.api.crud.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository  extends JpaRepository<Persona,Long> {
}
