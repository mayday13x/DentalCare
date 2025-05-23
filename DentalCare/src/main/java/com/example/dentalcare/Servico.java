package com.example.dentalcare;

import java.io.Serializable;

public class Servico implements Serializable {

    private int idServico;
    private String nomeServico;
    private String empresa;
    private float precoServico;

    public Servico(){}

    public Servico(int idServico, String nomeServico, String empresa, float precoServico) {
        this.idServico = idServico;
        this.nomeServico = nomeServico;
        this.empresa = empresa;
        this.precoServico = precoServico;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico(){
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public float getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(float precoServico) {
        this.precoServico = precoServico;
    }
}
