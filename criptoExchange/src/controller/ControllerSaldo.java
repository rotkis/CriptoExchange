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
    public void Saldo(String pwd){
        
        Investidor investidor = new Investidor(null, null,
                                      pwd, null);
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
            ResultSet res = dao.read(investidor);
            if (res.next()){
                JOptionPane.showMessageDialog(view, "Acesso liberado!",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                String nome = res.getString("Nome");
                String cpf = res.getString("CPF");
                String senha = res.getString("Senha");
                String idade = res.getString("Idade");
                double reais = res.getDouble("Reais");
                double bitcoin = res.getDouble("Bitcoin");
                double ripple = res.getDouble("Ripple");
                double etherum = res.getDouble("Etherum");
                Carteira c= new Carteira(reais, bitcoin, ripple, etherum, nome, cpf, senha, idade);
                
                

                
            } else {
                JOptionPane.showMessageDialog(view, "Login nao efetuado!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
            } 
             
        } catch(SQLException e){
                    JOptionPane.showMessageDialog(view, "Erro de conexao!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
 }
}
