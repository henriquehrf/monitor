/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.util.Optional;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

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
        }else{
            return "";
        }

    }
}
