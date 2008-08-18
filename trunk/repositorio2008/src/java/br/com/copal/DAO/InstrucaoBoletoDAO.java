package br.com.copal.DAO;

import br.com.copal.entity.InstrucaoBoleto;
import java.util.List;

/**
 *
 * @author Jeyson
 */
public interface InstrucaoBoletoDAO {
    
    /** Creates a new instance of InstrucaoBoletoDAO */
     //Metodos Padrões
    public void salvar(InstrucaoBoleto instrucaoBoleto);
    public void remover(InstrucaoBoleto instrucaoBoleto);
    public InstrucaoBoleto atualizar(InstrucaoBoleto instrucaoBoleto);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    
    
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
    public InstrucaoBoleto recuperarporId(int id);    
    
}
