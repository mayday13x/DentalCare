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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CriarServicoController implements Initializable {

    @FXML
    private ChoiceBox<String> escolherEmpresa;

    @FXML
    private TextField nomeServico;

    @FXML
    private TextField precoServico;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Repositorio repo = Repositorio.getRepositorio();

        for(Empresa empresa: repo.getEmpresas().values()){
            if(Objects.equals(empresa.getDono().getNome(), DataSessao.dono.getNome()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                escolherEmpresa.getItems().addAll(empresa.getNome());
            }
        }
    }

    public void criarServico(ActionEvent event){

        try{
            Servico servi = new Servico();

            servi.setIdServico(Repositorio.getRepositorio().getServicos().size() + 1);
            servi.setNomeServico(nomeServico.getText());
            servi.setPrecoServico(Float.parseFloat(precoServico.getText()));
            String empresa = escolherEmpresa.getValue();

            for(Empresa emp: Repositorio.getRepositorio().getEmpresas().values()){
                if(emp.getNome().equals(empresa)){
                servi.setEmpresa(emp.getNome());
                }
            }

            System.out.println("Nome: " + servi.getNomeServico());
            System.out.println("Id: " + servi.getIdServico());
            System.out.println("Preco: " + servi.getPrecoServico());
            System.out.println("Empresa: " + servi.getEmpresa());

            ServicoBLL.adicionarServico(servi);
        }catch (Exception e){
            System.out.println("Erro ao adicionar servico: " + e.getMessage());
            e.printStackTrace();
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
