package com.example.dentalcare;

public class DonoBLL {

    public static void registarDono(Dono dono){
        Repositorio.getRepositorio().getDonos().add(dono);
        System.out.println("O dono foi registado!");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }
}
