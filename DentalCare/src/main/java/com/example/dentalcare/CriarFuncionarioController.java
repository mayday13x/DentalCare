package com.example.dentalcare;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

    @FXML
    private Label nifError;

    @FXML
    private Label telefoneError;

    @FXML
    private Label ccError;

    @FXML
    private Label utilizadorError;

    @FXML
    private Label numCpText;

    boolean ccEstado = false;

    boolean nifEstado = false;

    boolean telefoneEstado = false;

    boolean utilizadorEstado = false;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ccError.setVisible(false);
        nifError.setVisible(false);
        telefoneError.setVisible(false);
        numCarteiraProfisional.setVisible(false);
        numCpText.setVisible(false);
        Repositorio repo = Repositorio.getRepositorio();
        ProfissaoFuncionario[] profissaos = ProfissaoFuncionario.values();

        escolherProfissao.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProfissaoFuncionario>() {
            @Override
            public void changed(ObservableValue<? extends ProfissaoFuncionario> observableValue, ProfissaoFuncionario profissaoFuncionario, ProfissaoFuncionario t1) {
                if(escolherProfissao.getSelectionModel().getSelectedItem().equals(ProfissaoFuncionario.DENTISTA)){
                    numCarteiraProfisional.setVisible(true);
                    numCpText.setVisible(true);
                }else {
                    numCarteiraProfisional.setVisible(false);
                    numCpText.setVisible(false);
                }
            }
        });


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

        if(telefoneEstado && ccEstado && nifEstado && utilizadorEstado){
            try {
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
                if(escolherProfissao.getSelectionModel().getSelectedItem().equals(ProfissaoFuncionario.DENTISTA)){
                    fn.setNumCarteiraProfissional(numCarteiraProfisional.getText());
                }else {
                    fn.setNumCarteiraProfissional("Não definida");
                }


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
            }catch (Exception e){
                System.out.println("Erro ao registar funcionario: " + e.getMessage());
            }

            try {
                Parent root = FXMLLoader.load(getClass().getResource("menuDono.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu Donos");
                stage.show();
            }catch (IOException ex){
                System.out.println("Erro ao carregar o menu dono: " + ex.getMessage());
            }
        }

    }

    @FXML
    void verificarNif(KeyEvent event){
        String text = nif.getText();
        if(text.matches("[0-9]*")){
            nifError.setVisible(false);
            nifEstado = true;

            if(text.length() != 9){
                nifError.setVisible(true);
                nifEstado = false;
            }
        }else {
            nifError.setVisible(true);
            nifEstado = false;
        }
    }

    @FXML
    void verificarCC(KeyEvent event){
        String text = cc.getText();

        if(text.matches("[0-9]*")){
            ccError.setVisible(false);
            ccEstado = true;

            if(text.length() != 8){
                ccError.setVisible(true);
                ccEstado = false;
            }
        }
        else {
            ccError.setVisible(true);
            ccEstado = false;
        }
    }

    @FXML
    void verificarTelefone(KeyEvent event){
        String text = telefone.getText();

        if(text.matches("[0-9]*")){
            telefoneError.setVisible(false);
            telefoneEstado = true;

            if(text.length() != 9){
                telefoneError.setVisible(true);
                telefoneEstado = false;
            }
        }else {
            telefoneError.setVisible(true);
            telefoneEstado = false;
        }
    }

    @FXML
    void verificarUtilizador(KeyEvent event){
        String text = utilizadorId.getText();
        utilizadorError.setVisible(false);
        utilizadorEstado = true;

        for (Cliente cliente: Repositorio.getRepositorio().getClientes()){
            if(cliente.getUtilizador().equals(text)){
                utilizadorError.setVisible(true);
                utilizadorEstado = false;
            }
        }

        for (Dono dono: Repositorio.getRepositorio().getDonos()){
            if(dono.getUtilizador().equals(text)){
                utilizadorError.setVisible(true);
                utilizadorEstado = false;
            }
        }

        for(Admin admin: Repositorio.getRepositorio().getAdmins()){
            if(admin.getUtilizador().equals(text)){
                utilizadorError.setVisible(true);
                utilizadorEstado = false;
            }
        }

        for(Funcionario funcionario: Repositorio.getRepositorio().getFuncionarios()){
            if(funcionario.getUtilizador().equals(text)){
                utilizadorError.setVisible(true);
                utilizadorEstado = false;
            }
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
