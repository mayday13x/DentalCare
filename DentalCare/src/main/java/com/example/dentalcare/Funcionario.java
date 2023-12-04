package com.example.dentalcare;

public class Funcionario extends Utilizador{

    private ProfissaoFuncionario profissao;
    private int numCarteiraProfissional;

    public Funcionario(String nome, int CC, int NIF, String morada, String localidade,ProfissaoFuncionario profissao,
                       String utilizador, String password) {

        super(nome,CC,NIF,morada,localidade, utilizador, password);
        this.profissao = profissao;

    }

    public Funcionario(String nome, int CC, int NIF, String morada,String localidade,ProfissaoFuncionario profissao,
                       int numCarteiraProfissional, String utilizador, String password) {

        super(nome,CC,NIF,morada,localidade, utilizador, password);
        this.profissao = profissao;

        //se for dentista tem de ter um numero de carteira prof.

        if(profissao.equals(ProfissaoFuncionario.DENTISTA)) {
            this.numCarteiraProfissional = numCarteiraProfissional;
        }

    }

    public ProfissaoFuncionario getProfissao() {
        return profissao;
    }

    public void setProfissao(ProfissaoFuncionario profissao) {
        this.profissao = profissao;
    }

    public int getNumCarteiraProfissional(){
        return numCarteiraProfissional;
    }

    public void setNumCarteiraProfissional(int numCarteiraProfissional) {   // considerar se é necessário
        this.numCarteiraProfissional = numCarteiraProfissional;
    }
}
