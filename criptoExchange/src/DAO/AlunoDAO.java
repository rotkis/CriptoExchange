/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.Aluno;

/**
 *
 * @author manga
 */
public class AlunoDAO {
    private Connection conn;

    public AlunoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Aluno aluno) throws SQLException{
        // esse comando é vulneravel
        //String sql = "select * from aluno where usuario = '"
        //        + aluno.getUsuario() + "' AND senha = '"
        //        + aluno.getSenha() +"'";
        String sql = "select * from alunos where CPF = ? AND Senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,aluno.getCpf());
        statement.setString(2,aluno.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    public void inserir(Aluno aluno) throws SQLException{
        String sql = "insert into alunos (Nome, CPF, Senha, Idade) values ('"
                + aluno.getNome()    + "', '" 
                + aluno.getCpf() + "', '"
                + aluno.getSenha() + "', '"
                + aluno.getIdade()   + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    public void atualizar(Aluno aluno) throws SQLException{
        String sql = "update alunos set Senha = ? where Nome = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, aluno.getSenha());
        statement.setString(2, aluno.getNome());
        statement.execute();
        conn.close();
    }
    public void excluir (Aluno aluno) throws SQLException{
        String sql = "delete from alunos where usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, aluno.getNome());
        statement.execute();
        conn.close();
    }
}
