package com.unifap;

public class Cliente {
    private double saldo;

    public Cliente(double saldo) {
        this.saldo = saldo;
    }

    public void compra(Jogos j) {
        if (this.saldo < j.getPreco()) {
            throw new IllegalArgumentException("SALDO INSUFICIENTE");
        } else {
            this.saldo -= j.getPreco();
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
