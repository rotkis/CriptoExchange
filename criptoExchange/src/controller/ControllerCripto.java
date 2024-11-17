/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.InvestidorDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Bitcoin;
import model.Carteira;
import model.Etherum;
import model.Moedas;
import model.Ripple;

/**
 *
 * @author manga
 */
public class ControllerCripto {
    private Etherum e;
    private InvestidorDAO investidorDAO;
    private Conexao conn;
    private Carteira investidor;
    public ControllerCripto() {
        investidor = InvestidorController.getInvestidorLogado();
    }
    public void comprarEtherum(String senha ){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            // Valida a senha
            if (!investidor.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String valor = JOptionPane.showInputDialog("Digite a quantidade de compra de Etherum: ");
            ResultSet resultado = investidorDAO.consultarSaldo(investidor.getCpf());
            if (resultado.next()) {
                
            double saldoAtual = Double.parseDouble(resultado.getString("Real"));
            double quantidadeAtualEthereum = Double.parseDouble(resultado.getString("Etherum"));

            // Cálculo do custo total
            double custoTotal = Etherum.compraTaxa() * Double.parseDouble(valor)* investidorDAO.consultarCotacao();
                if (saldoAtual < custoTotal) {
                    JOptionPane.showMessageDialog(null, "Valor inválido para compra!"); 
                    return;
                }
            double novoSaldo = saldoAtual - custoTotal;
            double novaQuantidadeEthereum = quantidadeAtualEthereum + Double.parseDouble(valor);
            investidor.setReais(String.valueOf(novoSaldo));
            investidor.setEtherum(String.valueOf(novaQuantidadeEthereum));
            investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Real",String.valueOf(novoSaldo));
            investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Etherum", String.valueOf(novaQuantidadeEthereum));
            investidorDAO.registrarExtrato(investidor, investidor.getCpf(), "Compra (Etherum)", novaQuantidadeEthereum);

            JOptionPane.showMessageDialog(null, String.format(
                    "Compra realizada com sucesso!\nNovo saldo: R$ %.2f\nQuantidade de Ethereum: %.8f ETH",
                    novoSaldo, novaQuantidadeEthereum
            ));
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao consultar extrato: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void comprarBitcoin(String senha) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            if (!investidor.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String valor = JOptionPane.showInputDialog("Digite a quantidade de compra de Bitcoin: ");
            ResultSet resultado = investidorDAO.consultarSaldo(investidor.getCpf());
            if (resultado.next()) {
                double saldoAtual = Double.parseDouble(resultado.getString("Real"));
                double quantidadeAtualBitcoin = Double.parseDouble(resultado.getString("Bitcoin"));

                double custoTotal = Bitcoin.compraTaxa() * Double.parseDouble(valor) * investidorDAO.consultarCotacao();
                if (saldoAtual < custoTotal) {
                    JOptionPane.showMessageDialog(null, "Valor inválido para compra!");
                    return;
                }
                double novoSaldo = saldoAtual - custoTotal;
                double novaQuantidadeBitcoin = quantidadeAtualBitcoin + Double.parseDouble(valor);
                investidor.setReais(String.valueOf(novoSaldo));
                investidor.setBitcoin(String.valueOf(novaQuantidadeBitcoin));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Real", String.valueOf(novoSaldo));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Bitcoin", String.valueOf(novaQuantidadeBitcoin));
                investidorDAO.registrarExtrato(investidor, investidor.getCpf(), "Compra (Bitcoin)", novaQuantidadeBitcoin);

                JOptionPane.showMessageDialog(null, String.format(
                        "Compra realizada com sucesso!\nNovo saldo: R$ %.2f\nQuantidade de Bitcoin: %.8f BTC",
                        novoSaldo, novaQuantidadeBitcoin
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao consultar extrato: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Compra de Ripple
    public void comprarRipple(String senha) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            if (!investidor.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String valor = JOptionPane.showInputDialog("Digite a quantidade de compra de Ripple: ");
            ResultSet resultado = investidorDAO.consultarSaldo(investidor.getCpf());
            if (resultado.next()) {
                double saldoAtual = Double.parseDouble(resultado.getString("Real"));
                double quantidadeAtualRipple = Double.parseDouble(resultado.getString("Ripple"));

                double custoTotal = Ripple.compraTaxa() * Double.parseDouble(valor)* investidorDAO.consultarCotacao();
                if (saldoAtual < custoTotal) {
                    JOptionPane.showMessageDialog(null, "Valor inválido para compra!");
                    return;
                }
                double novoSaldo = saldoAtual - custoTotal;
                double novaQuantidadeRipple = quantidadeAtualRipple + Double.parseDouble(valor);
                investidor.setReais(String.valueOf(novoSaldo));
                investidor.setRipple(String.valueOf(novaQuantidadeRipple));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Real", String.valueOf(novoSaldo));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Ripple", String.valueOf(novaQuantidadeRipple));
                investidorDAO.registrarExtrato(investidor, investidor.getCpf(), "Compra (Ripple)", novaQuantidadeRipple);

                JOptionPane.showMessageDialog(null, String.format(
                        "Compra realizada com sucesso!\nNovo saldo: R$ %.2f\nQuantidade de Ripple: %.8f XRP",
                        novoSaldo, novaQuantidadeRipple
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao consultar extrato: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void venderEtherum(String senha) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            // Valida a senha
            if (!investidor.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String valor = JOptionPane.showInputDialog("Digite a quantidade de venda de Etherum: ");
            ResultSet resultado = investidorDAO.consultarSaldo(investidor.getCpf());
            if (resultado.next()) {
                double saldoAtual = Double.parseDouble(resultado.getString("Real"));
                double quantidadeAtualEthereum = Double.parseDouble(resultado.getString("Etherum"));

                double quantidadeVenda = Double.parseDouble(valor);

                // Verifica se há quantidade suficiente para venda
                if (quantidadeAtualEthereum < quantidadeVenda) {
                    JOptionPane.showMessageDialog(null, "Quantidade insuficiente de Ethereum para venda!");
                    return;
                }

                // Cálculo do valor recebido
                double valorRecebido = quantidadeVenda * Etherum.vendaTaxa()* investidorDAO.consultarCotacao();
                double novoSaldo = saldoAtual + valorRecebido;
                double novaQuantidadeEthereum = quantidadeAtualEthereum - quantidadeVenda;

                // Atualiza os saldos
                investidor.setReais(String.valueOf(novoSaldo));
                investidor.setEtherum(String.valueOf(novaQuantidadeEthereum));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Real", String.valueOf(novoSaldo));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Etherum", String.valueOf(novaQuantidadeEthereum));
                investidorDAO.registrarExtrato(investidor, investidor.getCpf(), "Venda (Etherum)", quantidadeVenda);

                JOptionPane.showMessageDialog(null, String.format(
                        "Venda realizada com sucesso!\nNovo saldo: R$ %.2f\nQuantidade de Ethereum: %.8f ETH",
                        novoSaldo, novaQuantidadeEthereum
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Venda de Bitcoin
    public void venderBitcoin(String senha) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            if (!investidor.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String valor = JOptionPane.showInputDialog("Digite a quantidade de venda de Bitcoin: ");
            ResultSet resultado = investidorDAO.consultarSaldo(investidor.getCpf());
            if (resultado.next()) {
                double saldoAtual = Double.parseDouble(resultado.getString("Real"));
                double quantidadeAtualBitcoin = Double.parseDouble(resultado.getString("Bitcoin"));

                double quantidadeVenda = Double.parseDouble(valor);

                if (quantidadeAtualBitcoin < quantidadeVenda) {
                    JOptionPane.showMessageDialog(null, "Quantidade insuficiente de Bitcoin para venda!");
                    return;
                }

                double valorRecebido = quantidadeVenda * Bitcoin.vendaTaxa()* investidorDAO.consultarCotacao();
                double novoSaldo = saldoAtual + valorRecebido;
                double novaQuantidadeBitcoin = quantidadeAtualBitcoin - quantidadeVenda;

                investidor.setReais(String.valueOf(novoSaldo));
                investidor.setBitcoin(String.valueOf(novaQuantidadeBitcoin));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Real", String.valueOf(novoSaldo));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Bitcoin", String.valueOf(novaQuantidadeBitcoin));
                investidorDAO.registrarExtrato(investidor, investidor.getCpf(), "Venda (Bitcoin)", quantidadeVenda);

                JOptionPane.showMessageDialog(null, String.format(
                        "Venda realizada com sucesso!\nNovo saldo: R$ %.2f\nQuantidade de Bitcoin: %.8f BTC",
                        novoSaldo, novaQuantidadeBitcoin
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Venda de Ripple
    public void venderRipple(String senha) {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            if (!investidor.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String valor = JOptionPane.showInputDialog("Digite a quantidade de venda de Ripple: ");
            ResultSet resultado = investidorDAO.consultarSaldo(investidor.getCpf());
            if (resultado.next()) {
                double saldoAtual = Double.parseDouble(resultado.getString("Real"));
                double quantidadeAtualRipple = Double.parseDouble(resultado.getString("Ripple"));

                double quantidadeVenda = Double.parseDouble(valor);

                if (quantidadeAtualRipple < quantidadeVenda) {
                    JOptionPane.showMessageDialog(null, "Quantidade insuficiente de Ripple para venda!");
                    return;
                }

                double valorRecebido = quantidadeVenda * (Ripple.vendaTaxa() * investidorDAO.consultarCotacao());
                double novoSaldo = saldoAtual + valorRecebido;
                double novaQuantidadeRipple = quantidadeAtualRipple - quantidadeVenda;

                investidor.setReais(String.valueOf(novoSaldo));
                investidor.setRipple(String.valueOf(novaQuantidadeRipple));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Real", String.valueOf(novoSaldo));
                investidorDAO.atualizarSaldoMoeda(investidor.getCpf(), "Ripple", String.valueOf(novaQuantidadeRipple));
                investidorDAO.registrarExtrato(investidor, investidor.getCpf(), "Venda (Ripple)", quantidadeVenda);

                JOptionPane.showMessageDialog(null, String.format(
                        "Venda realizada com sucesso!\nNovo saldo: R$ %.2f\nQuantidade de Ripple: %.8f XRP",
                        novoSaldo, novaQuantidadeRipple
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String atualizarCotacao(){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            investidorDAO.atualizarCotacao(Moedas.cotacao());
            
            return String.format("%.2f%%", (investidorDAO.consultarCotacao()- 1) * 100);
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar depósito: " + e.getMessage());
        }
        return "";
    }
    
    public String lerCotacao(){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            investidorDAO = new InvestidorDAO(conn);
            investidorDAO.atualizarCotacao(Moedas.cotacao());
            
            return String.format("%.2f%%", (investidorDAO.consultarCotacao()- 1) * 100);
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar depósito: " + e.getMessage());
        }
        return "";
    }
}
