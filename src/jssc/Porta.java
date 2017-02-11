/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jssc;

import controller.GraficoController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jssc.SerialPort.MASK_RXCHAR;
import monitor.Alertas;
import negocio.Conversao;
import negocio.DadosCache;

/**
 *
 * @author Henrique Firmino
 */
public class Porta {

    SerialPort arduinoPort = null;
    ObservableList<String> portList;

    // Label labelValue;
    public void detectPort() {

        portList = FXCollections.observableArrayList();

        String[] serialPortNames = SerialPortList.getPortNames();
        for (String name : serialPortNames) {
            System.out.println(name);
            portList.add(name);
        }
    }
    public List<String> getPort(){
        detectPort();
        return portList;
    }

    public boolean connectArduino(String port) {

        System.out.println("connectArduino");

        boolean success = false;
        SerialPort serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setEventsMask(MASK_RXCHAR);

            serialPort.addEventListener((SerialPortEvent serialPortEvent) -> {
                if (serialPortEvent.isRXCHAR()) {
                    try {
                        String st = serialPort.readString(serialPortEvent
                                .getEventValue());

                        // System.out.println("O valor ->" + st.trim());
                        exibir(st);

                        //Update label in ui thread
//                        Platform.runLater(() -> {
//                           // labelValue.setText(""+st);
//                        });
                    } catch (SerialPortException ex) {
                        System.out.println("Erro interno");
                    }

                }
            });

            arduinoPort = serialPort;
            success = true;
        } catch (SerialPortException ex) {
            Logger.getLogger(Porta.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("SerialPortException: " + ex.toString());
        }

        return success;
    }

    public void disconnectArduino() {

        System.out.println("disconnectArduino()");
        if (arduinoPort != null) {
            try {
                arduinoPort.removeEventListener();

                if (arduinoPort.isOpened()) {
                    arduinoPort.closePort();
                }

            } catch (SerialPortException ex) {
                Logger.getLogger(Porta.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

//    @Override
//    public void stop() throws Exception {
//        disconnectArduino();
//        super.stop();
//    }
    public String aux = "";

    public static List<DadosCache> dado = new ArrayList<>();

    Alertas alert = new Alertas();
    public void exibir(String srt) {

        if (srt != null) {

            Conversao conv = new Conversao();
            DadosCache cache = new DadosCache();

            aux += srt;
            if (aux.contains("OK")) {
                aux = aux.replace("OK.", "");
                aux = aux.replace("Received:", "");
                aux = aux.replace("Device is ready", "");
                try {
               
                    cache = conv.getString(aux);
                    dado.add(cache);
                   GraficoController.setMemoria(dado);
                   
                   
                    //GraficoController.setMemoria(dado);
                   // gerarGrafico();
                } catch (Exception ex) {
                    alert.erro("Erro na leitura e/ou processamento dos dados", "Foi detectado um erro na porta serial", "Houve um problema no processamento dos dados obtidos da porta serial, eles podem não estar no formato suportado ou podem estar corrompidos", ex);
                    disconnectArduino();
                }

                aux = "";
            }
        }
    }

}
