package com.api.crud.services;

import com.api.crud.models.Cliente;
import com.api.crud.models.Cuenta;
import com.api.crud.models.Movimiento;
import com.api.crud.models.ReporteEstadoCuentaDTO;
import com.api.crud.repositories.ClienteRepository;
import com.api.crud.repositories.CuentaRepository;
import com.api.crud.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public ReporteEstadoCuentaDTO generarReporte(String fechaInicio, String fechaFin) throws Exception {
        List<Cuenta> cuentas = cuentaRepository.findAll();

        List<ReporteEstadoCuentaDTO.CuentaDTO> cuentasDTO = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = movimientoRepository.findByCuenta_NumeroCuentaAndFechaBetween(
                    cuenta.getNumeroCuenta(), fechaInicio, fechaFin
            );

            List<ReporteEstadoCuentaDTO.CuentaDTO.MovimientoDTO> movimientosDTO = new ArrayList<>();
            for (Movimiento movimiento : movimientos) {
                ReporteEstadoCuentaDTO.CuentaDTO.MovimientoDTO movimientoDTO = new ReporteEstadoCuentaDTO.CuentaDTO.MovimientoDTO();
                movimientoDTO.setFecha(movimiento.getFecha());
                movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
                movimientoDTO.setValor(movimiento.getValor());
                movimientoDTO.setSaldoInicial(movimiento.getSaldoInicial());
                movimientoDTO.setSaldoDisponible(movimiento.getSaldoDisponible());
                movimientosDTO.add(movimientoDTO);
            }

            ReporteEstadoCuentaDTO.CuentaDTO cuentaDTO = new ReporteEstadoCuentaDTO.CuentaDTO();
            cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
            cuentaDTO.setSaldoDisponible(cuenta.getSaldoInicial());
            cuentaDTO.setMovimientos(movimientosDTO);

            cuentasDTO.add(cuentaDTO);
        }

        ReporteEstadoCuentaDTO reporteDTO = new ReporteEstadoCuentaDTO();

        reporteDTO.setCuentas(cuentasDTO);

        return reporteDTO;
    }
}