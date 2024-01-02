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

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PagamentoConsultaController implements Initializable {

    @FXML
    private TextField clienteConsulta;

    @FXML
    private ListView<Integer> consultaListView;

    @FXML
    private TextField estadoConsulta;

    @FXML
    private TextField idConsulta;

    Consulta consultaAtual;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        clienteConsulta.setEditable(false);
        estadoConsulta.setEditable(false);
        idConsulta.setEditable(false);

        for(Consulta consulta:Repositorio.getRepositorio().getConsultas()){
            for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                if(consulta.getCliente().getNome().equals(DataSessao.cliente.getNome())
                        && consulta.getEstadoConsulta() == EstadoConsulta.NAOPAGA && empresa.getNome().equals(consulta.getEmpresa()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                    consultaListView.getItems().addAll(consulta.getIdConsulta());
                }
            }

        }

        consultaListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                    if (Objects.equals(consulta.getCliente().getNome(), DataSessao.cliente.getNome()) && consulta.getIdConsulta() == consultaListView.getSelectionModel().getSelectedItem()) {
                        consultaAtual = consulta;
                    }
                }

                clienteConsulta.setText(consultaAtual.getCliente().getNome());
                estadoConsulta.setText(String.valueOf(consultaAtual.getEstadoConsulta()));
                idConsulta.setText(String.valueOf(consultaAtual.getIdConsulta()));
            }
        });
    }

    public void pagarConsulta(ActionEvent event){

        for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
            int id =  Integer.parseInt(idConsulta.getText());
            if(consulta.getIdConsulta() == id){
                consulta.setEstadoConsulta(EstadoConsulta.PAGA);
            }
            estadoConsulta.setText(String.valueOf(consulta.getEstadoConsulta()));

        }
        System.out.println("O pagamento foi bem sucedido!");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }

    @FXML
    public void voltarAtras(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("menuCliente.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Cliente");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao acessar Menu cliente: " + ex.getMessage());
        }


    }
}
