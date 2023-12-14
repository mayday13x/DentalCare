package com.example.dentalcare;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.dentalcare.colors.Colors.*;

public class Repositorio implements Serializable {

    private static Repositorio repositorio = null;

    private Map<String, Cliente> clientes = new HashMap<>();

    private Map<String,Funcionario> funcionarios = new HashMap<>();
    private Map<Dono, Set<Empresa>> donos;
    private Map<String, Set<Empresa>> empresas;  //localidade --> Empresas
    private List<Consulta> consultas;

    public static final String ANSI_RED = "\u001B[31m";


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
                System.out.println("[/]A ler repositório...");
                repositorio = Repositorio.desserializar("src\\main\\resources\\repo\\repositorio.dat");
               // System.out.println(ANSI_GREEN + "Repositório OK" + ANSI_RESET);
                printGreen("Repositório OK");
            }catch (Exception e) {
               // System.out.println(ANSI_RED + "[-] Falha ao ler ficheiro (repositorio): " + e.getMessage() + ANSI_RESET);
                printRed("[-] Falha ao ler ficheiro (repositorio): " + e.getMessage());
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
            printGreen("[+] Dados guardados para: " + filename);
        }catch( IOException ex){
            printRed("Erro: " + ex.getMessage());
        }
    }

    public static Repositorio desserializar(String filename) throws ClassNotFoundException, IOException {

        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        repositorio = (Repositorio) in.readObject();
        in.close();
        fileIn.close();

        return repositorio;
    }


}
