package org.banco.service;

import org.banco.model.Cliente;
import org.banco.model.Conta;
import org.banco.repository.ContaRepository;

import java.sql.SQLException;

public class ContaService {
    public static void createContaPoupanca(int agencia, String nome, String cpf) throws SQLException {
        ContaRepository.saveCliente(nome, cpf);
        Cliente cliente = ContaRepository.selectCliente(nome, cpf);
        ContaRepository.saveContaPoupanca(agencia, cliente.getIdCliente());
    }

    public static void createContaCorrente(int agencia, String nome, String cpf) throws SQLException {
        ContaRepository.saveCliente(nome, cpf);
        Cliente cliente = ContaRepository.selectCliente(nome, cpf);
        ContaRepository.saveContaCorrente(agencia, cliente.getIdCliente());
    }

    public static Conta loginConta(String nome, String cpf) throws SQLException {
        Cliente cliente = ContaRepository.selectCliente(nome, cpf);
        return ContaRepository.selectContaByID(cliente.getIdCliente());
    }
    public static void deposit(double valor, Conta conta) throws SQLException {
        conta.depositar(valor);
        ContaRepository.updateSaldo(conta.getSaldo(), conta.getIdCliente());
    }
    public static void withdraw(double valor, Conta conta) throws SQLException {
        conta.sacar(valor);
        ContaRepository.updateSaldo(conta.getSaldo(), conta.getIdCliente());
    }
    public static void transfer(int numero, double valor,Conta conta) throws SQLException {
        Conta contaDestino = ContaRepository.selectContaByN(numero);
        conta.sacar(valor);
        contaDestino.depositar(valor);
        ContaRepository.updateSaldo(conta.getSaldo(), conta.getIdCliente());
        ContaRepository.updateSaldo(contaDestino.getSaldo(), contaDestino.getIdCliente());
        System.out.println("Transferência realizada com sucesso.");
    }
}
