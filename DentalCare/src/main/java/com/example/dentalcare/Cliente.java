package com.example.dentalcare;

public class Cliente extends Utilizador{

    public Cliente(){

    }

    public Cliente(String nome, int CC, int NIF,int telefone, String morada, String localidade, String utilizador, String password) {

        super(nome, CC,NIF,telefone,morada,localidade, utilizador, password);

    }
}
