package com.example.dentalcare;

import java.util.List;

public class FuncionarioBLL {

    public static void adicionarFuncionario(Funcionario funcionario, Empresa empresa, Consultorio consultorio){
        Repositorio.getRepositorio().getFuncionarios().add(funcionario);
        List<Consultorio> con =Repositorio.getRepositorio().getConsultorios().get(empresa);
        if(con.contains(consultorio)){
            consultorio.addFuncionario(funcionario);
        }
        System.out.println("O funcionario foi registado!");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }
}
