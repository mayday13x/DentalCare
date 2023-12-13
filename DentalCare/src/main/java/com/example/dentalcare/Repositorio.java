package com.example.dentalcare;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Repositorio implements Serializable {

    private static Repositorio repositorio = null;

    private Map<String, Cliente> clientes = new HashMap<>();

    private Map<String,Funcionario> funcionarios = new HashMap<>();
    private Map<Dono, Set<Empresa>> donos;
    private Map<String, Set<Empresa>> empresas;  //localidade --> Empresas


    private Repositorio(){}

    public Map<String,Cliente> getClientes(){
        return  clientes;
    }

    public Map<String,Funcionario> getFuncionarios(){
        return funcionarios;
    }

    public Map<Dono, Set<Empresa>> getDonos() {
        return donos;
    }

    public Map<String, Set<Empresa>> getEmpresas() {
        return empresas;
    }

    public static Repositorio getRepositorio(){

        if(repositorio == null){
            repositorio = new Repositorio();

            try {
                System.out.println("A ler ficheiro...");
                repositorio = Repositorio.desserializar("src\\main\\resources\\repo\\repositorio.dat");
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
