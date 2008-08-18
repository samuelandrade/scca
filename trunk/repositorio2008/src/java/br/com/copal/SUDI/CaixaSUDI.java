package br.com.copal.SUDI;

import br.com.copal.entity.Caixa;
import br.com.copal.DAO.CaixaDAO;
import java.util.Date;
import java.util.List;

public class CaixaSUDI extends ObjectSUDI implements CaixaDAO{
    
    /** Creates a new instance of caixaSUDI */
    public CaixaSUDI() {
    }
    
     public void salvar(Caixa caixa) {
        super.salvar(caixa);
    }

    public Caixa atualizar(Caixa caixa) {
       return (Caixa)super.atualizar(caixa);
    }

    public void remover(Caixa caixa) {
        super.remover(caixa);
    }

    public List recuperarTodos() {
        List ls =  super.namedQueryListNone("Caixa.recuperarTodos"); 
        return ls;
    }

    public Caixa recuperarPorId(Integer idcaixa) {
        return (Caixa)super.namedQueryObjectOne("Caixa.recuperarPorId","idcaixa",idcaixa);
    }

    public Caixa recuperarPorData(Date data_caixa) {
        return (Caixa)super.namedQueryObjectOne("Caixa.recuperarPorData","data_caixa",data_caixa);
    }
    
    public Integer recuperarUltimoCaixa(){
       List ls = super.namedQueryListNone("Caixa.recuperarUltimoCaixa");
       return (Integer)ls.get(0);
    }
    
}
