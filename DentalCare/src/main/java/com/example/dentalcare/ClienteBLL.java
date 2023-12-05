package com.example.dentalcare;

public class ClienteBLL {

    public static void registarCliente(Cliente cliente){
        Repositorio.getRepositorio().getClientes().put(cliente.getCC(),cliente);
        System.out.println("Cliente foi registado!");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }


}
