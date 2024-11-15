/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conn {
    private static Conn uniqueInstance;
    private Connection connection;

	private Conn() {
            try {
            // Substitua pelos detalhes do seu banco de dados
                String connect = "jdbc:postgresql://localhost:5432/postgr2es";
                String user = "postgres";
                String pwd = "fei";
        
                connection =  DriverManager.getConnection(connect, user, pwd);
                System.out.println("PostgreSQL   has been Successfully Connected With Java!");
        
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao conectar ao banco de dados!", e);
            }
	}

	public static synchronized Conn getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Conn();
        }
        return uniqueInstance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Recriar a conexão se ela foi fechada
                String connect = "jdbc:postgresql://localhost:5432/postgr2es";
                String user = "postgres";
                String pwd = "fei";
        
                connection =  DriverManager.getConnection(connect, user, pwd);
                System.out.println("PostgreSQL   has been Successfully Connected With Java!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recriar a conexão com o banco de dados!", e);
        }
        return connection;
    }
}
