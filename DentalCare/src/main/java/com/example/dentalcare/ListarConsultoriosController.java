package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


public class ListarConsultoriosController implements Initializable{

    @FXML
    private ListView<String> consultorioListView;

    @FXML
    private TextField empresaConsultorio;

    @FXML
    private TextField especialidadeConsultorio;

    @FXML
    private ListView<String> funcionarioListView;

    @FXML
    private TextField localidadeConsultorio;

    @FXML
    private TextField moradaConsultorio;

    @FXML
    private TextField nomeConsultorio;

    @FXML
    private TextField telefoneConsultorio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empresaConsultorio.setEditable(false);
        especialidadeConsultorio.setEditable(false);
        localidadeConsultorio.setEditable(false);
        moradaConsultorio.setEditable(false);
        nomeConsultorio.setEditable(false);
        telefoneConsultorio.setEditable(false);

        for(List<Consultorio> consultorio: Repositorio.getRepositorio().getConsultorios().values()){
            List<Consultorio> consultorioList = consultorio;

            for (Consultorio consultorio1: consultorioList){
                consultorioListView.getItems().addAll(consultorio1.getNome());
            }
        }

        consultorioListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(List<Consultorio> consultorio: Repositorio.getRepositorio().getConsultorios().values()){
                    List<Consultorio> consultorioList = consultorio;

                    for (Consultorio consultorio1: consultorioList){
                        if(consultorio1.getNome().equals(consultorioListView.getSelectionModel().getSelectedItem())){
                           especialidadeConsultorio.setText(consultorio1.getEspecialidade());
                           localidadeConsultorio.setText(consultorio1.getLocalidade());
                           moradaConsultorio.setText(consultorio1.getMorada());
                           nomeConsultorio.setText(consultorio1.getNome());
                           telefoneConsultorio.setText(consultorio1.getNumTelefone());
                        }
                    }
                }

                for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                    List<Consultorio>  consultorioList = empresa.getConsultorios();

                    for(Consultorio consultorio: consultorioList){
                        if(consultorio.getNome().equals(consultorioListView.getSelectionModel().getSelectedItem())){
                            empresaConsultorio.setText(empresa.getNome());

                            List<Funcionario> funcionariosList = consultorio.getFuncionarios();
                            for(Funcionario funcionario: funcionariosList){
                                funcionarioListView.getItems().addAll(funcionario.getNome());
                            }
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
