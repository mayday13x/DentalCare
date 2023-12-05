package com.example.dentalcare;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Repositorio implements Serializable {

    private static Repositorio repositorio = null;

    private Map<String, Cliente> clientes = new HashMap<>();

    private Map<String,Funcionario> funcionarios = new HashMap<>();

    public Map<String,Cliente> getClientes(){
        return  clientes;
    }

    public Map<String,Funcionario> getFuncionarios(){
        return funcionarios;
    }

    public Repositorio(){}



    public static Repositorio getRepositorio(){

        if(repositorio == null){
            repositorio = new Repositorio();

            try {
                System.out.println("A ler ficheiro...");
                repositorio = Repositorio.desserializar("repositorio.dat");
                System.out.println("Ficheiro lido com sucesso");
            }catch (Exception ignored) {

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
            System.out.println("Serialized data is saved in " + filename);
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
