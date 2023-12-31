package com.example.dentalcare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DesativarDonosEmpresasController implements Initializable {

    @FXML
    private ListView<String> DonosListView;

    @FXML
    private ListView<String> EmpresasListView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Dono donos: Repositorio.getRepositorio().getDonos()){
            DonosListView.getItems().addAll(donos.getNome());
        }

        for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
            EmpresasListView.getItems().addAll(empresa.getNome());
        }

    }

    public void Ativar(ActionEvent event){

        for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
            if(empresa.getNome().equals(EmpresasListView.getSelectionModel().getSelectedItem()) && empresa.getEstado().equals(EstadoDonoEmpresa.DESATIVADA)){
                try {
                    empresa.setEstado(EstadoDonoEmpresa.ATIVADA);

                    System.out.println("A empresa foi ativada com sucesso!");
                    Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");

                }catch (Exception e){
                    System.out.println("Erro ao tentar ativar a empresa: " + e.getMessage());
                }
            }else if(empresa.getNome().equals(EmpresasListView.getSelectionModel().getSelectedItem()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                try{
                    Alert alertContaAtiva = new Alert(Alert.AlertType.WARNING);
                    alertContaAtiva.setTitle("Aviso!");
                    alertContaAtiva.setHeaderText("Conta ja esta Ativa!");
                    alertContaAtiva.show();
                }catch (Exception e){
                    System.out.println("Erro ao tentar mostrar aviso: " + e.getMessage());
                }
            }
        }

        for(Dono dono: Repositorio.getRepositorio().getDonos()){
            if(dono.getNome().equals(DonosListView.getSelectionModel().getSelectedItem()) && dono.getEstado().equals(EstadoDonoEmpresa.DESATIVADA)){
                try {
                    dono.setEstado(EstadoDonoEmpresa.ATIVADA);

                    System.out.println("O Dono foi ativado com sucesso!");
                    Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");

                }catch (Exception e){
                    System.out.println("Erro ao tentar ativar o dono: " + e.getMessage());
                }
            }else if(dono.getNome().equals(DonosListView.getSelectionModel().getSelectedItem()) && dono.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                try{
                    Alert alertContaAtiva = new Alert(Alert.AlertType.WARNING);
                    alertContaAtiva.setTitle("Aviso!");
                    alertContaAtiva.setHeaderText("O dono ja esta Ativo!");
                    alertContaAtiva.show();
                }catch (Exception e){
                    System.out.println("Erro ao tentar mostrar aviso: " + e.getMessage());
                }

            }
        }
    }

    public void Desativar(ActionEvent event){

        for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
            if(empresa.getNome().equals(EmpresasListView.getSelectionModel().getSelectedItem()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                try {
                    empresa.setEstado(EstadoDonoEmpresa.ATIVADA);

                    System.out.println("A empresa foi desativada com sucesso!");
                    Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");

                }catch (Exception e){
                    System.out.println("Erro ao tentar desativar a empresa: " + e.getMessage());
                }
            }else if(empresa.getNome().equals(EmpresasListView.getSelectionModel().getSelectedItem()) && empresa.getEstado().equals(EstadoDonoEmpresa.DESATIVADA)){
                try{
                    Alert alertContaAtiva = new Alert(Alert.AlertType.WARNING);
                    alertContaAtiva.setTitle("Aviso!");
                    alertContaAtiva.setHeaderText("Conta ja esta Desativada!");
                    alertContaAtiva.show();
                }catch (Exception e){
                    System.out.println("Erro ao tentar mostrar aviso: " + e.getMessage());
                }
            }
        }

        for(Dono dono: Repositorio.getRepositorio().getDonos()){
            if(dono.getNome().equals(DonosListView.getSelectionModel().getSelectedItem()) && dono.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                try {
                    dono.setEstado(EstadoDonoEmpresa.DESATIVADA);

                    System.out.println("O Dono foi desativado com sucesso!");
                    Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");

                }catch (Exception e){
                    System.out.println("Erro ao tentar desativar o dono: " + e.getMessage());
                }
            }else if(dono.getNome().equals(DonosListView.getSelectionModel().getSelectedItem()) && dono.getEstado().equals(EstadoDonoEmpresa.DESATIVADA)){
                try{
                    Alert alertContaAtiva = new Alert(Alert.AlertType.WARNING);
                    alertContaAtiva.setTitle("Aviso!");
                    alertContaAtiva.setHeaderText("Dono ja esta Desativado!");
                    alertContaAtiva.show();
                }catch (Exception e){
                    System.out.println("Erro ao tentar mostrar aviso: " + e.getMessage());
                }
            }



            }
        }

    public void voltarAtras(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuAdmins.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Admins");
            stage.show();
        }catch (Exception e){
            System.out.println("Erro ao carregar o menu admin: " + e.getMessage());
        }
    }
}
