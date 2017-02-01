/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Henrique Firmino
 */
public class Conversao {

     List<DadosCache> cache = new ArrayList<>();

    public List<DadosCache> getFile(File localizacao) {
        try {

            Scanner scanner = new Scanner(new FileReader(localizacao.getAbsolutePath())).useDelimiter("\\||\\n");

            
            while (scanner.hasNext()) {
                DadosCache dc = new DadosCache();

                String umidade_dht = scanner.next();
                String temp_dht = scanner.next();
                String temp_ds = scanner.next();
                String data = scanner.next();
                Date dt = new Date();
                SimpleDateFormat dt_formatada = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                dt = (Date) dt_formatada.parse(data);

                dc.setData(dt);
                dc.setTemp_ds(Float.parseFloat(temp_ds));
                dc.setTemp_dth(Float.parseFloat(temp_dht));
                dc.setUmidade_dht(Float.parseFloat(umidade_dht));
                cache.add(dc);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cache;

    }

}
