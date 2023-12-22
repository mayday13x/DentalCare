package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuDonoController {


    public void menuCriarConsultorio(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adicionarConsultorio.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar consultorio");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar menu criar consultorio: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void menuCriarEmpresa(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("criarEmpresa.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar empresa");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao tentar acessar menu criar empresa: " + ex.getMessage());
        }
    }

    public void menuConsultarDadosEmpresa(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("consultarDadosEmpresa.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Consultar Dados da Empresa");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao tentar acessar menu consultar dados de empresa : " + ex.getMessage());
        }
    }

    public void menuCriarFuncionario(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("criaFuncionario.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar Funcionario");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao tentar acessar menu criar funcionario : " + ex.getMessage());
            ex.printStackTrace();
        }
    }


}
