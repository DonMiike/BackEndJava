package com.api.crud.controllers;


import com.api.crud.models.Movimiento;
import com.api.crud.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("Fecha", nuevoMovimiento.getFecha());
            respuesta.put("Cliente", nuevoMovimiento.getCuenta().getCliente().getPersona().getNombre());
            respuesta.put("Numero Cuenta", nuevoMovimiento.getCuenta().getNumeroCuenta());
            respuesta.put("Tipo", nuevoMovimiento.getCuenta().getTipoCuenta());
            respuesta.put("Saldo Inicial", nuevoMovimiento.getSaldoInicial());
            respuesta.put("Movimiento", nuevoMovimiento.getValor());
            respuesta.put("Saldo Disponible", nuevoMovimiento.getSaldoDisponible());
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(movimientoService.obtenerMovimientosPorCliente(clienteId));
    }
}
