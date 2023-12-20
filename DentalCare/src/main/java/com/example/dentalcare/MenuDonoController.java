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

    @FXML
    private Button criarConsultorio;

    @FXML
    private Button criarEmpresa;

    @FXML
    private Button  consultar_alterar_dados;

    @FXML
    private Button criarFuncionario;

    @FXML
    private Button listarMarcacoes;


    public void menuCriarConsultorio(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("criarConsultorio.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar consultorio");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro: " + ex.getMessage());
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
            System.out.println("Erro: " + ex.getMessage());
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
            System.out.println("Erro: " + ex.getMessage());
        }
    }


}
