/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import negocio.DadosCache;

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
    private RadioButton rdbTemperaturaAmbiente;

    @FXML
    private BorderPane border;
    @FXML
    private Label lblMedia;

    @FXML
    private Label lblMaximo;

    @FXML
    private Label lblMinimo;

    @FXML
    private Label lblVarianca;

    @FXML
    private Label lblDesvioPadrao;

    @FXML
    void rdbTemperaturaSoloOnAction(ActionEvent event) {
        rdbUmidadeAmbiente.setSelected(false);
        if (!rdbTemperaturaAmbiente.isSelected() && !rdbUmidadeAmbiente.isSelected()) {
            rdbTemperaturaSolo.setSelected(true);
        }
        gerarGrafico();

    }

    @FXML
    void rdbTemperaturaAmbiente_OnAction(ActionEvent event) {
        rdbUmidadeAmbiente.setSelected(false);
        if (!rdbTemperaturaSolo.isSelected() && !rdbUmidadeAmbiente.isSelected()) {
            rdbTemperaturaAmbiente.setSelected(true);
        }
        gerarGrafico();

    }

    @FXML
    void rdbUmidadeAmbiente_OnAction(ActionEvent event) {
        rdbTemperaturaAmbiente.setSelected(false);
        rdbTemperaturaSolo.setSelected(false);
        if (!rdbTemperaturaAmbiente.isSelected() && !rdbTemperaturaSolo.isSelected()) {
            rdbUmidadeAmbiente.setSelected(true);
        }
        gerarGrafico();
    }

    @FXML
    void btnVoltar_OnAction(ActionEvent event) {

    }

    void gerarEstastistica() {

        if (rdbUmidadeAmbiente.isSelected()) {
            float media = 0;
            float dPadrao = 0;
            float varianca = 0;
            float max = memoria.get(0).getUmidade_dht();
            float min = memoria.get(0).getUmidade_dht();

            for (int i = 0; i < memoria.size(); i++) {
                if (memoria.get(i).getUmidade_dht() > max) {
                    max = memoria.get(i).getUmidade_dht();
                }
                if (memoria.get(i).getUmidade_dht() < min) {
                    min = memoria.get(i).getUmidade_dht();
                }
                media = memoria.get(i).getUmidade_dht() + media;
                varianca = memoria.get(i).getUmidade_dht() * memoria.get(i).getUmidade_dht() + dPadrao;

            }
            if (memoria.size() <= 1) {
                varianca = 0;
                 
            } else {
                varianca = varianca / memoria.size() - 1;
                 media=media/memoria.size();
               
            }
           
            //Math.sqrt(x);
            dPadrao = (float) Math.sqrt(varianca);
            

            lblDesvioPadrao.setText("Desvio Padrão: " + dPadrao);
            lblMaximo.setText("Máximo: " + max+"%");
            lblMedia.setText("Média:" + media+"%");
            lblMinimo.setText("Minímo: " + min+"%");
            lblVarianca.setText("Variança: " + varianca);

        }
    }

    @FXML
    void btnVoltar_OnKeyPressed(ActionEvent event) {

    }

    public static List<DadosCache> getMemoria() {
        return memoria;
    }

    public static void setMemoria(List<DadosCache> memoria) {
        GraficoController.memoria = memoria;
    }

    public static List<DadosCache> memoria;

    String ajusteData(Date dt) {

        String data = "";

        SimpleDateFormat in = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        data = in.format(dt);
        return data;
    }

    void gerarGrafico() {
        gerarEstastistica();
        if (rdbTemperaturaAmbiente.isSelected() && rdbTemperaturaSolo.isSelected()) {
            final CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Periodo");

            final NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Variação de Temperatura");

            final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

            XYChart.Series dataSeries1 = new XYChart.Series();
            XYChart.Series dataSeries2 = new XYChart.Series();

            dataSeries1.setName("Temperatura DHT");
            dataSeries2.setName("Temperatura DS");

            dataSeries1.setNode(yAxis);
            dataSeries2.setNode(yAxis);

            for (int i = 0; i < memoria.size(); i++) {
                dataSeries1.getData().add(new XYChart.Data<String, Number>(ajusteData(memoria.get(i).getData()), memoria.get(i).getTemp_dth()));
                dataSeries2.getData().add(new XYChart.Data<String, Number>(ajusteData(memoria.get(i).getData()), memoria.get(i).getTemp_ds()));
            }
            lineChart.getData().add(dataSeries1);
            lineChart.getData().add(dataSeries2);

            lineChart.setAnimated(true);
            lineChart.createSymbolsProperty().set(false);

            border.setCenter(lineChart);
        }
        if (rdbTemperaturaAmbiente.isSelected() && !rdbTemperaturaSolo.isSelected()) {
            final CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Periodo");

            final NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Variação de Temperatura");

            final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

            XYChart.Series dataSeries1 = new XYChart.Series();
            //  XYChart.Series dataSeries2 = new XYChart.Series();

            dataSeries1.setName("Temperatura DHT");
            // dataSeries2.setName("Temperatura DS");

            dataSeries1.setNode(yAxis);
            // dataSeries2.setNode(yAxis);

            for (int i = 0; i < memoria.size(); i++) {
                dataSeries1.getData().add(new XYChart.Data<String, Number>(ajusteData(memoria.get(i).getData()), memoria.get(i).getTemp_dth()));
                //    dataSeries2.getData().add(new XYChart.Data<String, Number>(ajusteData(memoria.get(i).getData()), memoria.get(i).getTemp_ds()));
            }
            lineChart.getData().add(dataSeries1);
            // lineChart.getData().add(dataSeries2);

            lineChart.setAnimated(true);
            lineChart.createSymbolsProperty().set(false);

            border.setCenter(lineChart);

        }
        if (!rdbTemperaturaAmbiente.isSelected() && rdbTemperaturaSolo.isSelected()) {
            final CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Periodo");

            final NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Variação de Temperatura");

            final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

            // XYChart.Series dataSeries1 = new XYChart.Series();
            XYChart.Series dataSeries2 = new XYChart.Series();

            // dataSeries1.setName("Temperatura DHT");
            dataSeries2.setName("Temperatura DS");

            // dataSeries1.setNode(yAxis);
            dataSeries2.setNode(yAxis);

            for (int i = 0; i < memoria.size(); i++) {
                //  dataSeries1.getData().add(new XYChart.Data<String, Number>(ajusteData(memoria.get(i).getData()), memoria.get(i).getTemp_dth()));
                dataSeries2.getData().add(new XYChart.Data<String, Number>(ajusteData(memoria.get(i).getData()), memoria.get(i).getTemp_ds()));
            }
            //   lineChart.getData().add(dataSeries1);
            lineChart.getData().add(dataSeries2);

            lineChart.setAnimated(true);
            lineChart.createSymbolsProperty().set(false);

            border.setCenter(lineChart);
        }
        if (rdbUmidadeAmbiente.isSelected()) {
            final CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Periodo");

            final NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Variação de Umidade");

            final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

            XYChart.Series dataSeries1 = new XYChart.Series();
            // XYChart.Series dataSeries2 = new XYChart.Series();

            dataSeries1.setName("Umidade DHT");
            //  dataSeries2.setName("Temperatura DS");

            dataSeries1.setNode(yAxis);
            // dataSeries2.setNode(yAxis);

            for (int i = 0; i < memoria.size(); i++) {
                dataSeries1.getData().add(new XYChart.Data<String, Number>(ajusteData(memoria.get(i).getData()), memoria.get(i).getUmidade_dht()));
                //  dataSeries2.getData().add(new XYChart.Data<String, Number>(ajusteData(memoria.get(i).getData()), memoria.get(i).getTemp_ds()));
            }
            lineChart.getData().add(dataSeries1);
            //lineChart.getData().add(dataSeries2);

            lineChart.setAnimated(true);
            lineChart.createSymbolsProperty().set(false);

            border.setCenter(lineChart);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                rdbTemperaturaAmbiente.setSelected(true);
                rdbTemperaturaSolo.setSelected(true);
                gerarGrafico();

            }
        });

//        dataSeries1.getData().add(new XYChart.Data<String, Number>("jan", 12));
//
//        dataSeries1.getData().add(new XYChart.Data<String, Number>("fev", 32));
//        dataSeries1.getData().add(new XYChart.Data<>(10, data));
//        data.setMonth(5);
//        dataSeries1.getData().add(new XYChart.Data<>(20, data));
//        data.setMonth(6);
//        dataSeries1.getData().add(new XYChart.Data<>(40, data));
//        data.setMonth(7);
//        dataSeries1.getData().add(new XYChart.Data<>(80, data));
//        data.setMonth(8);
    }

}
