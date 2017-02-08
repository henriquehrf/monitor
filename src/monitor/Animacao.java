/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Henrique Firmino
 */
public class Animacao {

    private static WorkIndicatorDialog wd = null;

    public static void ProgressIndicator(boolean stop,String msg) {
        Alert dialog = new Alert(Alert.AlertType.NONE);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        wd = new WorkIndicatorDialog(stage.getScene().getWindow(), msg);

        wd.addTaskEndNotification(result -> {
            System.out.println(result);
            wd = null; // don't keep the object, cleanup
        });

        wd.exec("123", inputParam -> {
            // Thinks to do...
            // NO ACCESS TO UI ELEMENTS!
            if (stop) {

               // System.out.println("Loading data... '123' =->" + inputParam);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return new Integer(1);
        });
    }
     public static void ProgressIndicator(long stop,String msg) {
        Alert dialog = new Alert(Alert.AlertType.NONE);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        wd = new WorkIndicatorDialog(stage.getScene().getWindow(), msg);

        wd.addTaskEndNotification(result -> {
            System.out.println(result);
            wd = null; // don't keep the object, cleanup
        });

        wd.exec("123", inputParam -> {
            // Thinks to do...
            // NO ACCESS TO UI ELEMENTS!
          

               // System.out.println("Loading data... '123' =->" + inputParam);
                try {
                    Thread.sleep(stop);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            
            return new Integer(1);
        });
    }

    //ProgressBar progress = new ProgressBar(.1);
}
