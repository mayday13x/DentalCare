package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
}
