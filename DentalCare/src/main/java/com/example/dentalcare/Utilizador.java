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
    private int CC;
    private int NIF;
    private int telefone;
    private String morada;
    private String localidade;

    public Utilizador(){}

    public Utilizador(String nome, int CC, int NIF, String morada, String localidade, String utilizador, String password) {

        this.nome = nome;
        this.CC = CC;
        this.NIF = NIF;
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

    public int getCC() {
        return CC;
    }

    public void setCC(int CC) {
        this.CC = CC;
    }

    public int getNIF() {
        return NIF;
    }

    public void setNIF(int numFiscal) {
        this.NIF = numFiscal;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
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
