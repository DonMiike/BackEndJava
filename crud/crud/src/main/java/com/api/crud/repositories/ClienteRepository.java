package com.api.crud.repositories;

import com.api.crud.models.Cliente;
import com.api.crud.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    }
}