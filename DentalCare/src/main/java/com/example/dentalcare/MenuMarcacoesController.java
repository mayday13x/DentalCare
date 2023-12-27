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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuMarcacoesController  implements Initializable {

    @FXML
    private Button Alterar;

    @FXML
    private Button Cancelar;

    @FXML
    private Button Guardar;

    @FXML
    private TextField clienteConsulta;

    @FXML
    private TextField dataConulta;

    @FXML
    private ListView<Integer> escolherConsultas;

    @FXML
    private TextField especialidadeConsulta;

    @FXML
    private TextField estadoConsulta;

    @FXML
    private ChoiceBox<EstadoConsulta> estadoChoiceBox;

    @FXML
    private TextField funcionarioConsulta;

    @FXML
    private TextField idConsulta;

    @FXML
    private TextField precoConsulta;

    @FXML
    private TextField servicoConsulta;

    Consulta consultaAtual;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        clienteConsulta.setEditable(false);
        dataConulta.setEditable(false);
        especialidadeConsulta.setEditable(false);
        precoConsulta.setEditable(false);
        servicoConsulta.setEditable(false);
        idConsulta.setEditable(false);
        funcionarioConsulta.setEditable(false);
        estadoConsulta.setEditable(false);


        for(Consulta consulta:Repositorio.getRepositorio().getConsultas()){
            if(consulta.getFuncionario().equals(DataSessao.funcionario.getNome())){
                escolherConsultas.getItems().addAll(consulta.getIdConsulta());
                }
            }
            escolherConsultas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observableValue, Integer s, Integer t1) {
                    for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                        if (Objects.equals(consulta.getFuncionario(), DataSessao.funcionario.getNome()) && consulta.getIdConsulta() == escolherConsultas.getSelectionModel().getSelectedItem()) {
                            consultaAtual = consulta;
                        }
                    }

                    idConsulta.setText(String.valueOf(consultaAtual.getIdConsulta()));
                    dataConulta.setText(String.valueOf(consultaAtual.getDataConsulta()));
                    especialidadeConsulta.setText(consultaAtual.getEspecialidade());
                    precoConsulta.setText(String.valueOf(consultaAtual.getPrecoTotal()));
                    funcionarioConsulta.setText(consultaAtual.getFuncionario());
                    servicoConsulta.setText(consultaAtual.getServico());
                    clienteConsulta.setText(String.valueOf(consultaAtual.getCliente().getNome()));
                    estadoConsulta.setText(String.valueOf(consultaAtual.getEstadoConsulta()));


                }
            });
        }

        public void AlterarMarcacoes(ActionEvent event){
            estadoConsulta.setVisible(false);
            Alterar.setVisible(false);
            estadoChoiceBox.setVisible(true);
            Cancelar.setVisible(true);
            Guardar.setVisible(true);
            estadoChoiceBox.getItems().addAll(EstadoConsulta.values());
        }

        public void GuardarAlteracoes(ActionEvent event){
            try {
                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()) {
                    if (Objects.equals(consulta.getFuncionario(), DataSessao.funcionario.getNome()) && consulta.getIdConsulta() == escolherConsultas.getSelectionModel().getSelectedItem()) {
                        if(estadoChoiceBox.getValue() == EstadoConsulta.CONFIRMADA){
                            consulta.setEstadoConsulta(EstadoConsulta.CONFIRMADA);
                        } if(estadoChoiceBox.getValue() == EstadoConsulta.CANCELADA){
                            consulta.setEstadoConsulta(EstadoConsulta.CANCELADA);
                        } if(estadoChoiceBox.getValue() == EstadoConsulta.PAGA){
                            consulta.setEstadoConsulta(EstadoConsulta.PAGA);
                        } if(estadoChoiceBox.getValue() == EstadoConsulta.NAOPAGA){
                            consulta.setEstadoConsulta(EstadoConsulta.NAOPAGA);
                        } if(estadoChoiceBox.getValue() == EstadoConsulta.PROCESSADA){
                            consulta.setEstadoConsulta(EstadoConsulta.PROCESSADA);
                        }
                    }
                }

                System.out.println("Os dados foram alterados com sucesso!");
                Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
                voltarAtras(event);

            }catch (Exception e){
                System.out.println("Erro ao guardar alteracoes na consulata!" + e.getMessage());
            }
        }

        public void voltarAtras(ActionEvent event){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("menuMarcacoes.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Consulatar Marcacoes");
                stage.show();
            }catch (IOException ex){
                System.out.println("Erro: " + ex.getMessage());
            }
        }



        }


