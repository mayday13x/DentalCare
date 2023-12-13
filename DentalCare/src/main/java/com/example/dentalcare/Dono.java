package com.example.dentalcare;

import java.util.ArrayList;
import java.util.List;

public class Dono extends Utilizador {

    List<Empresa> empresas;

    public Dono(String nome, String CC, String NIF,String telefone, String morada, String localidade, String utilizador, String password) {
        super(nome, CC,NIF,telefone,morada,localidade, utilizador, password);
        empresas = new ArrayList<>();
    }
}
