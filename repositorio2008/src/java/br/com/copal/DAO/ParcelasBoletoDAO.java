/*
 * ParcelasBoletoDAO.java
 *
 * Created on 9 de Agosto de 2008, 10:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.DAO;

import br.com.copal.entity.ParcelasBoleto;
import java.util.List;

/**
 *
 * @author Silva
 */
public interface ParcelasBoletoDAO {
    
    /** Creates a new instance of TextoBoletoDAO */
     //Metodos Padrões
    public void salvar(ParcelasBoleto parcelasBoleto);
    public void remover(ParcelasBoleto parcelasBoleto);
    public ParcelasBoleto atualizar(ParcelasBoleto parcelasBoleto);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    
    
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
    public ParcelasBoleto recuperarporId(long id);   
    
}
