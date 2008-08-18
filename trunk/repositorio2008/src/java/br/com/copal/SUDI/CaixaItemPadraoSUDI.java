/*
 * CaixaItemPadraoSUDI.java
 *
 * Created on 6 de Maio de 2008, 07:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.SUDI;

import br.com.copal.DAO.CaixaItemPadraoDAO;
import br.com.copal.entity.CaixaItemPadrao;
import java.util.List;

/**
 *
 * @author Nhandeara
 */
public class CaixaItemPadraoSUDI extends ObjectSUDI implements CaixaItemPadraoDAO {
    
    /** Creates a new instance of CaixaItemPadraoSUDI */
    public CaixaItemPadraoSUDI(){}

    public void salvar(CaixaItemPadrao caixaitempadrao) {
        super.salvar(caixaitempadrao);
    }

    public void remover(CaixaItemPadrao caixaitempadrao) {
        super.remover(caixaitempadrao);
    }

    public CaixaItemPadrao atualizar(CaixaItemPadrao caixaitempadrao) {
        return (CaixaItemPadrao) super.atualizar(caixaitempadrao);
    }
    
    public List recuperarTodos() {
        List ls =  super.namedQueryListNone("CaixaItemPadrao.recuperarTodos");
        return ls;
    }
}
