/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.InvestidorDAO;
import javax.swing.JOptionPane;
import view.DepositoView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.Carteira;
import model.Investidor;

/**
 *
 * @author manga
 */
public class ControllerCi {
    private InvestidorDAO investidorDAO;
    private Conexao conn;
    private Carteira investidor;

    public ControllerCi() {
        
        investidor = InvestidorController.getInvestidorLogado();
    }
    
    public void deposito(double valor, String moeda) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);

            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "Valor inválido para depósito!");
                return;
            }

            ResultSet resultado = investidorDAO.consultarSaldo(investidor.getCpf());
            if (resultado.next()) {
                // Busca o saldo atual da moeda selecionada
                double saldoAtual = Double.parseDouble(resultado.getString(moeda));
                double novoSaldo = saldoAtual + valor;
                investidor.setReais(String.valueOf(novoSaldo));
                // Atualiza o saldo
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), moeda, String.valueOf(novoSaldo));

                // Registra no extrato
                investidorDAO.registrarExtrato(investidor, investidor.getCpf(), "Depósito (" + moeda + ")", valor);

                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso! Novo saldo de " + moeda + ": " + novoSaldo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar depósito: " + e.getMessage());
        }
    }


    public void saque(double valor, String moeda) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);

            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "Valor inválido para saque!");
                return;
            }

            ResultSet resultado = investidorDAO.consultarSaldo(investidor.getCpf());
            if (resultado.next()) {
                // Busca o saldo atual da moeda selecionada
                double saldoAtual = Double.parseDouble(resultado.getString(moeda));

                if (valor > saldoAtual) {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente para " + moeda + "!");
                    return;
                }

                double novoSaldo = saldoAtual - valor;
                investidor.setReais(String.valueOf(novoSaldo));
                // Atualiza o saldo
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), moeda, String.valueOf(novoSaldo));

                // Registra no extrato
                investidorDAO.registrarExtrato(investidor, investidor.getCpf(), "Saque (" + moeda + ")", -valor);

                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso! Novo saldo de " + moeda + ": " + novoSaldo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar saque: " + e.getMessage());
        }
    }


    public void consultarExtrato(String senha) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            // Valida a senha
            if (!investidor.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Consulta o extrato
            
            System.out.println(investidor.getCpf());
            ResultSet extrato = investidorDAO.consultarExtrato(investidor.getCpf());
            
            StringBuilder historico = new StringBuilder("Nome: "+investidor.getNome()+"\nExtrato:\n");

            while (extrato.next()) {
                String data = extrato.getString("Data");
                String descricao = extrato.getString("Descricao");
                double valor = extrato.getDouble("Valor");
                double reais = Double.parseDouble(extrato.getString("Real"));
                double ripple = Double.parseDouble(extrato.getString("Ripple"));
                double bitcoin = Double.parseDouble(extrato.getString("Bitcoin"));
                double etherum = Double.parseDouble(extrato.getString("Etherum"));

                historico.append(String.format(
                    "%s - %s: R$ %.2f\nSaldo após a operação:\nReais: R$ %.2f | Ripple: %.6f XRP | Bitcoin: %.8f BTC | Ethereum: %.8f ETH\n\n",
                    data, descricao, valor, reais, ripple, bitcoin, etherum
                ));
            }

            JOptionPane.showMessageDialog(null, historico.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao consultar extrato: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void consultarSaldo(String senha) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            if (!investidor.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ResultSet resultado = investidorDAO.consultarSaldo(investidor.getCpf());
            if (resultado.next()) {
                double reais = Double.parseDouble(resultado.getString("Real"));
                double ripple = Double.parseDouble(resultado.getString("Ripple"));
                double bitcoin = Double.parseDouble(resultado.getString("Bitcoin"));
                double etherum = Double.parseDouble(resultado.getString("Etherum"));

                String mensagem = String.format(
                    "Saldos:\nReais: R$ %.2f\nRipple: %.6f XRP\nBitcoin: %.8f BTC\nEthereum: %.8f ETH",
                    reais, ripple, bitcoin, etherum
                );
                JOptionPane.showMessageDialog(null, mensagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao consultar saldos: " + e.getMessage());
        }
    }
}
