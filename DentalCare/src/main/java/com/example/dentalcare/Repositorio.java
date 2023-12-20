package com.example.dentalcare;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repositorio implements Serializable {

    private static Repositorio repositorio = null;

    private List<Cliente> clientes = new ArrayList<>();

    private List<Funcionario> funcionarios = new ArrayList<>();

    private List<Dono> donos = new ArrayList<>();

    private Repositorio(){}

    public List<Cliente> getClientes(){
        return  clientes;
    }

    public List<Funcionario> getFuncionarios(){
        return funcionarios;
    }

    public List<Dono> getDonos(){
        return donos;
    }

    public static Repositorio getRepositorio(){

        if(repositorio == null){
            repositorio = new Repositorio();

            try {
                System.out.println("A ler ficheiro...");
                repositorio = Repositorio.desserializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
                System.out.println("Ficheiro lido com sucesso");
            }catch (Exception ignored) {
                System.out.println("Falha ao ler ficheiro (repositorio)");
            }
        }
        return  repositorio;
    }

    public void serializar(String filename) {

        try{
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Repositorio serailizado para: " + filename);
        }catch( IOException ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public static Repositorio desserializar(String filename) throws ClassNotFoundException, IOException {
        Repositorio carteira = null;

        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        repositorio = (Repositorio) in.readObject();
        in.close();
        fileIn.close();

        return repositorio;
    }


}
