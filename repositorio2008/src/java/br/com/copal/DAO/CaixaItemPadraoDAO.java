/*
 * CaixaItemPadraoDAO.java
 *
 * Created on 6 de Maio de 2008, 07:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.DAO;

import br.com.copal.entity.CaixaItemPadrao;
import java.util.List;

/**
 *
 * @author Nhandeara
 */
public interface CaixaItemPadraoDAO {
    
    // Metodos Padrões
    public void salvar(CaixaItemPadrao caixaitempadrao);
    public void remover(CaixaItemPadrao caixaitempadrao);
    public CaixaItemPadrao atualizar(CaixaItemPadrao caixaitempadrao);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    
}
