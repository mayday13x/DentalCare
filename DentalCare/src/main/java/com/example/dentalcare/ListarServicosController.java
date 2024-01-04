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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ListarServicosController implements Initializable {

    @FXML
    private Label empresaServico;

    @FXML
    private Label nomeServico;

    @FXML
    private Label precoServico;

    @FXML
    private Label idServico;

    @FXML
    private ListView<String> servicoListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Servico servico: Repositorio.getRepositorio().getServicos()){
            for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                if(empresa.getNome().equals(servico.getEmpresa()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                    servicoListView.getItems().addAll(servico.getNomeServico());
                }
            }

        }

        servicoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Servico servico: Repositorio.getRepositorio().getServicos()){
                    if(servico.getNomeServico().equals(servicoListView.getSelectionModel().getSelectedItem())){
                        empresaServico.setText(servico.getEmpresa());
                        nomeServico.setText(servico.getNomeServico());
                        idServico.setText(String.valueOf(servico.getIdServico()));
                        precoServico.setText(String.valueOf(servico.getPrecoServico()));
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
