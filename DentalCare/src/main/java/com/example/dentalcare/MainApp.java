package com.example.dentalcare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Test");
            stage.setScene(scene);
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    public static void main(String[] args) {
       launch();

        for(Admin adm: Repositorio.getRepositorio().getAdmins()){
            System.out.println("Admins");
            System.out.println("User: " + adm.getUtilizador());
            System.out.println("Pass: " + adm.getPassword());
        }

        for( Utilizador cliente: Repositorio.getRepositorio().getClientes()){
            System.out.println("Clientes");
            System.out.printf("%s , %s, %s, %s, %s, %s, %s\n", cliente.getCC(), cliente.getNome(), cliente.getMorada(),
                    cliente.getUtilizador(), cliente.getPassword(), cliente.getTelefone(), cliente.getLocalidade());

        }
        for( Utilizador funcionario: Repositorio.getRepositorio().getFuncionarios()){
            System.out.println("Funcionarios");
            System.out.printf("%s , %s, %s, %s, %s, %s, %s\n", funcionario.getCC(), funcionario.getNome(),
                    funcionario.getMorada(), funcionario.getUtilizador(), funcionario.getPassword(),
                    funcionario.getTelefone(), funcionario.getLocalidade());

        }

        for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
            System.out.println("Empresas");
            System.out.printf("%s , %s, %s, %s, %s, %s\n", empresa.getNome(), empresa.getMorada(), empresa.getLocalidade(),
                    empresa.getNumTelefone(), empresa.getConsultorios(),empresa.getDono().getNome());
        }

        for(Dono dono: Repositorio.getRepositorio().getEmpresas().keySet()){
            System.out.println("Donos");
            System.out.println("Dono " + dono.getNome());
        }

        for(Servico servico: Repositorio.getRepositorio().getServicos()){
            System.out.println("Servicos");
            System.out.println("Nome: " + servico.getNomeServico());
            System.out.println("Id: " + servico.getIdServico());
            System.out.println("Preco: " + servico.getPrecoServico());
            System.out.println("Empresa: " + servico.getEmpresa());
        }

        for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
            System.out.println("Consultas");
            System.out.println("Id : " + consulta.getIdConsulta());
            System.out.println("Cliente : " + consulta.getCliente().getNome());
            System.out.println("Data : " + consulta.getDataConsulta());
            System.out.println("Especialidade : " + consulta.getEspecialidade());
            System.out.println("Servico : " + consulta.getServico());
            System.out.println("Funcionario : " + consulta.getFuncionario());
            System.out.println("Preco: " + consulta.getPrecoTotal());
            System.out.println("Estado: " + consulta.getEstadoConsulta());
        }

    }
}