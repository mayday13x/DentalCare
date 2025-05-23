package com.example.dentalcare;

import javafx.fxml.Initializable;

import java.io.*;
import java.util.*;

public class Repositorio implements Serializable {

    private static Repositorio repositorio = null;

    private List<Cliente> clientes = new ArrayList<>();

    private List<Admin> admins = new ArrayList<>();

    private List<Funcionario> funcionarios = new ArrayList<>();

    private List<Dono> donos = new ArrayList<>();

    private List<Servico> servicos = new ArrayList<>();

    private List<Consulta> consultas = new ArrayList<>();

    private Map<String, Integer> precoConsultas = new HashMap<>();

    private Map<String, Empresa> localidade = new HashMap<>();

    private Map<Dono,Empresa> empresas = new HashMap<>();

    private Map<Empresa, List<Consultorio>> consultorios = new HashMap<>();

    private Repositorio(){}

    public List<Cliente> getClientes(){
        return  clientes;
    }

    public List<Admin> getAdmins(){
        if(admins == null){
            admins = new ArrayList<>();
        }
        return  admins;}

    public List<Funcionario> getFuncionarios(){
        return funcionarios;
    }

    public List<Dono> getDonos(){
        return donos;
    }

    public List<Servico> getServicos(){
        if(servicos == null){
            servicos = new ArrayList<>();
        }
        return servicos;
    }

    public List<Consulta> getConsultas(){
        if(consultas == null){
            consultas = new ArrayList<>();
        }
        return consultas;
    }
    public Map<String, Integer> getPrecoConsultas(){
        if(precoConsultas == null){
            precoConsultas = new HashMap<>();
        }
            return precoConsultas;
        }

    public Map<String, Empresa> getLocalidade(){return localidade;}

    public Map<Dono,Empresa> getEmpresas(){
        return empresas;
    }

    public Map<Empresa, List<Consultorio>> getConsultorios(){
        return consultorios;
    }

    @Serial
    private static final long serialVersionUID = 2341L;

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
            System.out.println("Erro ao serializar: " + ex.getMessage());
        }
    }

    public static Repositorio desserializar(String filename) throws ClassNotFoundException, IOException {

        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            repositorio = (Repositorio) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException ex){
            System.out.println("Erro em desserializar: " + ex.getMessage());
        } catch(ClassNotFoundException ex){
            System.out.println("Class repositorio nao foi encontrada. " + ex.getMessage());
        }


        return repositorio;
    }


}
