/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author manga
 */
public class Pessoa {
    protected String nome, cpf, senha, reais, bitcoin, ethereum,ripple;

    public Pessoa(String nome, String cpf, String senha, String reais, String bitcoin, String ethereum, String ripple) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.reais = reais;
        this.bitcoin = bitcoin;
        this.ethereum = ethereum;
        this.ripple = ripple;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getEthereum() {
        return ethereum;
    }

    public void setEthereum(String ethereum) {
        this.ethereum = ethereum;
    }

    public String getRipple() {
        return ripple;
    }

    public void setRipple(String ripple) {
        this.ripple = ripple;
    }
    
}
