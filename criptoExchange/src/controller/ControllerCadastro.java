/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.InvestidorDAO;
import DAO.Conexao;
import view.CadastroView;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Investidor;
/**
 *
 * @author manga
 */
public class ControllerCadastro {
    private CadastroView view;

    public ControllerCadastro(CadastroView view) {
        this.view = view;
    }
    public void salvarAluno(){
        String nome = view.getTxtNome().getText();
        String cpf = view.getTxtCpf().getText();
        String senha = view.getTxtSenha().getText();
        String idade = view.getTxtIdade().getText();
        Investidor investidor = new Investidor(nome, cpf, senha,idade);
        Conexao conexao = new Conexao();
        try {
            
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
            dao.inserir(investidor);
            JOptionPane.showMessageDialog(view, "Investidor cadastrado!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e){
            System.out.println("Ocorreu um erro no banco: "  + e.getMessage());
            JOptionPane.showMessageDialog(view, "Investidor não cadastrado!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
