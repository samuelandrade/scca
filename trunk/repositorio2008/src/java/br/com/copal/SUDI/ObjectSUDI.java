/*
 * ObjectSUDI.java
 *
 * Created on 4 de Dezembro de 2007, 15:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.SUDI;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author copal
 */
public abstract class ObjectSUDI {
    
    /** Creates a new instance of ObjectSUDI */
   private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("copal2PU");
    
   public List namedQueryListNone(String  namedQuery) {
        EntityManager em = emf.createEntityManager();
        List aux = null;
        try{
            aux = (List) em.createNamedQuery(namedQuery).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //em.close();
        }
        return aux;
    }//end metodo recuperarTodos
    
    /**
     * método genérico que recebe um namedQuery, um parâmetro String e um objeto. Retorna uma lista (List)
     */
    public List namedQueryListOne(String namedQuery, String paramName, Object param) {
        EntityManager em = emf.createEntityManager();
        List aux = null;
        try{
            aux = (List) em.createNamedQuery(namedQuery).setParameter(paramName,param).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //em.close();
        }
        return aux;
    }//end metodo recuperarTodos
    
    /**
     * método genérico que recebe um namedQuery, um parâmetro String e um objeto. Retorna uma lista (List)
     */
    public List namedQueryListTwo(String namedQuery, String paramName, Object param, String paramName2, Object param2) {
        EntityManager em = emf.createEntityManager();
        List aux = null;
        try{
            aux = (List) em.createNamedQuery(namedQuery).setParameter(paramName,param).setParameter(paramName2,param2).getResultList();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
                    
            e.printStackTrace();
        }finally {
            //em.close();
        }
        return aux;
    }//end metodo recuperarTodos
    
    
    public List namedQueryListThree(String namedQuery, String paramName, Object param, String paramName2, Object param2, String paramName3, Object param3) {
        EntityManager em = emf.createEntityManager();
        List aux = null;
        try{
            aux = (List) em.createNamedQuery(namedQuery).setParameter(paramName,param).setParameter(paramName2,param2).setParameter(paramName3,param3).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //em.close();
        }
        return aux;
    }//end metodo recuperarTodos
    
    /**
     * método genérico que recebe um namedQuery, e um parâmetro String e retorna um objeto (Object)
     */
    public Object namedQueryObjectOne(String namedQuery, String paramName, Object param) {
        EntityManager em = emf.createEntityManager();
        Object aux = null;
        try{
            aux = em.createNamedQuery(namedQuery).setParameter(paramName,param).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //em.close();
        }
        return aux;
    }//end metodo recuperarTodos
    
    /**
     * método genérico que recebe um namedQuery, um parâmetro String e um objeto. Retorna uma lista (List)
     */
    public Object namedQueryObjectTwo(String namedQuery, String paramName, Object param, String paramName2, Object param2) {
        EntityManager em = emf.createEntityManager();
        Object aux = null;
        try{
            aux = (Object) em.createNamedQuery(namedQuery).setParameter(paramName,param).setParameter(paramName2,param2).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //em.close();
        }
        return aux;
    }//end metodo recuperarTodos
    
    public Object atualizar(Object o) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            o = em.merge(o);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught atualizar ObjectSUDI", e);
            em.getTransaction().rollback();
        }
        return o;
    }
    
    public void remover(Object o) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //necessario dar um merge para poder excluir, senão não atualiza
            o = em.merge(o);
            em.remove(o);
            em.getTransaction().commit();
        } catch(Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught persist ProfessorSUDI", e);
            em.getTransaction().rollback();
        } finally {
            //em.close();
        }
    }//end metodo remover
    
    
    public void removerLista(String namedQuery, String paramName, Object param){
        EntityManager em = emf.createEntityManager();
        List aux = null;
        try{
            aux = (List) em.createNamedQuery(namedQuery).setParameter(paramName, param).getResultList();
            for(int i = 0; i < aux.size(); i++){
                em.getTransaction().begin();
                Object o = aux.get(i);
                 o = em.merge(o);
                 em.remove(o);
                 em.getTransaction().commit();
            }
        }
        catch(Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught persist ProfessorSUDI", e);
            em.getTransaction().rollback();
        }
    }
       
    public void salvar(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        }catch(Exception e) {
            System.out.println(e.getMessage());
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught", e);
            em.getTransaction().rollback();
        }finally {
            //em.close();
        }
    }//end metodo salvar
    
    public List recuperarPorFiltro(String cidade, String bairro, String setor, String negociador, String situacao,String boletognome) {
         EntityManager em = emf.createEntityManager();
         List aux = null;
        try{
            aux = (List) em.createQuery("SELECT a from Avon a WHERE a.setor LIKE concat(:setor,'%') AND"
                    +" a.cidade LIKE concat(:cidade,'%') AND a.bairro LIKE concat(:bairro,'%') AND"
                    +" a.cobrador.nome LIKE concat(:ncobrador,'%')").setParameter("cidade",cidade)
                    .setParameter("setor",setor).setParameter("bairro",bairro).setParameter("ncobrador",negociador)
                    .getResultList();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
                    
            e.printStackTrace();
        }finally {
            //em.close();
        }
        return aux;
    }
    
}//end class ObjectSUDI


