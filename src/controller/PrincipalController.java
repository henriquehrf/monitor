/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MedicaoDAO;
import dao.RegiaoDAO;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jssc.Porta;
import monitor.Alertas;
import monitor.LerMessage;
import monitor.Monitor;
import monitor.Animacao;
import negocio.Conversao;
import negocio.DadosCache;
import negocio.NegocioMedicao;
import negocio.NegocioRegiao;
import vo.Medicao;
import vo.Regiao;

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

    Animacao an = new Animacao();

    @FXML
    void btnImportarDados_OnAction(ActionEvent event) {
        Alertas aviso = new Alertas();
        LerMessage message = new LerMessage();
        try {

            Conversao conv = new Conversao();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Localizar Arquivo");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Arquivo de Texto", "*.txt"));
            Stage stage = new Stage();
            File file = fileChooser.showOpenDialog(stage);

            if (file == null) {
                return;
            }

            if (aviso.alerta(Alert.AlertType.CONFIRMATION, message.getMessage("msgAguardandoConfirmacao"), message.getMessage("msgConfirmacaoDeImportacao"), message.getMessage("msgSim"), message.getMessage("msgNao"))) {
                String local = aviso.entrada_dados("Completar Dados Obrigatório", "Digite de qual local foi esta medição?", "Por favor digitel o local: ");

                if (local != "") {

                    salvar(local, conv.getFile(file));
                    an.ProgressIndicator(2000, "Salvando Informações no Banco de Dados");

                } else {
                    System.out.println("Erro!");
                }

                if (aviso.alerta(Alert.AlertType.CONFIRMATION, message.getMessage("msgAguardandoConfirmacao"), "Importação realizada com sucesso! \nDeseja visualizar o gráfico?", message.getMessage("msgSim"), message.getMessage("msgNao"))) {
                    an.ProgressIndicator(true, "Renderizando o Gráfico");
                    conv.getFile(file);
                    GraficoController.setMemoria(conv.getFile(file));
                    an.ProgressIndicator(false, "");
                    GraficoController.menu=true;
                    Parent root;
                    root = FXMLLoader.load(GraficoController.class.getClassLoader().getResource("fxml/grafico.fxml"), ResourceBundle.getBundle("monitor/i18N_pt_BR"));
                    Monitor.SCENE.setRoot(root);
                }

            } else {
                aviso.alerta(Alert.AlertType.INFORMATION, message.getMessage("msgAcaoRealizadaComSucesso"), message.getMessage("msgGeracaoGrafico"));

                an.ProgressIndicator(true, "Renderizando o Gráfico");
                conv.getFile(file);
                GraficoController.setMemoria(conv.getFile(file));
                an.ProgressIndicator(false, "");
                GraficoController.menu=false;
                Parent root;
                root = FXMLLoader.load(GraficoController.class.getClassLoader().getResource("fxml/grafico.fxml"), ResourceBundle.getBundle("monitor/i18N_pt_BR"));
                Monitor.SCENE.setRoot(root);

            }
        } catch (Exception ex) {
            //aviso.alerta(Alert.AlertType.WARNING, "Erro", ex.getMessage());
            aviso.erro("Erro ao ler o arquivo", "Não foi possivel realizar a leitura do arquivo", "O arquivo não é compativel para a leitura ou pode estar corrompido", ex);
        }

    }

    void salvar(String local, List<DadosCache> leitura) {

        Regiao reg = new Regiao();

        reg.setRegiao(local);

        RegiaoDAO RDao = new RegiaoDAO();
        NegocioRegiao negRegiao = new NegocioRegiao(RDao);

        try {

            negRegiao.salvar(reg);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {

            for (int i = 0; i < leitura.size(); i++) {
                Medicao med = new Medicao();
                MedicaoDAO MDao = new MedicaoDAO();
                NegocioMedicao negMedicao = new NegocioMedicao(MDao);

                med.setTemperatura_dht(leitura.get(i).getTemp_dth());
                med.setRtc(leitura.get(i).getData());
                med.setTemperatura_ds(leitura.get(i).getTemp_ds());
                med.setUmidade_dht(leitura.get(i).getUmidade_dht());
                med.setId_regiao(reg);

                negMedicao.salvar(med);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

//          MedicaoDAO m = new MedicaoDAO();
//        NegocioMedicao neg = new NegocioMedicao(m);
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

        try {

            Parent root;
            root = FXMLLoader.load(Consulta_MedicaoController.class.getClassLoader().getResource("fxml/Consulta_Medicao.fxml"), ResourceBundle.getBundle("monitor/i18N_pt_BR"));
            Monitor.SCENE.setRoot(root);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void btnVisualizarDados_OnKeyPressed(ActionEvent event) {

    }

    @FXML
    void btnEstatistica_OnAction(ActionEvent event) {

        //System.out.println("aqui entrei na Porta Serial COM4");

        Alertas alert = new Alertas();
        Porta porta = new Porta();
        
        if(porta.getPort().size()==0){
            alert.alerta(Alert.AlertType.WARNING, "Portas Seriais Indisponíveis", "Não foi possivel conectar ou não há nenhuma porta serial disponível");
            return;
        }
        
        GraficoController.porta_serial=alert.escolha(porta.getPort(), "Portas Seriais Disponíveis", "Selecione uma porta serial disponível", "Porta selecionada");
        if (GraficoController.porta_serial != "") {

            
            GraficoController.real_time = true;
            DadosCache dc = new DadosCache();
            Date data = new Date();
            dc.setData(data);
            dc.setTemp_ds(0);
            dc.setTemp_dth(0);
            dc.setUmidade_dht(0);

            List<DadosCache> dado = new ArrayList<>();
            dado.add(dc);
            GraficoController.setMemoria(dado);

            try {

                Parent root;
                root = FXMLLoader.load(Consulta_MedicaoController.class.getClassLoader().getResource("fxml/grafico.fxml"), ResourceBundle.getBundle("monitor/i18N_pt_BR"));
                Monitor.SCENE.setRoot(root);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    void btnEstatistica_OnKeyPressed(ActionEvent event) {

    }

    public void initialize() {
        //   test();
    }

}
