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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Carteira;
import model.Investidor;

/**
 *
 * @author manga
 */
public class ControllerSaldo {
    private JFrame view;

    public ControllerSaldo(JFrame view) {
        this.view = view;
    }
    public String Saldo(String pwd){
        
        Carteira investidor = new Carteira(null,null,null,null,"", "",pwd, "");
        
        Conexao conexao = new Conexao();
       
        try {
            Connection conn = conexao.getConnection();
           
            InvestidorDAO dao = new InvestidorDAO(conn);
            
            ResultSet res = dao.read(investidor,pwd);
            
            if (res.next()){
                JOptionPane.showMessageDialog(view, "Acesso liberado!",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                String nome = res.getString("Nome");
                String cpf = res.getString("CPF");
                String senha = res.getString("Senha");
                String idade = res.getString("Idade");
                                
                String reais = res.getString("Real");
                                
                String bitcoin = res.getString("Bitcoin");
                                
                String ripple = res.getString("Ripple");
                                
                String etherum = res.getString("Etherum");
                                
                System.out.println("Nome: " + res.getString("Nome"));
                System.out.println("CPF: " + res.getString("CPF"));
                System.out.println("Idade: " + res.getString("Idade"));

                Carteira c= new Carteira(reais, bitcoin, ripple, etherum, nome, cpf, senha, idade);
                
                String saldo = c.saldo();
                
                return saldo;

                
            } else {
                JOptionPane.showMessageDialog(view, "Acesso negado!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
            } 
             
        } catch(SQLException e){
                    JOptionPane.showMessageDialog(view, "Erro de conexao!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
        return "";
  }
}
