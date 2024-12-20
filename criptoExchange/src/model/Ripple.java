/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manga
 */
public abstract class Ripple extends Carteira implements Tarifacao{
    private final static double valor = 6.56;
    public Ripple(String reais, String bitcoin, String ripple, String etherum, String nome, String cpf, String senha, String idade) {
        super(reais, bitcoin, ripple, etherum, nome, cpf, senha, idade);
    }
    public static double compraTaxa(){
        double taxa = (valor *1.01); 
        return taxa;
    }
    public static double vendaTaxa(){
        double taxa = (valor *0.99); 
        return taxa;
    }
}
