/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.Medicao;
import vo.Regiao;

/**
 *
 * @author Henrique Firmino
 */
public class MedicaoDAO extends GenericoDAO<Medicao> {

    public List<Medicao> BuscarDados(Regiao regiao) {
        EntityManager em = getEM();
        List<Medicao> list;
        try{
            Query query = em.createNamedQuery("Medicao.BuscarDados");
            query.setParameter("regiao",regiao.getId_regiao());
            list = query.getResultList();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
             list = new ArrayList();
        }finally{
            em.close();
        }
        
        return list;

    }

}
