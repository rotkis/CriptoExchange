/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author manga
 */
public class Conexao {
    public Connection getConnection() throws SQLException{
        Connection conexao = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/aluno",
                "alunos", "fei");
        return conexao;
    }
}
