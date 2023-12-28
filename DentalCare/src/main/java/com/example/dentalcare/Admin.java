package com.example.dentalcare;


public class Admin extends Utilizador {

    public Admin(){}

    public Admin(String nome, String CC, String NIF,String telefone, String morada, String localidade, String utilizador, String password) {
        super(nome, CC,NIF,telefone,morada,localidade, utilizador, password);
    }
}
