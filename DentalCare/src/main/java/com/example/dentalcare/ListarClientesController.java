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

public class ListarClientesController implements Initializable{

    @FXML
    private Label cc;

    @FXML
    private ListView<String> clientesListView;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Cliente cliente: Repositorio.getRepositorio().getClientes()){
            clientesListView.getItems().addAll(cliente.getNome());
        }

        clientesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Cliente cliente: Repositorio.getRepositorio().getClientes()){
                    if(clientesListView.getSelectionModel().getSelectedItem().equals(cliente.getNome())){
                        cc.setText(String.valueOf(cliente.getCC()));
                        localidade.setText(cliente.getLocalidade());
                        morada.setText(cliente.getMorada());
                        nif.setText(String.valueOf(cliente.getNIF()));
                        nome.setText(cliente.getNome());
                        password.setText(cliente.getPassword());
                        telefone.setText(String.valueOf(cliente.getTelefone()));
                        utilizadorId.setText(cliente.getUtilizador());
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
