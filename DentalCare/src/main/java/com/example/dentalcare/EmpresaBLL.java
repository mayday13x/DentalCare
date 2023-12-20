package com.example.dentalcare;

public class EmpresaBLL {

    public static void criarEmpresa(Empresa empresa,Dono dono){
        Repositorio.getRepositorio().getEmpresas().put(empresa, dono);
        System.out.println("A empresa foi registada!");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }

}
