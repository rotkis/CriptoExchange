/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.InvestidorDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Carteira;
import model.Investidor;
import view.ConfigView;

/**
 *
 * @author manga
 */
public class ApagarController {
    private Carteira investidor;
    private ConfigView view;

    public ApagarController(Carteira investidor, ConfigView view) {
        this.investidor = investidor;
        this.view = view;
    }
    
    public void excluir(){
        
        
        int opcao = JOptionPane.showConfirmDialog(view, "Deseja realmente excluir", "Aviso", JOptionPane.YES_NO_OPTION);
        if (opcao != 1){
            String cpf = JOptionPane.showInputDialog("Digite o cpf: ");
            Conexao conexao = new  Conexao();
            try{
                Connection conn = conexao.getConnection();
                
                InvestidorDAO dao = new InvestidorDAO(conn);
                
                dao.excluir(investidor, cpf);
                
                
                JOptionPane.showMessageDialog(view, "Investidor Excluido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Investidor não Excluido", "Erro", JOptionPane.ERROR_MESSAGE);
                
            }
        }
    }
}
