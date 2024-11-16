/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import controller.InvestidorController;
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
public class InvestidorDAO {
    private final Connection conn;

    public InvestidorDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Investidor investidor) throws SQLException{
        // esse comando é vulneravel
        //String sql = "select * from aluno where usuario = '"
        //        + aluno.getUsuario() + "' AND senha = '"
        //        + aluno.getSenha() +"'";
        String sql = "select * from investidor where CPF = ? AND Senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,investidor.getCpf());
        statement.setString(2,investidor.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    public ResultSet read(Carteira investidor, String senha) throws SQLException{
    String sql = "SELECT * FROM investidor WHERE Senha = ?";
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, senha);
    return statement.executeQuery();
    }
    public void inserir(Investidor investidor) throws SQLException{
        String sql = "insert into investidor (Nome, CPF, Senha, Idade) values ('"
                + investidor.getNome() + "', '" 
                + investidor.getCpf() + "', '"
                + investidor.getSenha() + "', '"
                + investidor.getIdade()   + "')";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    public void atualizar(Investidor investidor) throws SQLException{
        String sql = "update investidor set Senha = ? where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, investidor.getSenha());
        statement.setString(2, investidor.getCpf());
        statement.execute();
        conn.close();
    }
    public void excluir (Carteira investidor,String cpf) throws SQLException{
        String sql = "delete from investidor where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.execute();
        conn.close();
    }
    public void excluirExtrato (Carteira investidor,String cpf) throws SQLException{
        String sql = "delete from extrato where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.execute();
        conn.close();
    }
    public void registrarExtrato(Carteira investidor, String cpf, String descricao, double valor) throws SQLException {
        String sql = "INSERT INTO extrato (nome, CPF, senha, idade, real, ripple, bitcoin, etherum,Descricao, Valor, Data) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, investidor.getNome());
        statement.setString(2, cpf);
        statement.setString(3, investidor.getSenha());
        statement.setString(4, investidor.getIdade());
        statement.setString(5, investidor.getReais());
        statement.setString(6, investidor.getRipple());
        statement.setString(7, investidor.getBitcoin());
        statement.setString(8, investidor.getEtherum());
        statement.setString(9, descricao);
        statement.setDouble(10, valor);
        statement.execute();
    }

    // Atualizar saldo do investidor
    public void atualizarSaldoMoeda(String cpf, String moeda, String novoSaldo) throws SQLException {
    String sql = "UPDATE investidor SET " + moeda + " = ? WHERE CPF = ?";
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, novoSaldo);
    statement.setString(2, cpf);
    statement.execute();
}

    // Consultar saldo atual
    public ResultSet consultarSaldo(String cpf) throws SQLException {
        String sql = "SELECT * FROM investidor WHERE CPF = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpf);
        return statement.executeQuery();
    }

    // Consultar extrato
   public ResultSet consultarExtrato(String cpf) throws SQLException {
        String sql = "SELECT * FROM extrato WHERE CPF = ? ORDER BY Data DESC";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpf);
        return statement.executeQuery(); // executeQuery já retorna o ResultSet
    } 
}
