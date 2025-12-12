// Archivo: model/Cliente.java
package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private double saldo;
    private final List<String> historial;

    public Cliente(String nombre, double saldoInicial) {
        this.nombre = nombre;
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
        this.historial.add(String.format("Cuenta creada. Saldo inicial: $%.2f", saldoInicial));
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
        historial.add(String.format("Depósito: +$%.2f | Saldo: $%.2f", monto, saldo));
    }

    public boolean retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            historial.add(String.format("Retiro: -$%.2f | Saldo: $%.2f", monto, saldo));
            return true;
        }
        return false;
    }

    public boolean transferir(String destinatario, double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            historial.add(String.format("Transferencia a %s: -$%.2f | Saldo: $%.2f", destinatario, monto, saldo));
            return true;
        }
        return false;
    }

    public List<String> getHistorial() {
        return new ArrayList<>(historial);
    }

    public void agregarEntradaHistorial(String entrada) {
        historial.add(entrada);
    }
}