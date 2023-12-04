package com.example.dentalcare;

import java.util.ArrayList;
import java.util.List;

public class Consultorio {

    private String morada;
    private String localidade;
    private long numTelefone;
    private TipoConsulta tpConsulta;
    private List<Funcionario> funcionarios;

    public Consultorio(String morada, String localidade, long numTelefone, TipoConsulta tpConsulta) {
        this.morada = morada;
        this.localidade = localidade;
        this.numTelefone = numTelefone;
        this.tpConsulta = tpConsulta;
        this.funcionarios = new ArrayList<>();
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

    public TipoConsulta getTpConsulta() {
        return tpConsulta;
    }

    public void setTpConsulta(TipoConsulta tpConsulta) {
        this.tpConsulta = tpConsulta;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

}
