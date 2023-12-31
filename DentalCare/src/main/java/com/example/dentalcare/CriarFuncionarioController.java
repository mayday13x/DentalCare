package com.example.dentalcare;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.ResourceBundle;

public class CriarFuncionarioController implements Initializable {

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
    private TextField numCarteiraProfisional;

    @FXML
    private ChoiceBox<String> escolherEmpresa;

    @FXML
    private ChoiceBox<String> escolherConsultorio;

    @FXML
    private ChoiceBox<ProfissaoFuncionario> escolherProfissao;


    @FXML
    private TextField utilizadorId;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        Repositorio repo = Repositorio.getRepositorio();
        ProfissaoFuncionario[] profissaos = ProfissaoFuncionario.values();

        for(ProfissaoFuncionario profissaoFuncionario : profissaos){
            escolherProfissao.getItems().addAll(profissaoFuncionario);
        }

        for(Empresa empresa: repo.getEmpresas().values()){
            if(Objects.equals(empresa.getDono().getNome(), DataSessao.dono.getNome()) && empresa.getEstado().equals(EstadoDonoEmpresa.ATIVADA)){
                escolherEmpresa.getItems().addAll(empresa.getNome());
                List<Consultorio> consultorioList = empresa.getConsultorios();
                if(consultorioList != null){
                    for(Consultorio consultorio: consultorioList){
                        escolherConsultorio.getItems().addAll(consultorio.getNome());
                    }
                }
        }
        }

    }

    public void registarFuncionario(ActionEvent event){
        Funcionario fn = new Funcionario();

        fn.setUtilizador(utilizadorId.getText());
        fn.setPassword(password.getText());
        fn.setNome(nome.getText());
        fn.setCC(Integer.parseInt(cc.getText()));
        fn.setNIF(Integer.parseInt(nif.getText()));
        fn.setTelefone(Integer.parseInt(telefone.getText()));
        fn.setMorada(morada.getText());
        fn.setLocalidade(localidade.getText());
        fn.setProfissao(escolherProfissao.getValue());
        fn.setNumCarteiraProfissional(numCarteiraProfisional.getText());

        String emp = escolherEmpresa.getValue();
        String consultorio = escolherConsultorio.getValue();

        for(Empresa empresa : Repositorio.getRepositorio().getConsultorios().keySet()){
            List<Consultorio> consultorioList = empresa.getConsultorios();
            for(Consultorio con: consultorioList){
                if(con.getNome().equals(consultorio) && empresa.getNome().equals(emp)){
                    FuncionarioBLL.adicionarFuncionario(fn, empresa,con);
                }
            }
        }


        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Donos");
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro ao registar o funcionario: " + ex.getMessage());
        }
    }


}
