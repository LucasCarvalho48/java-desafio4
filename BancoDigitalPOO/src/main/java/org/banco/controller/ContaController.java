package org.banco.controller;

import org.banco.model.Conta;
import org.banco.service.ContaService;

import java.sql.SQLException;
import java.util.Scanner;

public class ContaController {
    public static void criarConta(Scanner scanner) throws SQLException {
        System.out.println("Você pretende criar uma conta poupança ou uma conta corrente?");
        String tipo = scanner.nextLine();
        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();
        System.out.println("Digite a agência:");
        int agencia = Integer.parseInt(scanner.nextLine());
        if (tipo.equalsIgnoreCase("corrente")){
            ContaService.createContaCorrente(agencia,nome,cpf);
        }else {
            ContaService.createContaPoupanca(agencia,nome,cpf);
        }
    }
    public static Conta entrarConta(Scanner scanner) throws SQLException {
        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();
        return ContaService.loginConta(nome, cpf);
    }
    public static void exibirInformacoes(Conta conta){
        System.out.println("Numero da conta: " + conta.getNumero());
        System.out.println("Agência: " + conta.getAgencia());
        System.out.println("Tipo da conta: " + conta.getTipo());
        System.out.println("Saldo: " + conta.getSaldo());
    }
    public static void debositar(Scanner scanner, Conta conta) throws SQLException {
        System.out.println("Informe o valor que será depositado: ");
        double valor = Double.parseDouble(scanner.nextLine());
        ContaService.deposit(valor, conta);
    }
    public static void sacar(Scanner scanner, Conta conta) throws SQLException {
        System.out.println("Informe o valor que será sacado: ");
        double valor = Double.parseDouble(scanner.nextLine());
        ContaService.withdraw(valor, conta);
    }
    public static void transferencia(Scanner scanner, Conta conta) throws SQLException {
        System.out.println("Informe o valor que será transferido: ");
        double valor = Double.parseDouble(scanner.nextLine());
        System.out.println("Informe o número da conta de destino: ");
        int numero = Integer.parseInt(scanner.nextLine());
        ContaService.transfer(numero, valor, conta);
    }
}
