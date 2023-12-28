package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistarAdminController {
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

    public void registarAdmin(ActionEvent event){
        try {
            Admin admin = new Admin();

            admin.setUtilizador(utilizadorId.getText());
            admin.setPassword(password.getText());
            admin.setNome(nome.getText());
            admin.setTelefone(telefone.getText());
            admin.setNIF(nif.getText());
            admin.setMorada(morada.getText());
            admin.setLocalidade(localidade.getText());
            admin.setCC(cc.getText());

            AdminBLL.adicionarAdmion(admin);
        }catch (Exception e){
            System.out.println("Erro ao tentar registar o admin: " + e.getMessage());
        }


    }

}
