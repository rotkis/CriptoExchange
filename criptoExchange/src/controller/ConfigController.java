/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AlunoDAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Aluno;
import view.AtualizarView;
import view.ConfigView;
import view.InvestidorView;

/**
 *
 * @author manga
 */
public class ConfigController {
    private AtualizarView view;
    private Aluno aluno;

    public ConfigController(AtualizarView view, Aluno aluno) {
        this.view = view;
        this.aluno = aluno;
    }
    public void atualizar(){
        String cpf = view.getTxtCpf().getText();
        String novaSenha = view.getTxtNovaSenha().getText();
        Aluno aluno = new Aluno("",cpf, novaSenha,"");
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            AlunoDAO dao = new AlunoDAO(conn);
            dao.atualizar(aluno);
            JOptionPane.showMessageDialog(view, "Senha alterada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Senha não alterada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void excluir(){
        int opcao = JOptionPane.showConfirmDialog(view, "Deseja realmente excluir", "Aviso", JOptionPane.YES_NO_OPTION);
        if (opcao != 1){
            Conexao conexao = new  Conexao();
            try{
                Connection conn = conexao.getConnection();
                
                AlunoDAO dao = new AlunoDAO(conn);
                dao.excluir(aluno);
                JOptionPane.showMessageDialog(view, "Aluno Excluido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Senha não Excluido", "Erro", JOptionPane.ERROR_MESSAGE);
                
            }
        }
    }
}
