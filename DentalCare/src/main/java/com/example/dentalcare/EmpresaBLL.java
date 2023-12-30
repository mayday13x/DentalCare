package com.example.dentalcare;

public class EmpresaBLL {

    public static void criarEmpresa(Dono dono,Empresa empresa){
        Repositorio.getRepositorio().getEmpresas().put(dono, empresa);
        System.out.println("A empresa foi registada!");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }

    public static void localidaEmpresa(String localidade, Empresa empresa){
        Repositorio.getRepositorio().getLocalidade().put(localidade,empresa);
        System.out.println("A localicade foi asscociada a empresa!");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }

}
