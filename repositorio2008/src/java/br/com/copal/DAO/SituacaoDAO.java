package br.com.copal.DAO;

import br.com.copal.entity.Situacao;
import java.util.List;

/**
 *
 * @author Jean
 */
public interface SituacaoDAO {
    
    //Metodos Padrões
    public void salvar(Situacao situacao);
    public void remover(Situacao situacao);
    public Situacao atualizar(Situacao situacao);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    
    
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
    public Situacao recuperarporId(int Id);
    
    
}
