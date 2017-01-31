/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import dao.EntidadeBase;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





/**
 *
 * @author Henrique Firmino
 */
@Entity
public class Medicao  implements Serializable, EntidadeBase{

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_medicao;
    
    @Column(scale = 6, precision = 2)
    private float umidade_dht;
    
    @Column(scale = 6, precision = 2)
    private float temperatura_dht;
    
    @Column(scale = 6, precision = 2)
    private float temperatura_ds;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date rtc;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dt_importacao;
    
    
    public Long getId_medicao() {
        return id_medicao;
    }

    public void setId_medicao(Long id_medicao) {
        this.id_medicao = id_medicao;
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
     * @return the temperatura_dht
     */
    public float getTemperatura_dht() {
        return temperatura_dht;
    }

    /**
     * @param temperatura_dht the temperatura_dht to set
     */
    public void setTemperatura_dht(float temperatura_dht) {
        this.temperatura_dht = temperatura_dht;
    }

    /**
     * @return the temperatura_ds
     */
    public float getTemperatura_ds() {
        return temperatura_ds;
    }

    /**
     * @param temperatura_ds the temperatura_ds to set
     */
    public void setTemperatura_ds(float temperatura_ds) {
        this.temperatura_ds = temperatura_ds;
    }

    /**
     * @return the rtc
     */
    public Date getRtc() {
        return rtc;
    }

    /**
     * @param rtc the rtc to set
     */
    public void setRtc(Date rtc) {
        this.rtc = rtc;
    }

    /**
     * @return the dt_importacao
     */
    public Date getDt_importacao() {
        return dt_importacao;
    }

    /**
     * @param dt_importacao the dt_importacao to set
     */
    public void setDt_importacao(Date dt_importacao) {
        this.dt_importacao = dt_importacao;
    }

    @Override
    public Long getId() {
        return id_medicao;
    }

 
    
}
