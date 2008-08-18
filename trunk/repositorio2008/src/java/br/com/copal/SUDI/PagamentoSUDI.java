package br.com.copal.SUDI;

import br.com.copal.DAO.PagamentoDAO;
import br.com.copal.entity.Pagamento;
import java.io.DataInput;
import java.util.Date;
import java.util.List;

/*
 * @author copal
 */

public class PagamentoSUDI extends ObjectSUDI implements PagamentoDAO{
    
    /** Creates a new instance of PagamentoSUDI */
    public PagamentoSUDI() {}
    
    //Metodos Padrões
    public void salvar(Pagamento pagamento) {
        super.salvar(pagamento);
    }

    public Pagamento atualizar(Pagamento pagamento) {
        return (Pagamento)super.atualizar(pagamento);
    }

    public void remover(Pagamento pagamento) {
        super.remover(pagamento);
    }
    
    // Pesquisas de Lista
    public List recuperarTodos() {
        List ls =  super.namedQueryListNone("Pagamento.recuperarTodos"); 
        return ls;
    }
    
    // Pesquisas de Valor
  
    // Pesquisas de Objetos

    public List recuperarPorCobradorePeriodo(Date datF, Date datI,String Ncob) {
        List ls = super.namedQueryListThree("Pagamento.recuperarPorPeriodo","data_fim",datF,"data_ini",datI,"nomeCob",Ncob);
        return ls;
    }

    public List recuperarPorAd(Integer idAd) {
        List ls = super.namedQueryListOne("Pagamento.recuperarPorAd","idAd",idAd);
        return ls;
    }
    
}
