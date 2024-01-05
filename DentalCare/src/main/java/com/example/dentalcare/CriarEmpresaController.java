package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CriarEmpresaController implements Initializable {


    @FXML
    private TextField localidadeEmpresa;

    @FXML
    private TextField moradaEmpresa;

    @FXML
    private TextField nomeEmpresa;

    @FXML
    private TextField telefoneEmpresa;

    @FXML
    private Label telefoneError;

    @FXML
    private Label nomeError;
    boolean telefoneEstado = false;

    boolean nomeEstado;

    public void criarEmpresa(ActionEvent event){
        if(localidadeEmpresa.getText().isEmpty() || moradaEmpresa.getText().isEmpty() || nomeEmpresa.getText().isEmpty()
                || telefoneEmpresa.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Campos obrigatorios por preencher");
            alert.setTitle("Aviso");
            alert.show();
        }else if(telefoneEstado && nomeEstado){
            try{
                Empresa emp = new Empresa();

                emp.setNome(nomeEmpresa.getText());
                emp.setMorada(moradaEmpresa.getText());
                emp.setLocalidade(localidadeEmpresa.getText());
                emp.setNumTelefone(telefoneEmpresa.getText());
                emp.setDono(DataSessao.dono);
                emp.setEstado(EstadoDonoEmpresa.ATIVADA);

                EmpresaBLL.criarEmpresa(DataSessao.dono, emp);
                EmpresaBLL.localidaEmpresa(localidadeEmpresa.getText(),emp);
            }catch (Exception e){
                System.out.println("Erro ao criar empresa: " + e.getMessage());
            }

            try {
                Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu Donos");
                stage.show();
            }catch (IOException ex){
                System.out.println("Erro ao carregar o menu: " + ex.getMessage());
            }
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

    @FXML
    void verifyTelefone(KeyEvent event){
        String text = telefoneEmpresa.getText();

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
    void verifyNome(KeyEvent event){
        String text = nomeEmpresa.getText();
        nomeError.setVisible(false);
        nomeEstado = true;


        for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
            if(empresa.getNome().equals(text)){
                nomeError.setVisible(true);
                nomeEstado = false;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        telefoneError.setVisible(false);
    }
}
