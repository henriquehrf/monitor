/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import dao.MedicaoDAO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.NegocioMedicao;
import vo.Medicao;

/**
 *
 * @author Henrique Firmino
 */
public class testeBanco {

    public static void main(String[] args) {
        Date dt = new Date();
        Medicao med = new Medicao();
      //  med.setDt_importacao(dt);
        med.setRtc(dt);
        med.setTemperatura_dht(3);
        med.setUmidade_dht(0);
        med.setTemperatura_ds(0);

        MedicaoDAO m = new MedicaoDAO();
        NegocioMedicao neg = new NegocioMedicao(m);

        try {
            System.out.println("tentando salvar");
            neg.salvar(med);
        } catch (Exception ex) {
            System.out.println("erro no salvar");
        }

    }

}
