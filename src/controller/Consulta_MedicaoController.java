/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MedicaoDAO;
import dao.RegiaoDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import monitor.Monitor;
import negocio.DadosCache;
import negocio.NegocioMedicao;
import negocio.NegocioRegiao;
import vo.Medicao;
import vo.Regiao;

/**
 *
 * @author Henrique Firmino
 */
public class Consulta_MedicaoController implements Initializable {

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Regiao> tblPrincipal;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnInserir;

    @FXML
    private Label Title;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtBuscador;

    @FXML
    private TableColumn<Regiao, String> tbcDescricao;

    @FXML
    void btnVisualizar_OnAction(ActionEvent event) {

        if (tblPrincipal.getItems() == null) {
            return;
        }
        List<Medicao> med = new ArrayList<>();
        try {
            med = medicao.BuscarDados(tblPrincipal.getSelectionModel().getSelectedItem());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        // GraficoController.setMemoria(med);
        List<DadosCache> dado = new ArrayList<>();
        for (int i = 0; i < med.size(); i++) {
            DadosCache cache = new DadosCache();
            cache.setData(med.get(i).getRtc());
            cache.setTemp_ds(med.get(i).getTemperatura_ds());
            cache.setTemp_dth(med.get(i).getTemperatura_dht());
            cache.setUmidade_dht(med.get(i).getUmidade_dht());
            dado.add(cache);
        }
        try {

            GraficoController.setMemoria(dado);
            Parent root;
            root = FXMLLoader.load(GraficoController.class.getClassLoader().getResource("fxml/grafico.fxml"), ResourceBundle.getBundle("monitor/i18N_pt_BR"));
            Monitor.SCENE.setRoot(root);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void btnImprimir_OnAction(ActionEvent event) {

    }

    @FXML
    void btnExcluir_OnAction(ActionEvent event) {

    }

    @FXML
    void btnVoltar_OnAction(ActionEvent event) {

    }

    @FXML
    void txtBuscadorOnKeyPressed(ActionEvent event) {

    }

    List<Regiao> lista;
    RegiaoDAO RDao = new RegiaoDAO();
    NegocioRegiao reg = new NegocioRegiao(RDao);
    MedicaoDAO MDao = new MedicaoDAO();
    NegocioMedicao medicao = new NegocioMedicao(MDao);

    void completar(List<Regiao> list) {

        ObservableList<Regiao> dado = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            dado.add(list.get(i));
        }
        this.tbcDescricao.setCellValueFactory(new PropertyValueFactory<>("regiao"));

        Comparator<Regiao> cmp = (Regiao reg1, Regiao reg2) -> reg1.getRegiao().compareTo(reg2.getRegiao());
        Collections.sort(dado, cmp);
        this.tblPrincipal.setItems(dado);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            lista = reg.BuscarRegiao();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        completar(lista);

    }

}
