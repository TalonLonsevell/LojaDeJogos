package com.unifap;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente(235.96);
        new Loja(cliente);
    }
}
