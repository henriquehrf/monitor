/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import monitor.Alertas;


/**
 *
 * @author Henrique Firmino
 */
public class GenericoDAO<T extends EntidadeBase> {

    public EntityManager getEM() {
        EntityManagerFactory factory;
        try {
            factory = Persistence.createEntityManagerFactory("MonitorPU");
            return factory.createEntityManager();
        } catch (Exception ex) {
            Alertas alert = new Alertas();
            alert.alerta(Alert.AlertType.ERROR, "Erro de Conexão com Banco de Dados", "Não foi possivel conectar com o Banco de Dados");
        }
        return null;

    }

    public T salvar(Class<T> clazz, T t) throws Exception {
        EntityManager em = getEM();

        try {
            em.getTransaction().begin();
            if (t.getId() == null) {
                em.persist(t); // executa insert
            } else {
                if (!em.contains(t)) {
                    if (em.find(clazz, t.getId()) == null) {
                        throw new Exception("Erro ao alterar o cadastro.");
                    }
                }
                t = em.merge(t); // executa update
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro ao salvar");
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return t;
    }

    public void remover(Class<T> clazz, T tt) throws Exception {
        EntityManager em = getEM();

        T t = em.find(clazz, tt.getId());

        try {
            em.getTransaction().begin();
            em.remove(t);  // executa o delete
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Erro ao remover");
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public T consutarPorId(Class<T> clazz, T tt) {
        EntityManager em = getEM();
        T t = null;

        try {
            t = em.find(clazz, tt.getId()); // execulta o select no banco de dados

        } catch (Exception ex) {
            System.out.println("Erro ao consultar");
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return t;
    }
}
