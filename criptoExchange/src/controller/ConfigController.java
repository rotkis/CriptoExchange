/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.InvestidorDAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Investidor;
import view.AtualizarView;
import view.ConfigView;
import view.InvestidorView;

/**
 *
 * @author manga
 */
public class ConfigController {
    private AtualizarView view;
    private Investidor investidor;

    public ConfigController(AtualizarView view, Investidor investidor) {
        this.view = view;
        this.investidor = investidor;
    }
    public void atualizar(){
        String cpf = view.getTxtCpf().getText();
        String novaSenha = view.getTxtNovaSenha().getText();
        Investidor investidor = new Investidor("",cpf, novaSenha,"");
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            InvestidorDAO dao = new InvestidorDAO(conn);
            dao.atualizar(investidor);
            JOptionPane.showMessageDialog(view, "Senha alterada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Senha não alterada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
