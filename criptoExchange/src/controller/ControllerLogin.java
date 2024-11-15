/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.InvestidorDAO;
import view.LoginView;
import java.sql.Connection;
import DAO.Conexao;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.JFrame;
import model.Investidor;
import view.InvestidorView;
/**
 *
 * @author manga
 */
public class ControllerLogin {
    private LoginView view;
    


    public ControllerLogin(LoginView view) {
        this.view = view; 
    }
   
    
    public void loginAluno(){
        Investidor investidor = new Investidor(null, view.getTxtCpf().getText(),
                                      view.getTxtSenha().getText(), null);
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
            ResultSet res = dao.consultar(investidor);
            if (res.next()){
                JOptionPane.showMessageDialog(view, "Login efetuado!",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                String nome = res.getString("Nome");
                String cpf = res.getString("CPF");
                String senha = res.getString("Senha");
                String idade = res.getString("Idade");
                Investidor investidorLogado = new Investidor(nome, cpf, senha, idade);
                InvestidorController.setInvestidorLogado(investidorLogado);
                InvestidorController c = new InvestidorController(view);
                c.menu();

                
            } else {
                JOptionPane.showMessageDialog(view, "Login nao efetuado!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
            } 
             
        } catch(SQLException e){
                    JOptionPane.showMessageDialog(view, "Erro de conexao!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
 }
}
