package com.example.dentalcare;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListarEmpresasController implements Initializable {
    @FXML
    private ListView<String> empresaListView;

    @FXML
    private ListView<String> consultoriosViewList;

    @FXML
    private TextField localidadeEmpresa;

    @FXML
    private TextField moradaEmpresa;

    @FXML
    private TextField nomeEmpresa;

    @FXML
    private TextField telefoneEmpresa;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localidadeEmpresa.setEditable(false);
        moradaEmpresa.setEditable(false);
        nomeEmpresa.setEditable(false);
        telefoneEmpresa.setEditable(false);

        for(Empresa empresa:Repositorio.getRepositorio().getEmpresas().values()){
            empresaListView.getItems().addAll(empresa.getNome());
        }

        empresaListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Empresa empresa:Repositorio.getRepositorio().getEmpresas().values()){
                    if(empresa.getNome().equals(empresaListView.getSelectionModel().getSelectedItem())){
                        localidadeEmpresa.setText(empresa.getLocalidade());
                        moradaEmpresa.setText(empresa.getMorada());
                        nomeEmpresa.setText(empresa.getNome());
                        telefoneEmpresa.setText(empresa.getNumTelefone());

                        List<Consultorio> consultoriosList = empresa.getConsultorios();
                        for(Consultorio consultorio: consultoriosList){
                            consultoriosViewList.getItems().addAll(consultorio.getNome());
                        }
                    }

                }
            }
        });

    }
}
