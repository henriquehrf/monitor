/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.Date;

/**
 *
 * @author Henrique Firmino
 */
public class DadosCache {
    
    private float temp_dth;
    private float temp_ds;
    private float umidade_dht;
    private Date data;

    /**
     * @return the temp_dth
     */
    public float getTemp_dth() {
        return temp_dth;
    }

    /**
     * @param temp_dth the temp_dth to set
     */
    public void setTemp_dth(float temp_dth) {
        this.temp_dth = temp_dth;
    }

    /**
     * @return the temp_ds
     */
    public float getTemp_ds() {
        return temp_ds;
    }

    /**
     * @param temp_ds the temp_ds to set
     */
    public void setTemp_ds(float temp_ds) {
        this.temp_ds = temp_ds;
    }

    /**
     * @return the umidade_dht
     */
    public float getUmidade_dht() {
        return umidade_dht;
    }

    /**
     * @param umidade_dht the umidade_dht to set
     */
    public void setUmidade_dht(float umidade_dht) {
        this.umidade_dht = umidade_dht;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }
    
    
    
}
