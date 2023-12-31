package com.example.dentalcare;

public class Funcionario extends Utilizador{

    private ProfissaoFuncionario profissao;
    private String numCarteiraProfissional;

    public Funcionario(){}

    public Funcionario(String nome, int CC, int NIF,int telefone, String morada, String localidade,ProfissaoFuncionario profissao,
                       String utilizador, String password) {

        super(nome,CC,NIF,telefone,morada,localidade, utilizador, password);
        this.profissao = profissao;

    }

    public Funcionario(String nome, int CC, int NIF,int telefone, String morada,String localidade,ProfissaoFuncionario profissao,
                       String numCarteiraProfissional, String utilizador, String password) {

        super(nome,CC,NIF,telefone,morada,localidade, utilizador, password);
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

    public String getNumCarteiraProfissional(){
        return numCarteiraProfissional;
    }

    public void setNumCarteiraProfissional(String numCarteiraProfissional) {   // considerar se é necessário
        this.numCarteiraProfissional = numCarteiraProfissional;
    }
}
