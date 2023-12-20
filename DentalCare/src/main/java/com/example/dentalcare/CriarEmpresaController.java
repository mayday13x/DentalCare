package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CriarEmpresaController {


    @FXML
    private TextField localidadeEmpresa;

    @FXML
    private TextField moradaEmpresa;

    @FXML
    private TextField nomeEmpresa;

    @FXML
    private TextField telefoneEmpresa;


    public void criarEmpresa(ActionEvent event){
        Empresa emp = new Empresa();

        emp.setNome(nomeEmpresa.getText());
        emp.setMorada(moradaEmpresa.getText());
        emp.setLocalidade(localidadeEmpresa.getText());
        emp.setNumTelefone(telefoneEmpresa.getText());

        EmpresaBLL.criarEmpresa(emp,DataSessao.dono);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Donos");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro no login: " + ex.getMessage());
        }

    }

}
