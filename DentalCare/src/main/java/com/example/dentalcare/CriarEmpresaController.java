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
        try{
            Empresa emp = new Empresa();

            emp.setNome(nomeEmpresa.getText());
            emp.setMorada(moradaEmpresa.getText());
            emp.setLocalidade(localidadeEmpresa.getText());
            emp.setNumTelefone(telefoneEmpresa.getText());
            emp.setDono(DataSessao.dono);
            emp.setEstado(EstadoDonoEmpresa.ATIVADA);

            EmpresaBLL.criarEmpresa(DataSessao.dono, emp);
            EmpresaBLL.localidaEmpresa(localidadeEmpresa.getText(),emp);
        }catch (Exception e){
            System.out.println("Erro ao criar empresa: " + e.getMessage());
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Donos");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao carregar o menu: " + ex.getMessage());
        }

    }

    @FXML
    public void voltarAtras(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Dono");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao acessar menu dono: " + ex.getMessage());
        }
    }

}
