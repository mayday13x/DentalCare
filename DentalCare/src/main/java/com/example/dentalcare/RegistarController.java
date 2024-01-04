package com.example.dentalcare;

import com.example.dentalcare.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistarController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField cc;

    @FXML
    private TextField localidade;

    @FXML
    private TextField morada;

    @FXML
    private TextField nif;

    @FXML
    private TextField nome;

    @FXML
    private TextField telefone;

    @FXML
    private ChoiceBox<String> tipoUtilizador;

    @FXML
    private TextField utilizadorId;

    @FXML
    private Label nifError;

    @FXML
    private Label telefoneError;

    @FXML
    private Label ccError;

    @FXML
    private Label utilizadorError;

    boolean ccEstado = false;

    boolean nifEstado = false;

    boolean telefoneEstado = false;

    boolean utilizadorEstado = false;


    @FXML
    public void voltarAtras(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Login");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro: " + ex.getMessage());
        }


    }

    @FXML
    public void registar(ActionEvent event) {
        if(telefoneEstado && ccEstado && nifEstado && utilizadorEstado){
            try{
                if(tipoUtilizador.getValue().equals("Cliente")){
                    Cliente cl = new Cliente();
                    cl.setUtilizador(utilizadorId.getText());
                    cl.setPassword(password.getText());
                    cl.setNome(nome.getText());
                    cl.setCC(Integer.parseInt(cc.getText()));
                    cl.setNIF(Integer.parseInt(nif.getText()));
                    cl.setTelefone(Integer.parseInt(telefone.getText()));
                    cl.setMorada(morada.getText());
                    cl.setLocalidade(localidade.getText());

                    ClienteBLL.registarCliente(cl);

                } else if(tipoUtilizador.getValue().equals("Dono de empresa")){
                    Dono dn = new Dono();
                    dn.setUtilizador(utilizadorId.getText());
                    dn.setPassword(password.getText());
                    dn.setNome(nome.getText());
                    dn.setCC(Integer.parseInt(cc.getText()));
                    dn.setNIF(Integer.parseInt(nif.getText()));
                    dn.setTelefone(Integer.parseInt(telefone.getText()));
                    dn.setMorada(morada.getText());
                    dn.setLocalidade(localidade.getText());
                    dn.setEstado(EstadoDonoEmpresa.ATIVADA);

                    DonoBLL.registarDono(dn);
                }
            }catch (Exception e){
                System.out.println("Erro ao registar utilizador: " + e.getMessage());
            }



            try {
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Login");
                stage.show();
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        tipoUtilizador.getItems().addAll("Cliente", "Dono de empresa");
        ccError.setVisible(false);
        nifError.setVisible(false);
        telefoneError.setVisible(false);
    }

    @FXML
    void verificarNif(KeyEvent event){
        String text = nif.getText();
        if(text.matches("[0-9]*")){
            nifError.setVisible(false);
            nifEstado = true;

            if(text.length() != 9){
                nifError.setVisible(true);
                nifEstado = false;
            }
        }else {
            nifError.setVisible(true);
            nifEstado = false;
        }
    }

    @FXML
    void verificarCC(KeyEvent event){
        String text = cc.getText();

        if(text.matches("[0-9]*")){
            ccError.setVisible(false);
            ccEstado = true;

            if(text.length() != 8){
                ccError.setVisible(true);
                ccEstado = false;
            }
        }
        else {
            ccError.setVisible(true);
            ccEstado = false;
        }
    }

    @FXML
    void verificarTelefone(KeyEvent event){
        String text = telefone.getText();

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
    void verificarUtilizador(KeyEvent event){
        String text = utilizadorId.getText();
        utilizadorError.setVisible(false);
        utilizadorEstado = true;

        for (Cliente cliente: Repositorio.getRepositorio().getClientes()){
            if(cliente.getUtilizador().equals(text)){
                utilizadorError.setVisible(true);
                utilizadorEstado = false;
            }
        }

        for (Dono dono: Repositorio.getRepositorio().getDonos()){
            if(dono.getUtilizador().equals(text)){
                utilizadorError.setVisible(true);
                utilizadorEstado = false;
            }
        }

        for(Admin admin: Repositorio.getRepositorio().getAdmins()){
            if(admin.getUtilizador().equals(text)){
                utilizadorError.setVisible(true);
                utilizadorEstado = false;
            }
        }

        for(Funcionario funcionario: Repositorio.getRepositorio().getFuncionarios()){
            if(funcionario.getUtilizador().equals(text)){
                utilizadorError.setVisible(true);
                utilizadorEstado = false;
            }
        }
    }

}
