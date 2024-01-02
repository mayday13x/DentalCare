package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsultarDadosEmpresaController implements Initializable {

    @FXML
    private ChoiceBox<String> escolherEmpresa;

    @FXML
    private TextField nomeEmpresa;

    @FXML
    private TextField moradaEmpresa;

    @FXML
    private TextField NumTelefone;

    @FXML
    private TextField localidadeEmpresa;

    @FXML
    private ToggleButton alterarDados;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        nomeEmpresa.setEditable(false);
        moradaEmpresa.setEditable(false);
        localidadeEmpresa.setEditable(false);
        NumTelefone.setEditable(false);
        Repositorio repo = Repositorio.getRepositorio();

        for(Empresa empresa: repo.getEmpresas().values()){
            if(Objects.equals(empresa.getDono().getNome(), DataSessao.dono.getNome()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                escolherEmpresa.getItems().addAll(empresa.getNome());
            }
        }
    }

    public void visualizarDados(ActionEvent event){

        for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
            if(escolherEmpresa.getValue().equals(empresa.getNome())){
                nomeEmpresa.setText(empresa.getNome());
                moradaEmpresa.setText(empresa.getMorada());
                localidadeEmpresa.setText(empresa.getLocalidade());
                NumTelefone.setText(empresa.getNumTelefone());
            }
        }
    }

    public void alterarDados(ActionEvent event){
        if(alterarDados.isSelected()){
            nomeEmpresa.setEditable(true);
            moradaEmpresa.setEditable(true);
            localidadeEmpresa.setEditable(true);
            NumTelefone.setEditable(true);

            for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                if(escolherEmpresa.getValue().equals(empresa.getNome())){
                    nomeEmpresa.setText(empresa.getNome());
                    moradaEmpresa.setText(empresa.getMorada());
                    localidadeEmpresa.setText(empresa.getLocalidade());
                    NumTelefone.setText(empresa.getNumTelefone());
                }
            }
        }else if (!alterarDados.isSelected()){
            nomeEmpresa.setEditable(false);
            moradaEmpresa.setEditable(false);
            localidadeEmpresa.setEditable(false);
            NumTelefone.setEditable(false);
        }
    }

    public void guardarDados(ActionEvent event){
        Empresa emp = new Empresa();

        emp.setNome(nomeEmpresa.getText());
        emp.setMorada(moradaEmpresa.getText());
        emp.setLocalidade(localidadeEmpresa.getText());
        emp.setNumTelefone(NumTelefone.getText());
        emp.setDono(DataSessao.dono);

        EmpresaBLL.criarEmpresa(DataSessao.dono, emp);
    }

    @FXML
    public void voltarAtras(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Dono");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao acessar menu dono: " + ex.getMessage());
        }
    }
}
