/*
 * TemplateBoletoDAO.java
 *
 * Created on 29 de Julho de 2008, 14:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.DAO;

import br.com.copal.entity.TemplateBoleto;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface TemplateBoletoDAO {
    
    /** Creates a new instance of TemplateBoletoDAO */
     //Metodos Padrões
    public void salvar(TemplateBoleto templateBoleto);
    public void remover(TemplateBoleto templateBoleto);
    public TemplateBoleto atualizar(TemplateBoleto templateBoleto);
    
    // Pesquisas de Lista
    public List recuperarTodos();    
    
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
    public TemplateBoleto recuperarporId(int id);    
    
}
