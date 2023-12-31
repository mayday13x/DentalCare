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
import java.util.*;

public class MarcarConsultaController implements Initializable {

    @FXML
    private ChoiceBox<String> escolherConsultorio;

    @FXML
    private ChoiceBox<String> escolherEmpresa;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Repositorio repo = Repositorio.getRepositorio();
        especialidadeConsultorio.setEditable(false);
        precoTotal.setEditable(false);

        Servico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Servico servico: Repositorio.getRepositorio().getServicos()){
                    if(servico.getNomeServico().equals(Servico.getValue())){
                        precoTotal.setText(String.valueOf(servico.getPrecoServico()));
                    }
                }
            }
        });


        for (Empresa empresa : repo.getEmpresas().values()) {
            if(empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                escolherEmpresa.getItems().addAll(empresa.getNome()); // adiciona as empresas a choicebox
            }

            List<Consultorio> consultorioList = empresa.getConsultorios();

            if (consultorioList != null) {
                for (Consultorio consultorio : consultorioList) {
                    escolherConsultorio.getItems().addAll(consultorio.getNome()); // adicionar os consultorios a choicebox
                }
            }

            escolherConsultorio.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {  // atualiza a lista de funcionarios de acordo com o consultorio
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    escolherFuncionario.getItems().clear();
                    if(consultorioList != null){
                        for (Consultorio consultorio : consultorioList) {
                            if(consultorio.getNome().equals(escolherConsultorio.getValue())){
                                List<Funcionario> funcionarioList = consultorio.getFuncionarios();
                                for (Funcionario funcionario : funcionarioList) {
                                    escolherFuncionario.getItems().addAll(funcionario.getNome());// adicionar os funcionarioa a choicebox
                                }

                                especialidadeConsultorio.setText(consultorio.getEspecialidade());
                            }
                    }
                    }
                }
            });

        for (Servico servico : Repositorio.getRepositorio().getServicos()) {
            if (servico.getEmpresa().equals(empresa.getNome())) {
                Servico.getItems().addAll(servico.getNomeServico()); // adiciona os servicos a choicebox
            }
        }
    }

}

    public void MarcarConsulta(ActionEvent event){
        try{
            Consulta consulta = new Consulta();

            consulta.setIdConsulta(Repositorio.getRepositorio().getConsultas().size() +1);
            consulta.setDataConsulta(escolherData.getValue());
            consulta.setCliente(DataSessao.cliente);
            consulta.setFuncionario(escolherFuncionario.getValue());
            consulta.setEstadoConsulta(EstadoConsulta.NAOPAGA);
            consulta.setConsultorio(escolherConsultorio.getValue());
            consulta.setEmpresa(escolherEmpresa.getValue());

            for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
                if(empresa.getNome().equals(escolherEmpresa.getValue())){
                    List<Consultorio> consultorioList = empresa.getConsultorios();
                    for(Consultorio consultorio: consultorioList){
                        if(consultorio.getNome().equals(escolherConsultorio.getValue())){
                            consulta.setEspecialidade(consultorio.getEspecialidade());
                        }
                    }
                }
            }

            consulta.setEspecialidade(especialidadeConsultorio.getText());
            consulta.setServico(Servico.getValue());

            String servico = consulta.getServico();
            for(Servico serv: Repositorio.getRepositorio().getServicos()){
                if(serv.getNomeServico().equals(servico)){
                    consulta.setPrecoTotal(serv.getPrecoServico());
                }
            }

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




}
