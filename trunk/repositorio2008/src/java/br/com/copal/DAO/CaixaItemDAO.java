package br.com.copal.DAO;

import br.com.copal.entity.CaixaItem;
import br.com.copal.entity.Pagamento;
import java.util.List;

/**
 *
 * @author Jeyson
 */

public interface CaixaItemDAO {
    
    // Metodos Padrões
    public void salvar(CaixaItem caixaitem);
    public void remover(CaixaItem caixaitem);
    public CaixaItem atualizar(CaixaItem caixaitem);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    public List recuperarPorIdCaixa(Integer Id);
    public List recuperarPorTipo(boolean entsai);
    
    // Pesquisas de Valor
    public double recuperarSomaCheque(Integer Id);
    public double recuperarSomaDinheiro(Integer Id);
    public double recuperarSomaChequeTipo(Integer Id, boolean entsai);
    public double recuperarSomaDinheiroTipo(Integer Id, boolean entsai);
    
    // Pesquisas de Objetos
    public CaixaItem recuperarPorId(Integer Id);
    public CaixaItem recuperarPeloPagamento(Pagamento Pg);
}
