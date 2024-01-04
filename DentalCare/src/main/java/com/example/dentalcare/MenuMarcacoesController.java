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
    private Label clienteConsulta;

    @FXML
    private Label dataConsulta;

    @FXML
    private ListView<Integer> escolherConsultas;

    @FXML
    private Label especialidadeConsulta;

    @FXML
    private Label estadoConsulta;

    @FXML
    private ChoiceBox<EstadoConsulta> estadoChoiceBox;

    @FXML
    private Label funcionarioConsulta;

    @FXML
    private Label idConsulta;

    @FXML
    private Label precoConsulta;

    @FXML
    private Label servicoConsulta;


    @FXML
    private Label MotivoText;

    @FXML
    private TextField MotivoTextField;


    Consulta consultaAtual;

    @Override
    public void initialize(URL url, ResourceBundle rb){


        for(Consulta consulta:Repositorio.getRepositorio().getConsultas()){
            for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                if(consulta.getFuncionario().equals(DataSessao.funcionario.getNome()) && consulta.getEmpresa().equals(empresa.getNome()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                    escolherConsultas.getItems().addAll(consulta.getIdConsulta());
                }
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
                    dataConsulta.setText(String.valueOf(consultaAtual.getDataConsulta()));
                    especialidadeConsulta.setText(consultaAtual.getEspecialidade());
                    precoConsulta.setText(String.valueOf(consultaAtual.getPrecoTotal()));
                    funcionarioConsulta.setText(consultaAtual.getFuncionario());
                    servicoConsulta.setText(consultaAtual.getServico());
                    clienteConsulta.setText(String.valueOf(consultaAtual.getCliente().getNome()));
                    estadoConsulta.setText(String.valueOf(consultaAtual.getEstadoConsulta()));


                }
            });

            estadoChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EstadoConsulta>() {
                @Override
                public void changed(ObservableValue<? extends EstadoConsulta> observableValue, EstadoConsulta estadoConsulta, EstadoConsulta t1) {
                    if(estadoChoiceBox.getSelectionModel().getSelectedItem().equals(EstadoConsulta.CANCELADA)){
                        MotivoText.setVisible(true);
                        MotivoTextField.setVisible(true);
                    }
                }
            });
        }

        public void AlterarMarcacoes(ActionEvent event){
            estadoConsulta.setVisible(false);
            Alterar.setVisible(false);
            estadoChoiceBox.setVisible(true);
            Cancelar.setVisible(true);
            Guardar.setVisible(true);
            estadoChoiceBox.getItems().addAll(EstadoConsulta.CONFIRMADA,EstadoConsulta.CANCELADA);
        }

        public void GuardarAlteracoes(ActionEvent event){
            try {
                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()) {
                    if (Objects.equals(consulta.getFuncionario(), DataSessao.funcionario.getNome()) && consulta.getIdConsulta() == escolherConsultas.getSelectionModel().getSelectedItem() && consulta.getEstadoPagamento() == EstadoPagamento.NAOPAGA) {
                        if(estadoChoiceBox.getValue() == EstadoConsulta.CONFIRMADA){
                            consulta.setEstadoConsulta(EstadoConsulta.CONFIRMADA);
                        } if(estadoChoiceBox.getValue() == EstadoConsulta.CANCELADA){
                            consulta.setEstadoConsulta(EstadoConsulta.CANCELADA);
                            consulta.setMotivoCancelada(MotivoTextField.getText());
                        }
                    }
                }

                System.out.println("Os dados foram alterados com sucesso!");
                Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
                voltarAtras(event);

            }catch (Exception e){
                System.out.println("Erro ao guardar alteracoes na consulta!" + e.getMessage());
            }
        }

        public void voltarAtras(ActionEvent event){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("menuFuncionario.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu funcionario");
                stage.show();
            }catch (IOException ex){
                System.out.println("Erro ao acessar menu funcionario: " + ex.getMessage());
            }
        }

        }


