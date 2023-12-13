package com.example.dentalcare;

import java.awt.image.ComponentColorModel;
import java.io.Serial;
import java.io.Serializable;

public class Utilizador implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String utilizador;
    private String password;
    private String nome;
    private String CC;
    private String NIF;
    private String telefone;
    private String morada;
    private String localidade;

    public Utilizador(){}

    public Utilizador(String nome, String CC, String NIF,String telefone,String morada, String localidade, String utilizador, String password) {

        this.nome = nome;
        this.CC = CC;
        this.NIF = NIF;
        this.telefone = telefone;
        this.morada = morada;
        this.localidade = localidade;
        this.utilizador = utilizador;
        this.password = password;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String numFiscal) {
        this.NIF = numFiscal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
