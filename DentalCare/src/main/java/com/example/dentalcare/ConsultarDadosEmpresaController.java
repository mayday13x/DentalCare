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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
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

    @FXML
    private Label localidadeLabel;

    @FXML
    private Label moradaLabel;

    @FXML
    private Label nomeLabel;

    @FXML
    private Label telefoneLabel;


    @FXML
    private Label telefoneError;

    boolean telefoneEstado = false;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Repositorio repo = Repositorio.getRepositorio();

        for(Empresa empresa: repo.getEmpresas().values()){
            if(Objects.equals(empresa.getDono().getNome(), DataSessao.dono.getNome()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                escolherEmpresa.getItems().addAll(empresa.getNome());
            }
        }
        escolherEmpresa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                    if(escolherEmpresa.getValue().equals(empresa.getNome())){
                        nomeLabel.setText(empresa.getNome());
                        moradaLabel.setText(empresa.getMorada());
                        localidadeLabel.setText(empresa.getLocalidade());
                        telefoneLabel.setText(empresa.getNumTelefone());
                    }
                }
            }
        });
    }


    public void alterarDados(ActionEvent event){
        if(alterarDados.isSelected()){
            mudarEstado(true);
            nomeLabel.setVisible(false);
            moradaLabel.setVisible(false);
            localidadeLabel.setVisible(false);
            telefoneLabel.setVisible(false);

            for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                if(escolherEmpresa.getValue().equals(empresa.getNome())){
                    nomeEmpresa.setText(empresa.getNome());
                    moradaEmpresa.setText(empresa.getMorada());
                    localidadeEmpresa.setText(empresa.getLocalidade());
                    NumTelefone.setText(empresa.getNumTelefone());
                }
            }
        }else if (!alterarDados.isSelected()){
          mudarEstado(false);
            nomeLabel.setVisible(true);
            moradaLabel.setVisible(true);
            localidadeLabel.setVisible(true);
            telefoneLabel.setVisible(true);
        }
    }

    public void guardarDados(ActionEvent event){
        if (telefoneEstado) {
            try {
                Empresa emp = new Empresa();

                emp.setNome(nomeEmpresa.getText());
                emp.setMorada(moradaEmpresa.getText());
                emp.setLocalidade(localidadeEmpresa.getText());
                emp.setNumTelefone(NumTelefone.getText());
                emp.setDono(DataSessao.dono);

                EmpresaBLL.criarEmpresa(DataSessao.dono, emp);
            }catch (Exception e){
                System.out.println("Erro ao tentar guardar os dados");
            }
        }
    }

    @FXML
    void mudarEstado(boolean status){
        nomeEmpresa.setVisible(status);
        moradaEmpresa.setVisible(status);
        localidadeEmpresa.setVisible(status);
        NumTelefone.setVisible(status);
        nomeEmpresa.setEditable(status);
        moradaEmpresa.setEditable(status);
        localidadeEmpresa.setEditable(status);
        NumTelefone.setEditable(status);
    }

    @FXML
    void verifyTelefone(KeyEvent event){
        String text =NumTelefone.getText();

        if(text.matches("[0-9]*")){
            telefoneError.setVisible(false);
            telefoneEstado = true;

            if(text.length() != 9){
                telefoneError.setVisible(true);
                telefoneEstado = false;
            }
        }else {
            telefoneError.setVisible(true);
            telefoneEstado = false;
        }
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
