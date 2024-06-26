package org.banco.model;

public class ContaCorrente extends Conta {
    public ContaCorrente(int numero, int agencia, double saldo, int idCliente, String tipo) {
        super(numero, agencia, saldo, idCliente, tipo);
    }
    public void transferir(double valor){
        this.saldo -= valor;
    }
}
