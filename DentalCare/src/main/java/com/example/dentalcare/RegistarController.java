package com.example.dentalcare;

import com.example.dentalcare.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistarController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField cc;

    @FXML
    private TextField localidade;

    @FXML
    private TextField morada;

    @FXML
    private TextField nif;

    @FXML
    private TextField nome;

    @FXML
    private TextField telefone;

    @FXML
    private ChoiceBox<String> tipoUtilizador;

    @FXML
    private TextField utilizadorId;


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
            System.out.println("Erro: " + ex.getMessage());
        }


    }

    @FXML
    public void registar(ActionEvent event) {
        if(tipoUtilizador.getValue().equals("Cliente")){
            Cliente cl = new Cliente();
            cl.setUtilizador(utilizadorId.getText());
            cl.setPassword(password.getText());
            cl.setNome(nome.getText());
            cl.setCC(Integer.parseInt(cc.getText()));
            cl.setNIF(Integer.parseInt(nif.getText()));
            cl.setTelefone(Integer.parseInt(telefone.getText()));
            cl.setMorada(morada.getText());
            cl.setLocalidade(localidade.getText());

            ClienteBLL.registarCliente(cl);

        } else if(tipoUtilizador.getValue().equals("Dono de empresa")){
            Dono dn = new Dono();
            dn.setUtilizador(utilizadorId.getText());
            dn.setPassword(password.getText());
            dn.setNome(nome.getText());
            dn.setCC(Integer.parseInt(cc.getText()));
            dn.setNIF(Integer.parseInt(nif.getText()));
            dn.setTelefone(Integer.parseInt(telefone.getText()));
            dn.setMorada(morada.getText());
            dn.setLocalidade(localidade.getText());
            dn.setEstado(EstadoDonoEmpresa.ATIVADA);

            DonoBLL.registarDono(dn);
        }


        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        tipoUtilizador.getItems().addAll("Cliente", "Dono de empresa");
    }


}
