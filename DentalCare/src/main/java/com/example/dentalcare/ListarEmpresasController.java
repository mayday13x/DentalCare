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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
