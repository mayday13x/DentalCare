package com.example.dentalcare;

import java.io.Serializable;
import java.time.LocalDate;

public class Consulta implements Serializable {

    public int idConsulta;
    private Cliente cliente;
    private LocalDate dataConsulta;
    private String funcionario;
    private float precoTotal;
    private String servico;
    private String especialidade;
    private EstadoConsulta estadoConsulta;

    public Consulta(){}

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String tipo) {
        this.especialidade = tipo;
    }

    public EstadoConsulta getEstadoConsulta() {
        return estadoConsulta;
    }

    public void setEstadoConsulta(EstadoConsulta estadoConsulta) {
        this.estadoConsulta = estadoConsulta;
    }
}
