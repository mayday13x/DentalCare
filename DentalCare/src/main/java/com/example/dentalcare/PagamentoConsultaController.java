package com.example.dentalcare;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
            if(consulta.getCliente().getNome().equals(DataSessao.cliente.getNome())&& consulta.getEstadoConsulta() == EstadoConsulta.NAOPAGA){
                consultaListView.getItems().addAll(consulta.getIdConsulta());
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


}
