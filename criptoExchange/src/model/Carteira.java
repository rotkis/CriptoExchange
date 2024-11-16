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
    private String reais,bitcoin ,  ripple, etherum;

    public Carteira(String reais, String bitcoin, String ripple, String etherum, String nome, String cpf, String senha, String idade) {
        super(nome, cpf, senha, idade);
        this.reais = reais;
        this.bitcoin = bitcoin;
        this.ripple = ripple;
        this.etherum = etherum;
    }
    public String saldo(){
        String res = "Nome: "+ getNome()+ " CPF: "+ getCpf() + " Idade: " + getIdade() +"\nReais: "+ reais+ " Bitcoin: " + bitcoin + " Ripple: "+ripple+" Etherum: "+ etherum;
        return res;
    }

    public String getReais() {
        return reais;
    }

    public void setReais(String reais) {
        this.reais = reais;
    }

    public String getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(String bitcoin) {
        this.bitcoin = bitcoin;
    }

    public String getRipple() {
        return ripple;
    }

    public void setRipple(String ripple) {
        this.ripple = ripple;
    }

    public String getEtherum() {
        return etherum;
    }

    public void setEtherum(String etherum) {
        this.etherum = etherum;
    }
    
}
