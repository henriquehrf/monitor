/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import monitor.Alertas;
import monitor.LerMessage;
import monitor.Monitor;
import negocio.Conversao;

/**
 * FXML Controller class
 *
 * @author Henrique Firmino
 */
public class PrincipalController {

    @FXML
    private Button btnVisualizarDados;

    @FXML
    private Button btnImportarDados;

    @FXML
    private Button btnExportarDados;

    @FXML
    private Button btnEstatistica;

    @FXML
    void btnImportarDados_OnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Localizar Arquivo");
         fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivo de Texto", "*.txt"));
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        
        Conversao conv = new Conversao();
        
        
        Alertas aviso = new Alertas();
        LerMessage message = new LerMessage();
        if(aviso.alerta(Alert.AlertType.CONFIRMATION, message.getMessage("msgAguardandoConfirmacao"),message.getMessage("msgConfirmacaoDeImportacao"), message.getMessage("msgSim"), message.getMessage("msgNao"))){
            
        }else{
            aviso.alerta(Alert.AlertType.INFORMATION, message.getMessage("msgAcaoRealizadaComSucesso"), message.getMessage("msgGeracaoGrafico"));
            conv.getFile(file);
            
              try {
            Parent root;
            root = FXMLLoader.load(GraficoController.class.getClassLoader().getResource("fxml/grafico.fxml"), ResourceBundle.getBundle("monitor/i18N_pt_BR"));
            Monitor.SCENE.setRoot(root);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        }
        
    }

    @FXML
    void btnImportarDados_OnKeyPressed(ActionEvent event) {

    }

    @FXML
    void btnExportarDados_OnAction(ActionEvent event) {

    }

    @FXML
    void btnExportarDados_OnKeyPressed(ActionEvent event) {

    }

    @FXML
    void btnVisualizarDados_OnAction(ActionEvent event) {

    }

    @FXML
    void btnVisualizarDados_OnKeyPressed(ActionEvent event) {

    }

    @FXML
    void btnEstatistica_OnAction(ActionEvent event) {

    }

    @FXML
    void btnEstatistica_OnKeyPressed(ActionEvent event) {

    }

    public void initialize() {
        System.out.println("Aqui estou");
    }

}
