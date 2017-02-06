/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import dao.EntidadeBase;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Henrique Firmino
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Regiao.BuscarTodosRegiao",query ="Select p from Regiao p")
})
public class Regiao implements Serializable, EntidadeBase {

    @Override
    public Long getId() {
        return id_regiao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_regiao;

    @Column(length = 100, nullable = false)
    private String regiao;

    /**
     * @return the id_regiao
     */
    public Long getId_regiao() {
        return id_regiao;
    }

    /**
     * @param id_regiao the id_regiao to set
     */
    public void setId_regiao(Long id_regiao) {
        this.id_regiao = id_regiao;
    }

    /**
     * @return the regiao
     */
    public String getRegiao() {
        return regiao;
    }

    /**
     * @param regiao the regiao to set
     */
    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

}
