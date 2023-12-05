package com.example.dentalcare;

public class FuncionarioBLL {

    public static void registarFuncionario(Funcionario funcionario){
        Repositorio.getRepositorio().getFuncionarios().put(funcionario.getCC(), funcionario);
        System.out.println("O funcionario foi registado");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }
}
