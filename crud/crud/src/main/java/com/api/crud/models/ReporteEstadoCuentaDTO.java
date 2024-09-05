package com.api.crud.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReporteEstadoCuentaDTO {

    private Long clienteId;
    private List<CuentaDTO> cuentas;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }

    public static class CuentaDTO {
        private Long numeroCuenta;
        private String tipoCuenta;
        private Double saldoInicial;
        private Double saldoDisponible;
        private List<MovimientoDTO> movimientos;

        public Long getNumeroCuenta() {
            return numeroCuenta;
        }

        public void setNumeroCuenta(Long numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
        }

        public String getTipoCuenta() {
            return tipoCuenta;
        }

        public void setTipoCuenta(String tipoCuenta) {
            this.tipoCuenta = tipoCuenta;
        }

        public Double getSaldoInicial() {
            return saldoInicial;
        }

        public void setSaldoInicial(Double saldoInicial) {
            this.saldoInicial = saldoInicial;
        }

        public Double getSaldoDisponible() {
            return saldoDisponible;
        }

        public void setSaldoDisponible(Double saldoDisponible) {
            this.saldoDisponible = saldoDisponible;
        }

        public List<MovimientoDTO> getMovimientos() {
            return movimientos;
        }

        public void setMovimientos(List<MovimientoDTO> movimientos) {
            this.movimientos = movimientos;
        }

        public static class MovimientoDTO {
            private String fecha;
            private String tipoMovimiento;
            private Double valor;
            private Double saldoInicial;
            private Double saldoDisponible;

            public String getFecha() {
                return fecha;
            }

            public void setFecha(String fecha) {
                this.fecha = fecha;
            }

            public String getTipoMovimiento() {
                return tipoMovimiento;
            }

            public void setTipoMovimiento(String tipoMovimiento) {
                this.tipoMovimiento = tipoMovimiento;
            }

            public Double getValor() {
                return valor;
            }

            public void setValor(Double valor) {
                this.valor = valor;
            }

            public Double getSaldoInicial() {
                return saldoInicial;
            }

            public void setSaldoInicial(Double saldoInicial) {
                this.saldoInicial = saldoInicial;
            }

            public Double getSaldoDisponible() {
                return saldoDisponible;
            }

            public void setSaldoDisponible(Double saldoDisponible) {
                this.saldoDisponible = saldoDisponible;
            }
        }
    }
}