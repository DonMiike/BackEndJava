package com.api.crud.controllers;

import com.api.crud.models.Cuenta;
import com.api.crud.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        List<Cuenta> cuentas = cuentaService.listarCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @PostMapping
    public ResponseEntity<Cuenta> guardarCuenta(@RequestBody Cuenta cuenta) {
        Cuenta cuentaGuardada = cuentaService.guardarCuenta(cuenta);
        return ResponseEntity.ok(cuentaGuardada);
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        try {
            Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
            return ResponseEntity.ok(cuenta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaActualizada) {
        try {
            Cuenta cuenta = cuentaService.actualizarCuenta(id, cuentaActualizada);
            return ResponseEntity.ok(cuenta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        try {
            cuentaService.eliminarCuenta(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}