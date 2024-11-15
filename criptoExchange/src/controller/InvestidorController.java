/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.InvestidorDAO;
import DAO.Conexao;
import DAO.Conn;
import view.InvestidorView;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Investidor;
/**
 *
 * @author manga
 */
public class InvestidorController {
    private JFrame views;
    private static Investidor investidorLogado;

    public static Investidor getInvestidorLogado() {
        return investidorLogado;
    }

    public static void setInvestidorLogado(Investidor investidorLogado) {
        InvestidorController.investidorLogado = investidorLogado;
    }
    
    
    public InvestidorController(JFrame views) {
        this.views = views;
    }
    
    public void menu() {
        InvestidorView investidorView = new InvestidorView(Conexao.getInstance());
        investidorView.setVisible(true);
        views.setVisible(false);
    }
}
