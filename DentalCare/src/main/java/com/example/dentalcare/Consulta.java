package com.example.dentalcare;

public class Consulta {

    private Empresa empresa;
    private Consultorio consultorio;
    private String tipoConsulta;
    private float preco;
    private EstadoConsulta estado;

    public Consulta(Empresa empresa, Consultorio consultorio, String tipoConsulta, float preco, EstadoConsulta estado){

        this.empresa = empresa;
        this.consultorio = consultorio;
        this.tipoConsulta = tipoConsulta;
        this.preco = preco;
        this.estado = EstadoConsulta.EM_CONFIRMACAO;    //dono_empresa ou secretaria confirmam ou cancelam

    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public EstadoConsulta getEstado() {
        return estado;
    }

    public void setEstado(EstadoConsulta estado) {
        this.estado = estado;
    }
}
