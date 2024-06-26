package org.banco.application;

import org.banco.controller.ContaController;
import org.banco.model.Conta;
import org.banco.model.ContaCorrente;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int menuControle1 = 1;
        String menu1 = """ 
                Menu:
                1. Criar Conta.
                2. Entrar.
                3. Sair.
                """;
        String menu2 = """ 
                Menu:
                1. Dados da conta.
                2. Depositar.
                3. Sacar.
                4. Transferir.
                5. Sair.
                """;
        while (menuControle1 != 3) {
            System.out.println(menu1);
            menuControle1 = Integer.parseInt(scanner.nextLine());

            switch (menuControle1) {
                case 1:
                    ContaController.criarConta(scanner);
                    break;
                case 2:
                    int menuControle2 = 1;
                    Conta conta = ContaController.entrarConta(scanner);
                    while (menuControle2 != 5) {
                        System.out.println(menu2);
                        menuControle2 = Integer.parseInt(scanner.nextLine());
                        switch (menuControle2) {
                            case 1:
                                ContaController.exibirInformacoes(conta);
                                break;
                            case 2:
                                ContaController.debositar(scanner, conta);
                                break;
                            case 3:
                                ContaController.sacar(scanner, conta);
                                break;
                            case 4:
                                if (conta instanceof ContaCorrente) {
                                    ContaController.transferencia(scanner, conta);
                                } else
                                    System.out.println("Sua conta não permite a realização de transferências; somente contas correntes têm essa funcionalidade.");
                                break;
                            case 5:
                                System.out.println("Saindo da conta...");
                                break;
                        }
                    }
                    break;
                case 3:
                    break;
            }
        }
    }
}
