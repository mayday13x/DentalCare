package com.example.dentalcare;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ListarFuncionarioController implements Initializable {

    @FXML
    private ListView<String> FuncionariosListView;

    @FXML
    private Label cc;

    @FXML
    private Label localidade;

    @FXML
    private Label morada;

    @FXML
    private Label nif;

    @FXML
    private Label nome;

    @FXML
    private Label password;

    @FXML
    private Label telefone;

    @FXML
    private Label utilizadorId;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Funcionario funcionario: Repositorio.getRepositorio().getFuncionarios()){
            FuncionariosListView.getItems().addAll(funcionario.getNome());
        }

        FuncionariosListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Funcionario funcionario: Repositorio.getRepositorio().getFuncionarios()){
                    if(FuncionariosListView.getSelectionModel().getSelectedItem().equals(funcionario.getNome())){
                        cc.setText(String.valueOf(funcionario.getCC()));
                        localidade.setText(funcionario.getLocalidade());
                        morada.setText(funcionario.getMorada());
                        nif.setText(String.valueOf(funcionario.getNIF()));
                        nome.setText(funcionario.getNome());
                        password.setText(funcionario.getPassword());
                        telefone.setText(String.valueOf(funcionario.getTelefone()));
                        utilizadorId.setText(funcionario.getUtilizador());
                    }
                }

            }
        });
    }

    public void voltarAtras(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuAdmins.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Admins");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao tentar acessar ao menu listar clientes: " + ex.getMessage());
        }
    }

}
