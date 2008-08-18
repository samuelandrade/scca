/*
 * AvonSUDI.java
 *
 * Created on 4 de Dezembro de 2007, 15:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.SUDI;

import br.com.copal.DAO.AvonDAO;
import br.com.copal.entity.Avon;
import br.com.copal.entity.Cobrador;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.SQLQuery;

/**
 *
 * @author copal
    */
public class AvonSUDI extends ObjectSUDI implements AvonDAO{
    
    /** Creates a new instance of AvonSUDI */
    public AvonSUDI() {}

    public void salvar(Avon avon) {
        super.salvar(avon);
    }

    public Avon atualizar(Avon avon) {
        return (Avon)super.atualizar(avon);
    }

    public void remover(Avon avon) {
        super.remover(avon);
    }

    public List recuperarTodos() {
        List ls =  super.namedQueryListNone("Avon.recuperarTodos"); 
        return ls;
    }

    public List recuperarPorCidade(String cidade) {
      return super.namedQueryListOne("Avon.recuperarCidade","cidade",cidade);  
    }
    
     public List recuperarPorCobrador(String nomeCobrador){
        return super.namedQueryListOne("Avon.recuperarPorCobrador","cobrador",nomeCobrador);
    }

   
    public List recuperarPorSituacaoCb(String nomeSituacao){
        return super.namedQueryListOne("Avon.recuperarPorSituacaoCb","situacaocb",nomeSituacao);
    }
     
    public List recuperarPorAviso(Integer aviso) {
       return super.namedQueryListOne("Avon.recuperarPorAviso","aviso",aviso); 
    }

    public List recuperarPorSetor(int setor) {
        return super.namedQueryListOne("Avon.recuperarPorSetor","setor",setor);
    }

    public List recuperarPorNome(String nome) {
        return super.namedQueryListOne("Avon.recuperarPorNome","nome",nome);
    }

    public Avon recuperarPorAvisoObjeto(Integer aviso) {
        return (Avon) super.namedQueryObjectOne("Avon.recuperarPorAvisoObjeto","aviso",aviso);
    }
  
    public List recuperarTodos2(String cobrador){
        return super.namedQueryListOne("Avon.recuperarTodos2","cobrador",cobrador);
    }

    public List recuperarPorStatusCobrador(String nomeCobrador, String statusAviso) {
        return super.namedQueryListTwo("Avon.recuperarPorStatusCobrador","cobrador", nomeCobrador,"statusaviso",statusAviso);
    }

    public List recuperarVencidos(Date devolucao, String cobrador) {
        return super.namedQueryListTwo("Avon.recuperarVencidos","devolucao",devolucao,"cobrador",cobrador);
    }

    public List recuperarPorFiltros(String cidade, String bairro, String setor, String negociador, String situacao, String boletognome) {
        return super.recuperarPorFiltro(cidade,bairro,setor,negociador,situacao,boletognome);
    }
}
