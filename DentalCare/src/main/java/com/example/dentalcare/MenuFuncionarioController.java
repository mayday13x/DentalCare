package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuFuncionarioController {


    public void MenuMarcacoes(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuMarcacoes.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Consulatar Marcacoes");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public void listarMarcacoesPorEmpresaConsultorio(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listarMarcacoesPorEmpresaConsultorio.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Consulatar Marcacoes");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
