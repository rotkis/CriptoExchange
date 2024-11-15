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

import model.Investidor;

/**
 *
 * @author manga
 */
public class InvestidorDAO {
    private Connection conn;

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
    public ResultSet read(Investidor investidor) throws SQLException{
    String sql = "select * from investidor where Senha = ?";
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, InvestidorController.getInvestidorLogado().getSenha());
    statement.execute();
    return statement.getResultSet();
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
        String sql = "update investidor set Senha = ? where Nome = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, investidor.getSenha());
        statement.setString(2, investidor.getNome());
        statement.execute();
        conn.close();
    }
    public void excluir (Investidor investidor) throws SQLException{
        String sql = "delete from investidor where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, investidor.getNome());
        statement.execute();
        conn.close();
    }
}
