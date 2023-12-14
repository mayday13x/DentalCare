package com.example.dentalcare;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

import static com.example.dentalcare.colors.Colors.*;


public class LoginController {



    @FXML
    private PasswordField password;

    @FXML
    private TextField utilizador;
    @FXML
    public void login(ActionEvent event) throws IOException {

        Repositorio repo = Repositorio.getRepositorio();


        for(Cliente cl : repo.getClientes().values()){
            if(cl.getUtilizador().equals(utilizador.getText()) && cl.getPassword().equals(password.getText())){
                Parent root = FXMLLoader.load(getClass().getResource("menuCliente.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu");
                stage.show();
                printGreen("Logged in: " + cl.getUtilizador()+"(CLIENTE)");
               } else {
                printRed("Crendenciais incorretas!");
            }
           }

        for(Funcionario fn: repo.getFuncionarios().values()){
            if(utilizador.getText().equals(fn.getUtilizador()) && password.getText().equals(password.getText())){
                Parent root = FXMLLoader.load(getClass().getResource("menuFuncionario.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu");
                stage.show();
               } else {
                printRed("Crendenciais incorretas!");
            }
           }

    }

    @FXML
    public void menuRegistar(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("menuRegistar.fxml"));
        Scene regCena = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(regCena);
        stage.setTitle("Registar User");
        stage.show();
    }


}
