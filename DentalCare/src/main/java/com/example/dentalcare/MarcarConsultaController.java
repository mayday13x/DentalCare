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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class MarcarConsultaController implements Initializable {

    @FXML
    private ChoiceBox<String> escolherConsultorio;

    @FXML
    private ChoiceBox<String> escolherFuncionario;

    @FXML
    private TextField especialidadeConsultorio;

    @FXML
    private TextField precoTotal;

    @FXML
    private ChoiceBox<String> Servico;

    @FXML
    private DatePicker escolherData;

    @FXML
    private ChoiceBox<String> horarioConsulta;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Repositorio repo = Repositorio.getRepositorio();
        especialidadeConsultorio.setEditable(false);
        precoTotal.setEditable(false);



        escolherFuncionario.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                horarioConsulta.getItems().clear();
                horarioConsulta.getItems().addAll("9:00","10:00","11:00","12:00","14:00","15:00","16:00","17:00");
                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                    if(consulta.getDataConsulta().equals(escolherData.getValue()) && consulta.getFuncionario().equals(escolherFuncionario.getValue())){
                        String itemaRemover = consulta.getHoraConsulta();
                        horarioConsulta.getItems().remove(itemaRemover);
                    }
                }
            }
        });

        escolherData.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                horarioConsulta.getItems().clear();
                horarioConsulta.getItems().addAll("9:00","10:00","11:00","12:00","14:00","15:00","16:00","17:00");
                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                    if(consulta.getDataConsulta().equals(escolherData.getValue()) && consulta.getFuncionario().equals(escolherFuncionario.getValue())){
                        String itemaRemover = consulta.getHoraConsulta();
                        horarioConsulta.getItems().remove(itemaRemover);
                    }
                }
            }
        });

        Servico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Servico servico: Repositorio.getRepositorio().getServicos()){
                    if(servico.getNomeServico().equals(Servico.getValue())){
                        float preco = Integer.parseInt(precoTotal.getText());
                        preco = preco + servico.getPrecoServico();
                        precoTotal.setText(String.valueOf(preco));
                    }
                }
            }
        });

            for(List<Consultorio> consultorios:Repositorio.getRepositorio().getConsultorios().values()) {
                List<Consultorio> consultorioList = consultorios;
                if (consultorioList != null) {
                    for (Consultorio consultorio : consultorioList) {
                        escolherConsultorio.getItems().addAll(consultorio.getNome()); // adicionar os consultorios a choicebox
                    }
                }
            }


            escolherConsultorio.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {  // atualiza a lista de funcionarios de acordo com o consultorio
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    escolherFuncionario.getItems().clear();

                    for(List<Consultorio> consultorios:Repositorio.getRepositorio().getConsultorios().values()) {
                        List<Consultorio> consultorioList = consultorios;
                        if (consultorioList != null) {
                            for (Consultorio consultorio : consultorioList) {
                                if (consultorio.getNome().equals(escolherConsultorio.getValue())) {
                                    List<Funcionario> funcionarioList = consultorio.getFuncionarios();
                                    for (Funcionario funcionario : funcionarioList) {
                                        escolherFuncionario.getItems().addAll(funcionario.getNome());// adicionar os funcionarioa a choicebox
                                    }

                                    especialidadeConsultorio.setText(consultorio.getEspecialidade());
                                }
                            }
                        }
                    }

                    for(String tipo:Repositorio.getRepositorio().getPrecoConsultas().keySet()){
                        if(Objects.equals(tipo,especialidadeConsultorio.getText())){
                            System.out.println(tipo);
                            int preco = Repositorio.getRepositorio().getPrecoConsultas().get(tipo);
                            precoTotal.setText(String.valueOf(preco));
                        }
                    }



                    for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                        List<Consultorio> consultoriosList = empresa.getConsultorios();

                        for(Consultorio consultorio: consultoriosList){
                            if(consultorio.getNome().equals(escolherConsultorio.getSelectionModel().getSelectedItem())){
                                for (Servico servico : Repositorio.getRepositorio().getServicos()) {
                                    if (servico.getEmpresa().equals(empresa.getNome()) ) {
                                        Servico.getItems().addAll(servico.getNomeServico());// adiciona os servicos a choicebox

                                    }
                                }
                            }
                        }
                    }
                }
            });
}

    public void MarcarConsulta(ActionEvent event){
        try{
            Consulta consulta = new Consulta();

            consulta.setIdConsulta(Repositorio.getRepositorio().getConsultas().size() +1);
            consulta.setDataConsulta(escolherData.getValue());
            consulta.setCliente(DataSessao.cliente);
            consulta.setFuncionario(escolherFuncionario.getValue());
            consulta.setEstadoConsulta(EstadoConsulta.PORCOMFIRMAR);
            consulta.setEstadoPagamento(EstadoPagamento.NAOPAGA);
            consulta.setConsultorio(escolherConsultorio.getValue());
            consulta.setHoraConsulta(horarioConsulta.getValue());
            consulta.setPrecoTotal(Float.parseFloat(precoTotal.getText()));

            for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                    List<Consultorio> consultorioList = empresa.getConsultorios();
                    for(Consultorio consultorio: consultorioList){
                        if(consultorio.getNome().equals(escolherConsultorio.getValue())){
                            consulta.setEspecialidade(consultorio.getEspecialidade());
                            consulta.setEmpresa(empresa.getNome());
                        }
                    }
                }


            consulta.setEspecialidade(especialidadeConsultorio.getText());
            consulta.setServico(Servico.getValue());

            MarcarConsultaBLL.adicionarConsulta(consulta);

        }catch (Exception e){
            System.out.println("Erro ao adicionar servico: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuCliente.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Cliente");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao carregar o menu de cliente: " + ex.getMessage());
        }

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
            System.out.println("Erro ao acessar menu cliente: " + ex.getMessage());
        }


    }



}
