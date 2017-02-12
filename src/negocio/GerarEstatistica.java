/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrique Firmino
 */
public class GerarEstatistica {

    private float media;
    private float dPadrao;
    private float varianca;
    private float max;
    private float min;

    private String Temp_Solo;
    private String Temp_Ambiente;
    private String Umidade;
    private String T_Solo_Ambiente;

    List<DadosCache> memoria = new ArrayList<>();

    public void calcular() {
        media = 0;
        dPadrao = 0;
        varianca = 0;
        max = memoria.get(0).getUmidade_dht();
        min = memoria.get(0).getUmidade_dht();
        
        DecimalFormat df = new DecimalFormat("0.00");

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
            varianca = varianca / (memoria.size() - 1);
            media = media / memoria.size();

        }

        //Math.sqrt(x);
        dPadrao = (float) Math.sqrt(varianca);

        Umidade = "Máx: " + df.format(max) + "%";
        Umidade += " Mín: " + df.format(min) + "%";
        Umidade += " Média:" + df.format(media) + "%";
        Umidade += " Var: " + df.format(varianca);
        Umidade += " Des.Padrão: " + df.format(dPadrao);
        reset();

        float max = memoria.get(0).getTemp_dth();
        float min = memoria.get(0).getTemp_dth();

        for (int i = 0; i < memoria.size(); i++) {
            if (memoria.get(i).getTemp_dth() > max) {
                max = memoria.get(i).getTemp_dth();
            }
            if (memoria.get(i).getTemp_dth() < min) {
                min = memoria.get(i).getTemp_dth();
            }
            media = memoria.get(i).getTemp_dth() + media;
            varianca = memoria.get(i).getTemp_dth() * memoria.get(i).getTemp_dth() + dPadrao;
        }
        if (memoria.size() <= 1) {
            varianca = 0;

        } else {
            varianca = varianca / (memoria.size() - 1);
            media = media / memoria.size();

        }
        dPadrao = (float) Math.sqrt(varianca);

        Temp_Ambiente = "Máx: " + df.format(max) + "ºC";
        Temp_Ambiente += " Mín: " + df.format(min) + "ºC";
        Temp_Ambiente += " Média: " + df.format(media) + "ºC";
        Temp_Ambiente += " Var: " + df.format(varianca);
        Temp_Ambiente += " Des.Padrão: " + df.format(dPadrao);

        reset();
        max = memoria.get(0).getTemp_ds();
        min = memoria.get(0).getTemp_ds();

        for (int i = 0; i < memoria.size(); i++) {
            if (memoria.get(i).getTemp_ds() > max) {
                max = memoria.get(i).getTemp_ds();
            }
            if (memoria.get(i).getTemp_ds() < min) {
                min = memoria.get(i).getTemp_ds();
            }
            media = memoria.get(i).getTemp_ds() + media;
            varianca = memoria.get(i).getTemp_ds() * memoria.get(i).getTemp_ds() + dPadrao;
        }
        if (memoria.size() <= 1) {
            varianca = 0;

        } else {
            varianca = varianca / (memoria.size() - 1);
            media = media / memoria.size();

        }
        dPadrao = (float) Math.sqrt(varianca);

        Temp_Solo = "Máx: " + df.format(max) + "ºC";
        Temp_Solo += " Mín: " + df.format(min) + "ºC";
        Temp_Solo += " Média: " + df.format(media) + "ºC";
        Temp_Solo += " Var: " + df.format(varianca);
        Temp_Solo += " Des.Padrão: " + df.format(dPadrao);

        reset();

        max = memoria.get(0).getTemp_ds();
        min = memoria.get(0).getTemp_ds();

        for (int i = 0; i < memoria.size(); i++) {
            if (memoria.get(i).getTemp_ds() > max) {
                max = memoria.get(i).getTemp_ds();
            }
            if (memoria.get(i).getTemp_ds() < min) {
                min = memoria.get(i).getTemp_ds();
            }
            if (memoria.get(i).getTemp_dth() > max) {
                max = memoria.get(i).getTemp_dth();
            }
            if (memoria.get(i).getTemp_dth() < min) {
                min = memoria.get(i).getTemp_dth();
            }
            media = (memoria.get(i).getTemp_ds() + memoria.get(i).getTemp_dth()) / 2 + media;
            varianca = (memoria.get(i).getTemp_ds() - memoria.get(i).getTemp_dth()) * (memoria.get(i).getTemp_ds() - memoria.get(i).getTemp_dth()) + dPadrao;
        }
        if (memoria.size() <= 1) {
            varianca = 0;

        } else {
            varianca = varianca / (memoria.size() - 1);
            media = media / memoria.size();

        }
        dPadrao = (float) Math.sqrt(varianca);

        T_Solo_Ambiente = "Máx: " + df.format(max) + "ºC";
        T_Solo_Ambiente += " Mín: " + df.format(min) + "ºC";
        T_Solo_Ambiente += " Média: " + df.format(media) + "ºC";
        T_Solo_Ambiente += " Var: " + df.format(varianca);
        T_Solo_Ambiente += " Des.Padrão: " + df.format(dPadrao);
        reset();

    }

    void reset() {
        setdPadrao(0);
        setMax(0);
        setMin(0);
        setVarianca(0);
        setMedia(0);
    }

    public GerarEstatistica(float max, float min, List<DadosCache> memoriaDado) {
        this.max = max;
        this.min = min;
        this.memoria = memoriaDado;
    }

    /**
     * @return the media
     */
    public float getMedia() {
        return media;
    }

    /**
     * @param media the media to set
     */
    public void setMedia(float media) {
        this.media = media;
    }

    /**
     * @return the dPadrao
     */
    public float getdPadrao() {
        return dPadrao;
    }

    /**
     * @param dPadrao the dPadrao to set
     */
    public void setdPadrao(float dPadrao) {
        this.dPadrao = dPadrao;
    }

    /**
     * @return the varianca
     */
    public float getVarianca() {
        return varianca;
    }

    /**
     * @param varianca the varianca to set
     */
    public void setVarianca(float varianca) {
        this.varianca = varianca;
    }

    /**
     * @return the max
     */
    public float getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(float max) {
        this.max = max;
    }

    /**
     * @return the min
     */
    public float getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(float min) {
        this.min = min;
    }

    /**
     * @return the Temp_Solo
     */
    public String getTemp_Solo() {
        return Temp_Solo;
    }

    /**
     * @return the Temp_Ambiente
     */
    public String getTemp_Ambiente() {
        return Temp_Ambiente;
    }

    /**
     * @return the Umidade
     */
    public String getUmidade() {
        return Umidade;
    }

    /**
     * @return the T_Solo_Ambiente
     */
    public String getT_Solo_Ambiente() {
        return T_Solo_Ambiente;
    }

}
