package org.banco.repository;

import org.banco.model.Cliente;
import org.banco.model.Conta;
import org.banco.model.ContaCorrente;
import org.banco.model.ContaPoupanca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.banco.util.DatabaseUtil.closeConnection;
import static org.banco.util.DatabaseUtil.openConnection;

public class ContaRepository {
    
    public static void saveCliente(String nome, String cpf) throws SQLException {
        Connection con = openConnection();
        String sql = "INSERT INTO CLIENTE (NOME, CPF) VALUES (?, ?);";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1, nome);
        psmt.setString(2, cpf);
        int rowsAffected = psmt.executeUpdate();
        closeConnection(con);
        if (rowsAffected > 0) {
            System.out.println("Cliente Criado");
        } else {
            System.out.println("Erro ao inserir cliente.");
        }
    }

    public static void saveContaCorrente(int agencia, int idCliente) throws SQLException {
        Connection con = openConnection();
        String sql = "INSERT INTO CONTA (AGENCIA, SALDO, TIPO, ID_CLIENTE) VALUES (?, 0, 'corrente', ?);";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1, agencia);
        psmt.setInt(2, idCliente);
        int rowsAffected = psmt.executeUpdate();
        closeConnection(con);
        if (rowsAffected > 0) {
            System.out.println("Conta Criada");
        } else {
            System.out.println("Erro ao inserir cliente.");
        }
    }

    public static void saveContaPoupanca(int agencia, int idCliente) throws SQLException {
        Connection con = openConnection();
        String sql = "INSERT INTO CONTA (AGENCIA, SALDO, TIPO, ID_CLIENTE) VALUES (?, 0, 'poupança', ?);";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1, agencia);
        psmt.setInt(2, idCliente);
        int rowsAffected = psmt.executeUpdate();
        closeConnection(con);
        if (rowsAffected > 0) {
            System.out.println("Conta Criada");
        } else {
            System.out.println("Erro ao inserir cliente.");
        }
    }

    public static Conta selectContaByID(int idCliente) throws SQLException {
        Connection con = openConnection();
        String sql = "SELECT * FROM CONTA WHERE ID_CLIENTE = ?;";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1, idCliente);
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            String tipo = rs.getString("TIPO");
            double saldo = rs.getDouble("SALDO");
            int numero = rs.getInt("NUMERO");
            int agencia = rs.getInt("AGENCIA");
            closeConnection(con);
            if (tipo.equals("corrente")) return new ContaCorrente(numero, agencia, saldo, idCliente, tipo);
            else return new ContaPoupanca(numero, agencia, saldo, idCliente, tipo);
        } else {
            closeConnection(con);
            throw new SQLException("Cliente não encontrado.");
        }
    }
    public static Conta selectContaByN(int numero) throws SQLException {
        Connection con = openConnection();
        String sql = "SELECT * FROM CONTA WHERE NUMERO = ?;";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1, numero);
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            String tipo = rs.getString("TIPO");
            double saldo = rs.getDouble("SALDO");
            int idCliente = rs.getInt("ID_CLIENTE");
            int agencia = rs.getInt("AGENCIA");
            closeConnection(con);
            if (tipo.equals("corrente")) return new ContaCorrente(numero, agencia, saldo, idCliente, tipo);
            else return new ContaPoupanca(numero, agencia, saldo, idCliente, tipo);
        } else {
            closeConnection(con);
            throw new SQLException("Cliente não encontrado.");
        }
    }

    public static Cliente selectCliente(String nome, String cpf) throws SQLException {
        Connection con = openConnection();
        String sql = "SELECT * FROM CLIENTE WHERE NOME = ? AND CPF  = ?;";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1, nome);
        psmt.setString(2, cpf);
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            int idCliente = rs.getInt("ID_CLIENTE");
            closeConnection(con);
            return new Cliente(idCliente, nome, cpf);
        } else {
            closeConnection(con);
            throw new SQLException("Cliente não encontrado.");
        }
    }

    public static void updateSaldo(double saldoNovo, int idCliente) throws SQLException {
        Connection con = openConnection();
        String sql = "UPDATE CONTA SET SALDO = ? WHERE ID_CLIENTE = ?;";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setDouble(1, saldoNovo);
        psmt.setInt(2, idCliente);
        int rowsAffected = psmt.executeUpdate();
        closeConnection(con);
        if (rowsAffected > 0) {
            System.out.println("Saldo Atualizado.");
        } else {
            System.out.println("Erro ao atualizar o saldo.");
        }
    }

    public static void deleteCliente(int numero) throws SQLException {
        Connection con = openConnection();
        String sql = "DELETE FROM CONTA WHERE NUMERO = ?;";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1, numero);
        int rowsAffected = psmt.executeUpdate();
        closeConnection(con);
        if (rowsAffected > 0) {
            System.out.println("Cliente deletada.");
        } else {
            System.out.println("Erro ao deletar cliente.");
        }
    }

    public static void deleteConta(int numero) throws SQLException {
        Connection con = openConnection();
        String sql = "DELETE FROM CONTA WHERE NUMERO = ?;";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1, numero);
        int rowsAffected = psmt.executeUpdate();
        closeConnection(con);
        if (rowsAffected > 0) {
            System.out.println("Conta deletada.");
        } else {
            System.out.println("Erro ao deletar conta.");
        }
    }

}
