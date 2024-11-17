/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Random;

/**
 *
 * @author manga
 */
public abstract class Moedas extends Carteira {
    
    public Moedas(String reais, String bitcoin, String ripple, String etherum, String nome, String cpf, String senha, String idade) {
        super(reais, bitcoin, ripple, etherum, nome, cpf, senha, idade);
    }
    public static double cotacao(){
        Random random = new Random();
        double variacao = (random.nextDouble() * 0.1) - 0.05; // Variar de -5% a +5%
        return (1 + variacao);
    }
}
