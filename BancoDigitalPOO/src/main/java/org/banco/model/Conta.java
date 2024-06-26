package org.banco.model;

public abstract class Conta {
    protected int numero;
    protected int agencia;
    protected double saldo;
    protected int idCliente;
    protected String tipo;

    public Conta(int numero, int agencia, double saldo, int idCliente, String tipo) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
        this.idCliente = idCliente;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }
    // O valor pode ficar negativo
    public void sacar(double valor){
        this.saldo -= valor;
    }
    public void depositar(double valor){
        this.saldo += valor;
    }
}
