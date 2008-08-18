package br.com.copal.DAO;

import br.com.copal.entity.TextoBoleto;
import java.util.List;

/**
 *
 * @author Jeyson
 */
public interface TextoBoletoDAO {
    
    /** Creates a new instance of TextoBoletoDAO */
     //Metodos Padrões
    public void salvar(TextoBoleto textoBoleto);
    public void remover(TextoBoleto textoBoleto);
    public TextoBoleto atualizar(TextoBoleto textoBoleto);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    
    
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
    public TextoBoleto recuperarporId(int id);    
}
