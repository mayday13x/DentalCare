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

public class ListarDonosController implements Initializable {

    @FXML
    private ListView<String> DonosListView;

    @FXML
    private ListView<String> EmpresasListView1;

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


        for(Dono dono: Repositorio.getRepositorio().getDonos()){
            DonosListView.getItems().addAll(dono.getNome());
        }

        DonosListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Dono dono: Repositorio.getRepositorio().getDonos()){
                    if(DonosListView.getSelectionModel().getSelectedItem().equals(dono.getNome())){
                        cc.setText(String.valueOf(dono.getCC()));
                        localidade.setText(dono.getLocalidade());
                        morada.setText(dono.getMorada());
                        nif.setText(String.valueOf(dono.getNIF()));
                        nome.setText(dono.getNome());
                        password.setText(dono.getPassword());
                        telefone.setText(String.valueOf(dono.getTelefone()));
                        utilizadorId.setText(dono.getUtilizador());
                    }


                }

                for(Empresa empresa:Repositorio.getRepositorio().getEmpresas().values()) {
                    if (empresa.getDono().getNome().equals(DonosListView.getSelectionModel().getSelectedItem())){
                            EmpresasListView1.getItems().addAll(empresa.getNome());
                    }
                }

            }
        });
    }

    @FXML
    void voltarAtras(ActionEvent event) {
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
