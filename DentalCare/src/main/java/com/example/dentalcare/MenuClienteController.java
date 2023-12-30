package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuClienteController {

    @FXML
    private void marcarConsulta(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("marcarConsulta.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar consulta");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar menu marcar consulta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void pagamentoConsulta(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("pagamentoConsulta.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Pagar consulta");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar menu pagar consulta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void listarMarcacoes(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listarMarcacoesCliente.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar Marcacoes do cliente");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar menu listar marcacoes: " + ex.getMessage());
            ex.printStackTrace();
        }
    }



}
