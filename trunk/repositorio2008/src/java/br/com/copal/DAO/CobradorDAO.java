package br.com.copal.DAO;

import br.com.copal.entity.Cobrador;
import java.util.List;

/**
 *
 * @author Jean
 */
public interface CobradorDAO {
    
    //Metodos Padrões
    public void salvar(Cobrador cobrador);
    public void remover(Cobrador cobrador);
    public Cobrador atualizar(Cobrador cobrador);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    
    
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
    public Cobrador recuperarporId(int Id);
    
}
