/*
 * PagamentoDAO.java
 *
 * Created on 9 de Janeiro de 2008, 15:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.DAO;

import br.com.copal.entity.Pagamento;
import java.util.Date;
import java.util.List;

/**
 *
 * @author copal
 */
public interface PagamentoDAO {
    
    //Metodos Padrões
    public void salvar(Pagamento pagamento);
    public Pagamento atualizar(Pagamento pagamento);
    public void remover(Pagamento pagamento);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    public List recuperarPorAd(Integer idAd);
    public List recuperarPorCobradorePeriodo(Date datF, Date datI, String Ncob);
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
        
}
