/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.MedicaoDAO;
import java.util.List;
import vo.Medicao;
import vo.Regiao;

/**
 *
 * @author Henrique Firmino
 */
public class NegocioMedicao {

    public NegocioMedicao(MedicaoDAO mediaoDAO) {
        this.medicaoDAO = mediaoDAO;
    }
    
   private final MedicaoDAO medicaoDAO;
   
   public Medicao salvar(Medicao medicao)throws Exception{
       String erro="";
       
           System.out.println("entrei no salvar");
           return medicaoDAO.salvar(Medicao.class, medicao);

       
      
   }
   
   public List<Medicao> BuscarDados(Regiao reg)throws Exception{
       return medicaoDAO.BuscarDados(reg);
       
   }
    
   public void remover(Medicao medicao) throws Exception{
       try{
           medicaoDAO.remover(Medicao.class, medicao);
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
   }
}
