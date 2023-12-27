package com.example.dentalcare;


import com.example.dentalcare.Cliente;
import com.example.dentalcare.Dono;
import com.example.dentalcare.Funcionario;
import com.example.dentalcare.Repositorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;


public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField utilizador;

    @FXML
    private Label User_Pass_Wrong;

    @FXML
    public void login(ActionEvent event) throws IOException {
         boolean message = true;


        try{
            Repositorio repo = Repositorio.getRepositorio();
            DataSessao ds = new DataSessao();

            for(Cliente cl : repo.getClientes()){
                if(cl.getUtilizador().equals(utilizador.getText()) && cl.getPassword().equals(password.getText())){
                    message = false;
                    DataSessao.cliente = cl;
                    Parent root = FXMLLoader.load(getClass().getResource("menuCliente.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Cliente");
                    stage.show();
                }
            }

            for(Dono dono: Repositorio.getRepositorio().getDonos()){
                if(utilizador.getText().equals(dono.getUtilizador()) && dono.getPassword().equals(password.getText())){
                    message = false;
                    DataSessao.dono = dono;
                    Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Donos");
                    stage.show();
                }
            }

            for(Funcionario funcionario: Repositorio.getRepositorio().getFuncionarios()){
                if(utilizador.getText().equals(funcionario.getUtilizador()) && password.getText().equals(funcionario.getPassword())){
                    message = false;
                    DataSessao.funcionario = funcionario;
                    Parent root = FXMLLoader.load(getClass().getResource("menuFuncionario.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Funcionarios");
                    stage.show();
                }
            }

            if(message){
                User_Pass_Wrong.setVisible(true);
            }
        }catch (Exception ex){
            System.out.println("Erro no login: " + ex.getMessage());
        }

    }

    @FXML
    public void menuRegistar(ActionEvent event) throws IOException{

        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuRegistar.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Registar User");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro: " + ex.getMessage());
        }


    }


}
