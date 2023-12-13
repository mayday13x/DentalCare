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
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
       launch();

      for( Utilizador cliente: Repositorio.getRepositorio().getClientes().values()){

          System.out.printf("%s , %s, %s, %s, %s, %s, %s\n", cliente.getCC(), cliente.getNome(), cliente.getMorada(),
                  cliente.getUtilizador(), cliente.getPassword(), cliente.getTelefone(), cliente.getLocalidade());

      }
      for( Utilizador funcionario: Repositorio.getRepositorio().getFuncionarios().values()){

            System.out.printf("%s , %s, %s, %s, %s, %s, %s\n", funcionario.getCC(), funcionario.getNome(),
                    funcionario.getMorada(), funcionario.getUtilizador(), funcionario.getPassword(),
                    funcionario.getTelefone(), funcionario.getLocalidade());

      }

    }

}