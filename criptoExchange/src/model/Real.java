/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manga
 */
public abstract class Real extends Moedas implements Tarifacao {

    public Real(String reais, String bitcoin, String ripple, String etherum, String nome, String cpf, String senha, String idade) {
        super(reais, bitcoin, ripple, etherum, nome, cpf, senha, idade);
    }
    
}
