/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Henrique Firmino
 */
public class GraficoController implements Initializable {

    @FXML
    private RadioButton rdbTemperaturaSolo;

    @FXML
    private Button btnVoltar;

    @FXML
    private RadioButton rdbUmidadeAmbiente;

    @FXML
    private LineChart graph;

    @FXML
    private CategoryAxis eixoX;

    @FXML
    private NumberAxis eixoY;

    @FXML
    private RadioButton rdbTemperaturaAmbiente;
    
    @FXML
    private BorderPane border;

    @FXML
    void btnVoltar_OnAction(ActionEvent event) {

    }

    @FXML
    void btnVoltar_OnKeyPressed(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        
//        NumberAxis X = new NumberAxis("teste2",100,200,1);
//        NumberAxis Y = new NumberAxis("teste3",0,50,5);
//        
//        ObservableList<XYChart.Series<Integer,Integer>> dados = FXCollections.observableArrayList(
//        new LineChart.Series<Integer,Integer>("Teste 1",FXCollections.observableArrayList(
//        new XYChart.Data<Integer, Integer>(100,65),
//            new XYChart.Data<Integer, Integer>(110,15))));
//     LineChart graph2 = new LineChart(X,Y,dados);
//     border.getChildren().add(graph2);
    }

}
