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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListarMarcacoesDonoController implements Initializable {


    @FXML
    private ListView<String> consultorioListView;

    @FXML
    private ListView<String> empresaListView;

    @FXML
    private ListView<Integer> marcacoesListView;

    @FXML
    private Label ConsultoriosText;

    @FXML
    private Label EmpresasText;

    @FXML
    private Label MarcacoesText;

    @FXML
    private Button Alterar;

    @FXML
    private Button Cancelar;

    @FXML
    private Button Confirmar;

    @FXML
    private Label DataText;

    @FXML
    private Label EspecialidadeText;

    @FXML
    private Label EstadoText;

    @FXML
    private ChoiceBox<EstadoConsulta> estadoChoiceBox;

    @FXML
    private TextField Funcionario;

    @FXML
    private Label FuncionarioText;

    @FXML
    private Label PrecoText;

    @FXML
    private TextField Servico;

    @FXML
    private Label ServicoText;

    @FXML
    private TextField cliente;

    @FXML
    private Label clienteText;

    @FXML
    private TextField dataMarcada;

    @FXML
    private TextField especialidadeConsultorio;

    @FXML
    private TextField precoTotal;

    @FXML
    private TextField horaConsulta;

    @FXML
    private Label HoraText;


    public void voltarAtras(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Dono");
            stage.show();
        }catch (Exception ex){
            System.out.println("Erro ao acessar menu dono: " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Funcionario.setEditable(false);
        Servico.setEditable(false);
        cliente.setEditable(false);
        dataMarcada.setEditable(false);
        especialidadeConsultorio.setEditable(false);
        precoTotal.setEditable(false);
        horaConsulta.setEditable(false);

        ChangeStatus(false);
        estadoChoiceBox.getItems().addAll(EstadoConsulta.values());

        for(Empresa empresa:Repositorio.getRepositorio().getEmpresas().values()){
            if(empresa.getDono().equals(DataSessao.dono) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                empresaListView.getItems().addAll(empresa.getNome());
            }

        }
        empresaListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                for(Empresa empresa:Repositorio.getRepositorio().getEmpresas().values()){
                    List<Consultorio> consultorioList = empresa.getConsultorios();

                    for(Consultorio consultorio: consultorioList){
                        consultorioListView.getItems().addAll(consultorio.getNome());
                    }
                }

                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                    if(consulta.getEmpresa().equals(empresaListView.getSelectionModel().getSelectedItem())){
                        marcacoesListView.getItems().addAll(consulta.getIdConsulta());
                    }
                }
            }
        });

        consultorioListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                marcacoesListView.getItems().clear();


                for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                    if(consulta.getConsultorio().equals(consultorioListView.getSelectionModel().getSelectedItem())){
                        marcacoesListView.getItems().addAll(consulta.getIdConsulta());

                    }
                }

            }
        });
    }

    public void alterarDadosMarcacao(ActionEvent event){
        empresaListView.setVisible(false);
        consultorioListView.setVisible(false);
        marcacoesListView.setVisible(false);
        EmpresasText.setVisible(false);
        ConsultoriosText.setVisible(false);
        MarcacoesText.setVisible(false);
        Alterar.setVisible(false);

        ChangeStatus(true);

        for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
            if(consulta.getIdConsulta() == marcacoesListView.getSelectionModel().getSelectedItem()){
                Funcionario.setText(consulta.getFuncionario());
                Servico.setText(consulta.getServico());
                cliente.setText(consulta.getCliente().getNome());
                dataMarcada.setText(String.valueOf(consulta.getDataConsulta()));
                especialidadeConsultorio.setText(consulta.getEspecialidade());
                precoTotal.setText(String.valueOf(consulta.getPrecoTotal()));
                estadoChoiceBox.setValue(consulta.getEstadoConsulta());
                horaConsulta.setText(consulta.getHoraConsulta());
            }
        }


    }

    public void cancelar(ActionEvent event){
        empresaListView.setVisible(true);
        consultorioListView.setVisible(true);
        marcacoesListView.setVisible(true);
        EmpresasText.setVisible(true);
        ConsultoriosText.setVisible(true);
        MarcacoesText.setVisible(true);
        Alterar.setVisible(true);


        ChangeStatus(false);
    }

    public void confirmar(ActionEvent event){
        try {
            for(Consulta consulta: Repositorio.getRepositorio().getConsultas()){
                if(consulta.getIdConsulta() == marcacoesListView.getSelectionModel().getSelectedItem()){
                    consulta.setEstadoConsulta(estadoChoiceBox.getValue());

                }
            }
            System.out.println("Os dados foram alterados com sucesso!");
            Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");

            ChangeStatus(false);
            empresaListView.setVisible(true);
            consultorioListView.setVisible(true);
            marcacoesListView.setVisible(true);
            EmpresasText.setVisible(true);
            ConsultoriosText.setVisible(true);
            MarcacoesText.setVisible(true);
            Alterar.setVisible(true);
        }catch (Exception e){
            System.out.println("Erro ao guardar alteracoes na consulta!" + e.getMessage());
        }


    }

    public void ChangeStatus(Boolean status){
        Cancelar.setVisible(status);
        Confirmar.setVisible(status);
        DataText.setVisible(status);
        EspecialidadeText.setVisible(status);
        EstadoText.setVisible(status);
        FuncionarioText.setVisible(status);
        PrecoText.setVisible(status);
        ServicoText.setVisible(status);
        clienteText.setVisible(status);
        HoraText.setVisible(status);

        Funcionario.setVisible(status);
        Servico.setVisible(status);
        estadoChoiceBox.setVisible(status);
        cliente.setVisible(status);
        dataMarcada.setVisible(status);
        especialidadeConsultorio.setVisible(status);
        precoTotal.setVisible(status);
        horaConsulta.setVisible(status);

    }

}
