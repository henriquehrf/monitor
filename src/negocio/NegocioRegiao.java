/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.RegiaoDAO;
import java.util.List;
import vo.Regiao;

/**
 *
 * @author Henrique Firmino
 */
public class NegocioRegiao {

    public NegocioRegiao(RegiaoDAO regiaoDAO) {
        this.regiaoDAO = regiaoDAO;
    }
  
    private final RegiaoDAO regiaoDAO;
    
    public Regiao salvar(Regiao regiao)throws Exception{
        //  return mediaoDAO.salvar(Medicao.class, medicao);
        return regiaoDAO.salvar(Regiao.class, regiao);
        
    }
    public List<Regiao> BuscarRegiao() throws Exception{
      return regiaoDAO.BuscarRegiao();
    }
    
}
