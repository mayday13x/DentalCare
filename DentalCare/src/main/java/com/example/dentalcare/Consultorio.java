package com.example.dentalcare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Consultorio implements Serializable {

    private String morada;
    private String localidade;
    private String numTelefone;
    private String especialidade;
    private Set<String> servicos;  //tipos de consulta disponiveis
    private List<Funcionario> funcionarios;

    public Consultorio(){}

    public Consultorio(String morada, String localidade, String numTelefone, String especialidade) {
        this.morada = morada;
        this.localidade = localidade;
        this.numTelefone = numTelefone;
        this.especialidade = especialidade;
        this.servicos = new HashSet<>();
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

    public String getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Set<String> getServicos() {
        return servicos;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    public void addServico(String servico) {
        this.servicos.add(servico);
    }

}
