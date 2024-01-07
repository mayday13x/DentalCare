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

public class ListarMarcacoesController implements Initializable {

    @FXML
    private ListView<Integer> MarcacoesListView;

    @FXML
    private Label Funcionario;

    @FXML
    private Label Servico;

    @FXML
    private Label cliente;

    @FXML
    private Label dataMarcada;

    @FXML
    private Label especialidadeConsultorio;

    @FXML
    private Label precoTotal;

    @FXML
    private Label estado;

    @FXML
    private Label horaConsulta;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
            MarcacoesListView.getItems().addAll(consulta.getIdConsulta());
        }

        MarcacoesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                    if(consulta.getIdConsulta() == MarcacoesListView.getSelectionModel().getSelectedItem()){
                        Funcionario.setText(consulta.getFuncionario());
                        Servico.setText(consulta.getServico());
                        cliente.setText(consulta.getCliente().getNome());
                        dataMarcada.setText(String.valueOf(consulta.getDataConsulta()));
                        especialidadeConsultorio.setText(consulta.getEspecialidade());
                        precoTotal.setText(String.valueOf(consulta.getPrecoTotal()));
                        estado.setText(String.valueOf(consulta.getEstadoConsulta()));
                        horaConsulta.setText(consulta.getHoraConsulta());
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