package com.example.dentalcare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.ReadOnlyBufferException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
       //launch();
       Dono user = new Dono("Miguel",123,999999999,"Rua de Cima","Viana do Castelo","miguel","1234");
       Empresa company = new Empresa("Dental LDA.", "Rua de Baixo","VC",99,user);
       Funcionario employee = new Funcionario("Joao",223342,23423423,"Rua Atlantico","VC",ProfissaoFuncionario.ENFERMEIRO,
               "joao","12345");
       Consultorio consultorio = new Consultorio("Rua de Lado","PVZ",923294234, TipoConsulta.DESTARTARIZACAO);
       Funcionario employee2 = new Funcionario("Maria",2,2222,"Apartamento 22","PVZ",ProfissaoFuncionario.DENTISTA,
               223344, "maria","1234");

       consultorio.addFuncionario(employee);
       consultorio.addFuncionario(employee2);
       company.addConsultorio(consultorio);


       for(Consultorio x : company.getConsultorios()){

           System.out.printf("%s - %s - %s- %s\n", company.getNome(), x.getMorada(), x.getTpConsulta(), company.getDono().getNome());

           for(Funcionario y : x.getFuncionarios()){

               System.out.printf("%s - %s - %s- %d\n",y.getNome(),y.getProfissao(),y.getUtilizador(),y.getNumCarteiraProfissional());

           }
       }

    }

}