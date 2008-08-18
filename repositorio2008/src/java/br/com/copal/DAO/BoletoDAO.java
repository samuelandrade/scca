package br.com.copal.DAO;

import br.com.copal.entity.Boleto;
import java.util.List;

/**
 *
 * @author Jeyson
 */
public interface BoletoDAO {
    
    /** Creates a new instance of BoletoDAO */
     //Metodos Padrões
    public void salvar(Boleto boleto);
    public void remover(Boleto boleto);
    public Boleto atualizar(Boleto boleto);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    
    
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
    public Boleto recuperarporId(int idBoleto);    
    
}
