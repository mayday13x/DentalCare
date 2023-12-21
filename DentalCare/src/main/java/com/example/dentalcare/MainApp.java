package com.example.dentalcare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Test");
            stage.setScene(scene);
            stage.show();
        }catch (IOException ex){
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    public static void main(String[] args) {
       launch();

      for( Utilizador cliente: Repositorio.getRepositorio().getClientes()){

          System.out.printf("%s , %s, %s, %s, %s, %s, %s\n", cliente.getCC(), cliente.getNome(), cliente.getMorada(),
                  cliente.getUtilizador(), cliente.getPassword(), cliente.getTelefone(), cliente.getLocalidade());

      }
      for( Utilizador funcionario: Repositorio.getRepositorio().getFuncionarios()){

            System.out.printf("%s , %s, %s, %s, %s, %s, %s\n", funcionario.getCC(), funcionario.getNome(),
                    funcionario.getMorada(), funcionario.getUtilizador(), funcionario.getPassword(),
                    funcionario.getTelefone(), funcionario.getLocalidade());

      }

      for(Empresa empresa: Repositorio.getRepositorio().getEmpresas().values()){
          System.out.printf("%s , %s, %s, %s, %s, %s\n", empresa.getNome(), empresa.getMorada(), empresa.getLocalidade(),
                  empresa.getNumTelefone(), empresa.getConsultorios(),empresa.getDono().getNome());
      }

      for(Dono dono: Repositorio.getRepositorio().getEmpresas().keySet()){
          System.out.println("Dono " + dono.getNome());
      }

    }

}