package com.api.crud.repositories;

import com.api.crud.models.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuenta_Cliente_ClienteId(Long clienteId);
    List<Movimiento> findByCuenta_NumeroCuentaAndFechaBetween(Long numeroCuenta, String fechaInicio, String fechaFin);
}