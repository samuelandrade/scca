package br.com.copal.DAO;

import br.com.copal.entity.Caixa;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Silva
 */

public interface CaixaDAO {
   
    //Metodos Padrões
    public void salvar(Caixa ci);
    public Caixa atualizar(Caixa ci);
    public void remover(Caixa ci);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    
    // Pesquisas de Valor
    public Integer recuperarUltimoCaixa();
    
    // Pesquisas de Objetos
    public Caixa recuperarPorId(Integer idcaixa);
    public Caixa recuperarPorData(Date datacaixa);
    
}