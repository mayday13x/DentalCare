package com.example.dentalcare;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListarMarcacoesPorEmpresaConsultorioController implements Initializable {

    @FXML
    private ListView<String> consultorioListView;

    @FXML
    private ListView<String> empresaListView;

    @FXML
    private  ListView<Integer> marcacoesListView;

    @FXML
    private Label numMarcacoes;

    public ListarMarcacoesPorEmpresaConsultorioController() {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Empresa empresa:Repositorio.getRepositorio().getEmpresas().values()){
            empresaListView.getItems().addAll(empresa.getNome());
        }

        empresaListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Empresa empresa:Repositorio.getRepositorio().getEmpresas().values()){
                    List<Consultorio> consultorioList = empresa.getConsultorios();

                    for(Consultorio consultorio: consultorioList){
                        consultorioListView.getItems().addAll(consultorio.getNome());
                    }
                }
                int count = 0;
                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                    if(consulta.getEmpresa().equals(empresaListView.getSelectionModel().getSelectedItem())){
                        marcacoesListView.getItems().addAll(consulta.getIdConsulta());
                        count++;
                    }
                }
                numMarcacoes.setText("Numero de marcacoes: " + count);
            }
        });

        consultorioListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                marcacoesListView.getItems().clear();
                int count = 0;

                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                    if(consulta.getConsultorio().equals(consultorioListView.getSelectionModel().getSelectedItem())){
                        marcacoesListView.getItems().addAll(consulta.getIdConsulta());
                        count++;
                    }
                }
                numMarcacoes.setText("Numero de marcacoes: " + count);
            }
        });


    }
}
