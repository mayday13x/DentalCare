package com.example.dentalcare;

public class ServicoBLL {

    public static void adicionarServico(Servico servico){
        Repositorio.getRepositorio().getServicos().add(servico);
        System.out.println("O servico foi adicionado!");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }
}
