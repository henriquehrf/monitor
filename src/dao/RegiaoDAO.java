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
import vo.Regiao;

/**
 *
 * @author Henrique Firmino
 */
public class RegiaoDAO extends GenericoDAO<Regiao> {
    
    public List<Regiao> BuscarRegiao(){
         EntityManager em = getEM();
         List<Regiao> list;
         try{
             Query query = em.createNamedQuery("Regiao.BuscarTodosRegiao");
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
