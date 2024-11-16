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
    private static Conexao uniqueInstance;
    public static Connection getConnection() throws SQLException{
        String connect = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pwd = "fei";
        
        Connection conexao =  DriverManager.getConnection(connect, user, pwd);
        //System.out.println("PostgreSQL   has been Successfully Connected With Java!");
        return conexao;
    }
    
    public static synchronized Conexao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Conexao();
        }
        return uniqueInstance;
    }
}
