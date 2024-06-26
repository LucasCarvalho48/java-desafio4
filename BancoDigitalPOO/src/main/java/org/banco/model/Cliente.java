package org.banco.model;

public class Cliente {
    private int idCliente;
    private String nome;
    private String cpf;

    public Cliente(int idCliente, String nome, String cpf) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdCliente() {
        return idCliente;
    }
}
