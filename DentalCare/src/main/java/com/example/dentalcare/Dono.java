package com.example.dentalcare;

public class Dono extends Utilizador {

    private EstadoDonoEmpresa estado;

    public Dono(){}

    public Dono(String nome, String CC, String NIF,String telefone, String morada, String localidade, String utilizador, String password) {
        super(nome, CC,NIF,telefone,morada,localidade, utilizador, password);
    }

    public EstadoDonoEmpresa getEstado() {
        return estado;
    }

    public void setEstado(EstadoDonoEmpresa estado) {
        this.estado = estado;
    }
}
