/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author Henrique
 */
public class Alertas {

    public void alerta(Alert.AlertType TipoAviso, String cabecalho, String msg) {
        try {

            Alert alert = new Alert(TipoAviso);
            alert.setTitle(cabecalho);
            alert.setHeaderText(null);
            alert.setContentText(msg);

            alert.showAndWait();

        } catch (Exception ex) {
            //  alerta(Alert.AlertType.ERROR, ex.getMessage(), "ERRO");
            System.out.println(ex.getMessage());
        }

    }

    public boolean alerta(Alert.AlertType TipoAviso, String cabecalho, String msg, String Opcao1, String Opcao2) {

        if (TipoAviso.equals(Alert.AlertType.CONFIRMATION)) {
            Alert alert = new Alert(TipoAviso);
            alert.setTitle(cabecalho);
            alert.setHeaderText(null);
            alert.setContentText(msg);

            ButtonType buttonTypeSIM = new ButtonType(Opcao1);
            ButtonType buttonTypeNAO = new ButtonType(Opcao2);

            alert.getButtonTypes().setAll(buttonTypeSIM, buttonTypeNAO);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeSIM) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    public String entrada_dados(String cabecalho, String msg, String content) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(cabecalho);
        dialog.setHeaderText(msg);
        dialog.setContentText(content);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return "";
        }

    }

    public void erro(String cabecalho, String titulo, String msg, Exception ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(cabecalho);
        alert.setHeaderText(titulo);
        alert.setContentText(msg);

       // Exception ex = new FileNotFoundException("Could not find file blabla.txt");

// Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Um erro foi encontrado e uma exceção foi lançada:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();

    }
}
