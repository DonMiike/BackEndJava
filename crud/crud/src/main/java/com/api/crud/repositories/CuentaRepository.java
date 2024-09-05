package com.api.crud.repositories;

import com.api.crud.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByCliente_ClienteId(Long clienteId);
}
