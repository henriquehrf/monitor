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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import monitor.Alertas;
import monitor.Animacao;
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

        if (tblPrincipal.getSelectionModel().getSelectedItem() == null) {
            return;
        } else {
            GraficoController.real_time=false;
            GraficoController.menu=true;

            an.ProgressIndicator(true, "Acessando o Banco de Dados  e Renderizando o Gráfico");
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
                an.ProgressIndicator(false, "");
                Parent root;
                root = FXMLLoader.load(GraficoController.class.getClassLoader().getResource("fxml/grafico.fxml"), ResourceBundle.getBundle("monitor/i18N_pt_BR"));
                Monitor.SCENE.setRoot(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    void btnImprimir_OnAction(ActionEvent event) {

    }

    @FXML
    void btnExcluir_OnAction(ActionEvent event) {
        Animacao an = new Animacao();
        Alertas alert = new Alertas();

        if (tblPrincipal.getSelectionModel().getSelectedItem() != null) {
            int i = tblPrincipal.getSelectionModel().getSelectedItems().size();
            String msg1;
            String msg2;
            if (i == 1) {
                msg1 = "Confirma Remover o Arquivo Selecionado?";
                msg2 = "A remoção foi realizada com sucesso";
            } else {
                msg1 = "Confirma Remover os Arquivos Selecionados?";
                msg2 = "As remoções foram realizadas com sucesso";
            }
            if (alert.alerta(Alert.AlertType.CONFIRMATION, "Confirmação do Usuário", msg1, "Sim", "Não")) {
                an.ProgressIndicator(true, "Apagando Informações do Banco de Dados");

                if (remover(tblPrincipal.getSelectionModel().getSelectedItems())) {
                    try {
                        lista = reg.BuscarRegiao();
                        completar(lista);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    an.ProgressIndicator(false, "");
                    alert.alerta(Alert.AlertType.INFORMATION, "Operação Realizada Com Sucesso", msg2);

                }

            } else {
                System.out.println("Erro");
            }
        }
    }

    boolean remover(List<Regiao> reg) {

        for (int j = 0; j < reg.size(); j++) {

            NegocioMedicao NMed = new NegocioMedicao(MDao);
            NegocioRegiao NReg = new NegocioRegiao(RDao);

            try {
                List<Medicao> dadoMedicao = NMed.BuscarDados(reg.get(j));

                for (int i = 0; i < dadoMedicao.size(); i++) {
                    NMed.remover(dadoMedicao.get(i));
                }
                NReg.remover(reg.get(j));

                System.out.println("Remocao Realizada com Sucesso");

            } catch (Exception ex) {
                return false;
            }
        }

        return true;
    }

    @FXML
    void btnVoltar_OnAction(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(PrincipalController.class.getClassLoader().getResource("fxml/Principal.fxml"), ResourceBundle.getBundle("monitor/i18N_pt_BR"));
            Monitor.SCENE.setRoot(root);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void txtBuscadorOnKeyPressed(ActionEvent event) {

    }

    List<Regiao> lista;
    RegiaoDAO RDao = new RegiaoDAO();
    NegocioRegiao reg = new NegocioRegiao(RDao);
    MedicaoDAO MDao = new MedicaoDAO();
    NegocioMedicao medicao = new NegocioMedicao(MDao);
    Animacao an = new Animacao();

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

        tblPrincipal.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
            lista = reg.BuscarRegiao();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        completar(lista);

    }

}
