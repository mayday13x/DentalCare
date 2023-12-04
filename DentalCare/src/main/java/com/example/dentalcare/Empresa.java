package com.example.dentalcare;

import java.util.*;

public class Empresa {

    private String nome;
    private String morada;
    private String localidade;
    private long numTelefone;
    private List<Consultorio> consultorios;
    private Dono dono;

    public Empresa(String nome, String morada, String localidade, long numTelefone, Dono dono) {
        this.nome = nome;
        this.morada = morada;
        this.localidade = localidade;
        this.numTelefone = numTelefone;
        this.dono = dono;
        consultorios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public long getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(long numTelefone) {
        this.numTelefone = numTelefone;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public List<Consultorio> getConsultorios() {
        return consultorios;
    }
    public void addConsultorio(Consultorio consultorio) {
        this.consultorios.add(consultorio);
    }
}
