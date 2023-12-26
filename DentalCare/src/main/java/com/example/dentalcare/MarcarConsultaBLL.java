package com.example.dentalcare;

public class MarcarConsultaBLL {

    public static void adicionarConsulta(Consulta consulta){
        Repositorio.getRepositorio().getConsultas().add(consulta);
        System.out.println("A consulta foi adicionada!");
        System.out.println("Espere pela confirmacao da mesma");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }
}
