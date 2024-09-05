package com.api.crud.services;

import com.api.crud.execptions.SaldoInsuficienteException;
import com.api.crud.models.Cuenta;
import com.api.crud.models.Movimiento;
import com.api.crud.repositories.CuentaRepository;
import com.api.crud.repositories.MovimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Movimiento registrarMovimiento(Movimiento movimiento) throws Exception {
        // Verifica si el tipoMovimiento es nulo
        if (movimiento.getTipoMovimiento() == null || movimiento.getTipoMovimiento().isEmpty()) {
            throw new Exception("El tipo de movimiento no puede estar vacío.");
        }

        // Verifica si la cuenta existe y tiene un cliente asociado
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getNumeroCuenta())
                .orElseThrow(() -> new Exception("La cuenta no existe."));
        if (cuenta.getCliente() == null) {
            throw new Exception("La cuenta no tiene un cliente asociado.");
        }

        // Verifica y actualiza el saldo disponible
        Double saldoDisponible = cuenta.getSaldoInicial();
        if (movimiento.getTipoMovimiento().equalsIgnoreCase("Depósito")) {
            saldoDisponible += movimiento.getValor();
        } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("Retiro")) {
            if (saldoDisponible < movimiento.getValor()) {
                throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro.");
            }
            saldoDisponible -= movimiento.getValor();
        } else {
            throw new Exception("Tipo de movimiento no válido.");
        }

        // Actualiza el saldo de la cuenta
        cuenta.setSaldoInicial(saldoDisponible);
        cuentaRepository.save(cuenta);

        // Establece los saldos inicial y disponible en el movimiento
        movimiento.setSaldoInicial(cuenta.getSaldoInicial());
        movimiento.setSaldoDisponible(saldoDisponible);

        // Registro del movimiento
        movimiento.setCuenta(cuenta); // Asegúrate de que la cuenta esté asociada
        return movimientoRepository.save(movimiento);
    }


    public List<Movimiento> obtenerMovimientosPorCliente(Long clienteId) {
        return movimientoRepository.findByCuenta_Cliente_ClienteId(clienteId);
    }
}