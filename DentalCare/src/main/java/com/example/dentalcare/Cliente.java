package com.example.dentalcare;

public class Cliente extends Utilizador{

    public Cliente(){

    }

    public Cliente(String nome, String CC, String NIF, String morada, String localidade, String utilizador, String password) {

        super(nome, CC,NIF,morada,localidade, utilizador, password);

    }
}
