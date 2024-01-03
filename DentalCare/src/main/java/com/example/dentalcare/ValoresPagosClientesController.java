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

public class ValoresPagosClientesController implements Initializable {
    @FXML
    private Label TextCliente;

    @FXML
    private ListView<String> clientesListView;

    @FXML
    private TextField valorCliente;

    @FXML
    private TextField valorTotal;

    @FXML
    private TextField valoresDentalCare;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        float totalpago = 0;


        for(Cliente cliente:Repositorio.getRepositorio().getClientes()){
        clientesListView.getItems().addAll(cliente.getNome());
        }


        for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
            totalpago+= consulta.getPrecoTotal();
        }

        float totalDentalCare = (float) (totalpago * 0.05);
        valorTotal.setText(String.valueOf(totalpago));
        valoresDentalCare.setText(String.valueOf(totalDentalCare));

        clientesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                float totalcliente = 0;


                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                    if(consulta.getCliente().getNome().equals(clientesListView.getSelectionModel().getSelectedItem())){
                        TextCliente.setText("Valor pago pelo cliente " + consulta.getCliente().getNome());
                        totalcliente+= consulta.getPrecoTotal();

                    }
                }

                valorCliente.setText(String.valueOf(totalcliente));
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
