package br.com.copal.SUDI;

import br.com.copal.DAO.CaixaItemDAO;
import br.com.copal.entity.CaixaItem;
import br.com.copal.entity.Pagamento;
import java.util.List;

/**
 *
 * @author Jeyson
 */
public class CaixaItemSUDI extends ObjectSUDI implements CaixaItemDAO{
    
    /** Creates a new instance of CaixaItemSUDI */
    public CaixaItemSUDI() {
    }
    
    public void salvar(CaixaItem caixaitem) {
        super.salvar(caixaitem);
    }
    
    public CaixaItem atualizar(CaixaItem caixaitem) {
        return (CaixaItem)super.atualizar(caixaitem);
    }
    
    public void remover(CaixaItem caixaitem) {
        super.remover(caixaitem);
    }
    
    public List recuperarTodos() {
        List ls =  super.namedQueryListNone("CaixaItem.recuperarTodos");
        return ls;
    }
    
    public List recuperarPorIdCaixa(Integer caixaid) {
        return super.namedQueryListOne("CaixaItem.recuperarPorIdCaixa","idcaixaitensP",caixaid);
    }
    
     public List recuperarPorTipo(boolean entsai) {
        return super.namedQueryListOne("CaixaItem.recuperarPorTipo","entsai",entsai);
    }
    
    public CaixaItem recuperarPorId(Integer caixaitemid){
        return (CaixaItem) super.namedQueryListOne("CaixaItem.recuperarPorId","idcaixa",caixaitemid);
    }
    
    public double recuperarSomaCheque(Integer Id){
        //Tratamento de Erro Contra nullpoint Exception
        try{
            List ls = super.namedQueryListOne("CaixaItem.recuperarSomaCheque", "idcaixa", Id);
            double i = Double.parseDouble(ls.get(0).toString());
            return i;
        } catch(Exception e){
            return 0.00;
        }
    }
    
    public double recuperarSomaDinheiro(Integer Id){
        //Tratamento de Erro Contra nullpoint Exception
        try{
            List ls = super.namedQueryListOne("CaixaItem.recuperarSomaDinheiro", "idcaixa", Id);
            double i = Double.parseDouble(ls.get(0).toString());
            return i;
        } catch(Exception e){
            return 0.00;
        }
    }
    
     public double recuperarSomaChequeTipo(Integer Id, boolean entsai){
        //Tratamento de Erro Contra nullpoint Exception
        try{
            List ls = super.namedQueryListTwo("CaixaItem.recuperarSomaChequeTipo","idcaixa",Id,"entsai",entsai);
            double i = Double.parseDouble(ls.get(0).toString());
            return i;
        } catch(Exception e){
            return 0.00;
        }
    }
     
     public double recuperarSomaDinheiroTipo(Integer Id, boolean entsai){
        //Tratamento de Erro Contra nullpoint Exception
        try{
            List ls = super.namedQueryListTwo("CaixaItem.recuperarSomaDinheiroTipo","idcaixa",Id,"entsai",entsai);
            double i = Double.parseDouble(ls.get(0).toString());
            return i;
        } catch(Exception e){
            return 0.00;
        }
    }
     
     public CaixaItem recuperarPeloPagamento(Pagamento Pg){
        return (CaixaItem) super.namedQueryObjectOne("CaixaItem.recuperarPeloPagamento","pagamento",Pg);
     }
}
