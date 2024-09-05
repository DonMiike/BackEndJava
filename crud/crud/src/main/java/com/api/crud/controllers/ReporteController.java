package com.api.crud.controllers;


import com.api.crud.models.ReporteEstadoCuentaDTO;
import com.api.crud.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<?> generarReporte(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin
    ) {
        try {
            ReporteEstadoCuentaDTO reporte = reporteService.generarReporte(fechaInicio, fechaFin);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
