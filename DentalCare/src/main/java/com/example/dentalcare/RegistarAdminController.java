package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistarAdminController implements Initializable {
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
    private PasswordField password;

    @FXML
    private TextField telefone;

    @FXML
    private TextField utilizadorId;

    @FXML
    private Label nifError;

    @FXML
    private Label telefoneError;

    @FXML
    private Label ccError;

    boolean ccEstado = false;

    boolean nifEstado = false;

    boolean telefoneEstado = false;

    public void registarAdmin(ActionEvent event){

        if(telefoneEstado && ccEstado && nifEstado){
            try {
                Admin admin = new Admin();

                admin.setUtilizador(utilizadorId.getText());
                admin.setPassword(password.getText());
                admin.setNome(nome.getText());
                admin.setTelefone(Integer.parseInt(telefone.getText()));
                admin.setNIF(Integer.parseInt(nif.getText()));
                admin.setMorada(morada.getText());
                admin.setLocalidade(localidade.getText());
                admin.setCC(Integer.parseInt(cc.getText()));

                AdminBLL.adicionarAdmion(admin);
            }catch (Exception e){
                System.out.println("Erro ao tentar registar o admin: " + e.getMessage());
            }
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ccError.setVisible(false);
        nifError.setVisible(false);
        telefoneError.setVisible(false);
    }

    @FXML
    void verifyNif(KeyEvent event){
        String text = nif.getText();
        if(text.matches("[0-9]*")){
            nifError.setVisible(false);
            nifEstado = true;

            if(text.length() != 9){
                nifError.setVisible(true);
                nifEstado = false;
            }
        }else {
            nifError.setVisible(true);
            nifEstado = false;
        }
    }

    @FXML
    void verifyCC(KeyEvent event){
        String text = cc.getText();

        if(text.matches("[0-9]*")){
            ccError.setVisible(false);
            ccEstado = true;

            if(text.length() != 8){
                ccError.setVisible(true);
                ccEstado = false;
            }
        }
        else {
            ccError.setVisible(true);
            ccEstado = false;
        }
    }

    @FXML
    void verifyTelefone(KeyEvent event){
        String text = telefone.getText();

        if(text.matches("[0-9]*")){
            telefoneError.setVisible(false);
            telefoneEstado = true;

            if(text.length() != 9){
                telefoneError.setVisible(true);
                telefoneEstado = false;
            }
        }else {
            telefoneError.setVisible(true);
            telefoneEstado = false;
        }
    }

    @FXML
    public void voltarAtras(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Admin");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao acessar o menu admin: " + ex.getMessage());
        }


    }

}
