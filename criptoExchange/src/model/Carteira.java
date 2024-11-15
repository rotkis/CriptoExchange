/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manga
 */
public class Carteira extends Investidor{
    private double reais,bitcoin ,  ripple, etherum;

    public Carteira(double reais, double bitcoin, double ripple, double etherum, String nome, String cpf, String senha, String idade) {
        super(nome, cpf, senha, idade);
        this.reais = reais;
        this.bitcoin = bitcoin;
        this.ripple = ripple;
        this.etherum = etherum;
    }
    public String saldo(){
        String res = "Nome: "+ nome+ " CPF: "+ cpf + " Idade: " + idade+"\nReais: "+ reais+ " Bitcoin: " + bitcoin + " Ripple: "+ripple+" Etherum: "+ etherum;
        return res;
    }
    
}
