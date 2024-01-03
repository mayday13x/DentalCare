package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuAdminsController {

    public void registarAdmins(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("registarAdmin.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Registar Admin");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar menu registar admin: " + ex.getMessage());

        }
    }

    public void listarEmpresas(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listarEmpresas.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar empresas");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar ao menu listar empresas: " + ex.getMessage());

        }
    }

    public void listarClientes(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listarClientes.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar clientes");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar ao menu listar clientes: " + ex.getMessage());
        }
    }

    public void listarConsultorio(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listarConsultorios.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar consultorios");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar ao menu listar consultorios: " + ex.getMessage());
        }
    }

    public void listarFuncionarios(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listarFuncionario.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar funcionarios");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar ao menu listar funcionarios: " + ex.getMessage());
        }
    }

    public void listarDonos(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listarDonos.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar donos");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar ao menu listar donos: " + ex.getMessage());
        }
    }

    public void listarServicos(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listarServicos.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar servicos");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar ao menu listar servicos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void listarMarcacoes(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listarMarcacoes.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar marcacoes");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar ao menu listar marcacoes: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public  void desativarContaDonosEmpresas(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("desativarDonosEmpresas.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Desativar Donos e Empresas");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar ao menu Desativar Donos e Empresas: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void valoresPagosClientes(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("valoresPagosClientes.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Valores pagos por clientes");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao acessar o menu de valores pagos por clientes: " + ex.getMessage());
        }
    }

    @FXML
    public void voltarAtras(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Login");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao acessar menu login: " + ex.getMessage());
        }


    }


}
