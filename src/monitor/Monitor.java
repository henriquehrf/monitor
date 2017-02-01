/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import controller.PrincipalController;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Henrique Firmino
 */
public class Monitor extends Application {
    
   public static Scene SCENE;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane pane = null;

        try {

            pane = FXMLLoader.load(PrincipalController.class.getClassLoader().getResource("fxml/Principal.fxml"), ResourceBundle.getBundle("monitor/i18N_pt_BR"));

            Scene scene = new Scene(pane, stage.getWidth(), stage.getHeight());

            stage.setResizable(false);
            SCENE = scene;
            stage.setTitle("Monitor - Sistema de medição de temperatura do solo de baixo-custo");

            stage.setScene(scene);

            stage.show();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }
    
     public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("MonitorPU");
         EntityManager em = emf.createEntityManager();
          Application.launch(Monitor.class, args);
     }
    
}
