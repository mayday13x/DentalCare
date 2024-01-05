package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CriarConsultorioController implements Initializable {

    @FXML
    private ChoiceBox<String> escolherEmpresa;

    @FXML
    private TextField nomeConsultorio;

    @FXML
    private ChoiceBox<String> especialidadeConsultorio;

    @FXML
    private TextField localidadeConsultorio;

    @FXML
    private TextField moradaConsultorio;

    @FXML
    private TextField telefoneConsultorio;

    @FXML
    private Label telefoneError;

    boolean telefoneEstado = false;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        telefoneError.setVisible(false);
        Repositorio repo = Repositorio.getRepositorio();

        for(Empresa empresa: repo.getEmpresas().values()){
            if(Objects.equals(empresa.getDono().getNome(), DataSessao.dono.getNome()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                escolherEmpresa.getItems().addAll(empresa.getNome());
            }
        }

        especialidadeConsultorio.getItems().addAll("Geral", "LIMPEZA DENTES", "DESTARTARIZACAO");

    }

    @FXML
    void verifyTelefone(KeyEvent event){
        String text = telefoneConsultorio.getText();

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
    public void criarConsulturio(ActionEvent event){
            try {
                if(nomeConsultorio.getText().isEmpty() || moradaConsultorio.getText().isEmpty() || localidadeConsultorio.getText().isEmpty()
                        || telefoneConsultorio.getText().isEmpty() || especialidadeConsultorio.getValue().isEmpty() || escolherEmpresa.getValue().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Campos obrigatorios por preencher");
                    alert.setTitle("Aviso");
                    alert.show();
                }else if(telefoneEstado){
                    Consultorio con = new Consultorio();
                    List<Consultorio> consultorioList = new ArrayList<>();

                    con.setNome(nomeConsultorio.getText());
                    con.setMorada(moradaConsultorio.getText());
                    con.setLocalidade(localidadeConsultorio.getText());
                    con.setNumTelefone(telefoneConsultorio.getText());
                    con.setEspecialidade(especialidadeConsultorio.getValue());
                    String nomeEmpresa = escolherEmpresa.getValue();

                    consultorioList.add(con);


                    for(Empresa emp: Repositorio.getRepositorio().getEmpresas().values()){
                        if(emp.getNome().equals(nomeEmpresa)){
                            ConsultorioBLL.AdicionarConsultorio(emp,consultorioList);
                            ConsultorioBLL.AdiconarConsultorioEmp(emp, con);
                        }
                    }

                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
                        Scene regCena = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(regCena);
                        stage.setTitle("Menu Donos");
                        stage.show();
                    }catch (IOException ex){
                        System.out.println("Erro no login: " + ex.getMessage());
                    }
                }

            }catch (Exception e){
                System.out.println("Erro ao cria consultorio: " + e.getMessage());
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
