package com.example.dentalcare;

public class AdminBLL {

    public static void adicionarAdmion(Admin admin){
        Repositorio.getRepositorio().getAdmins().add(admin);
        System.out.println("O admin foi registado com sucesso!");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }
}
