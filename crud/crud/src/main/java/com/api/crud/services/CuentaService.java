package com.api.crud.services;

import com.api.crud.models.Cuenta;
import com.api.crud.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta guardarCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        if (cuenta.isPresent()) {
            return cuenta.get();
        } else {
            throw new RuntimeException("Cuenta no encontrada con el ID: " + id);
        }
    }

    public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) {
        Cuenta cuentaExistente = obtenerCuentaPorId(id);
        cuentaExistente.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        cuentaExistente.setSaldoInicial(cuentaActualizada.getSaldoInicial());
        cuentaExistente.setEstado(cuentaActualizada.getEstado());
        return cuentaRepository.save(cuentaExistente);
    }

    public void eliminarCuenta(Long id) {
        Cuenta cuenta = obtenerCuentaPorId(id);
        cuentaRepository.delete(cuenta);
    }
}
