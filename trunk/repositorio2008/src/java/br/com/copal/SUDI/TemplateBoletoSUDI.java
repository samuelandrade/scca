/*
 * TemplateBoletoSUDI.java
 *
 * Created on 29 de Julho de 2008, 14:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.SUDI;

import br.com.copal.DAO.TemplateBoletoDAO;
import br.com.copal.entity.TemplateBoleto;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class TemplateBoletoSUDI extends ObjectSUDI implements TemplateBoletoDAO  {
    
    /** Creates a new instance of TemplateBoletoSUDI */
    public TemplateBoletoSUDI() {
    }

    public void salvar(TemplateBoleto templateBoleto) {
        super.salvar(templateBoleto);
    }

    public void remover(TemplateBoleto templateBoleto) {
        super.remover(templateBoleto);
    }

    public TemplateBoleto atualizar(TemplateBoleto templateBoleto) {
        return (TemplateBoleto)super.atualizar(templateBoleto);
    }

    public List recuperarTodos() {
        return super.namedQueryListNone("TemplateBoleto.recuperarTodos");
    }

    public TemplateBoleto recuperarporId(int id) {
        return (TemplateBoleto)super.namedQueryListOne("TemplateBoleto.recuperarPorId","id",id);
    }
    
}
