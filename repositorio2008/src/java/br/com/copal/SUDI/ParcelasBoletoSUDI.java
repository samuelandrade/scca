/*
 * ParcelasBoletoSUDI.java
 *
 * Created on 9 de Agosto de 2008, 10:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.SUDI;

import br.com.copal.DAO.ParcelasBoletoDAO;
import br.com.copal.entity.ParcelasBoleto;
import java.util.List;

/**
 *
 * @author Silva
 */
public class ParcelasBoletoSUDI extends ObjectSUDI implements ParcelasBoletoDAO{
    
    /** Creates a new instance of ParcelasBoletoSUDI */
    public ParcelasBoletoSUDI() {
    }

    public void salvar(ParcelasBoleto parcelasBoleto) {
        super.salvar(parcelasBoleto);
    }

    public void remover(ParcelasBoleto parcelasBoleto) {
        super.remover(parcelasBoleto);
    }

    public ParcelasBoleto atualizar(ParcelasBoleto parcelasBoleto) {
        return (ParcelasBoleto)super.atualizar(parcelasBoleto);
    }

    public List recuperarTodos() {
        return super.namedQueryListNone("ParcelasBoleto.recuperarTodos");
    }

    public ParcelasBoleto recuperarporId(long id) {
        return (ParcelasBoleto)super.namedQueryListOne("ParcelasBoleto.recuperarPorId","id",id);
    }
    
}
