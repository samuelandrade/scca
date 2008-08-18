package br.com.copal.MB;

import br.com.copal.DAO.CaixaDAO;
import br.com.copal.DAO.CaixaItemDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.DAO.PagamentoDAO;
import br.com.copal.FN.CaixaItemFN;
import br.com.copal.entity.CaixaItem;
import br.com.copal.entity.Caixa;
import br.com.copal.entity.Pagamento;
import br.com.copal.util.FacesMessages;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jeyson
 */
public class CaixaItemManager {
    private CaixaItem caixaitem;
    private DataModel caixaitemModel;
    private CaixaItemDAO cDAO;
    //private CaixaDAO caDAO;
    private CaixaItemFN cFN;
    //private PagamentoDAO pDAO;
    private Caixa caixaSession;
    private FacesMessages msg;
    //private List<CaixaItem> ListaCaixaItem;
    
    
    /** Creates a new instance of CaixaItemManager */
    public CaixaItemManager() {
        this.setCaixaItem(new CaixaItem());
        this.setCDAO(FactoryDAO.criarCaixaItemDAO());
        this.setCFN(new CaixaItemFN(this));
        //pDAO = FactoryDAO.criarPagamentoDAO();
    }
    
    public CaixaItemManager(CaixaItemFN caixaitemFN) {
        this.setCaixaItem(new CaixaItem());
        this.setCDAO(FactoryDAO.criarCaixaItemDAO());
        this.setCFN(caixaitemFN);
        //pDAO = FactoryDAO.criarPagamentoDAO();
    }
    
    public CaixaItem getCaixaItem() {
        return caixaitem;
    }
    
    public void setCaixaItem(CaixaItem caixaitem) {
        this.caixaitem = caixaitem;
    }
    
    public CaixaItemDAO getCDAO() {
        return cDAO;
    }
    
    public void setCDAO(CaixaItemDAO cDAO) {
        this.cDAO = cDAO;
    }
    
    public CaixaItemFN getCFN() {
        return cFN;
    }

    public void setCFN(CaixaItemFN cFN) {
        this.cFN = cFN;
    }
    
    // Metodos de Model
    
    public DataModel getListarCaixaItem() {
        caixaitemModel = new ListDataModel(cDAO.recuperarTodos());
        return caixaitemModel;
    }
    
    public DataModel getListarCaixaItemporCaixa() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        caixaSession = (Caixa) session.getAttribute("Caixa");
        caixaitemModel = new ListDataModel(cDAO.recuperarPorIdCaixa(caixaSession.getIdcaixa()));
        return caixaitemModel;
    }
    
    public DataModel getListaDeEntradas(){
        caixaitemModel = new ListDataModel(cDAO.recuperarPorTipo(true));
        return caixaitemModel;
    }
    
    // Metodos de DAO
    
    public String criarCaixaItem(){
        setCaixaItem(new CaixaItem());
        return "criarCaixaItem";
    }
    
    public String salvarCaixaItem(){
        /*caDAO = FactoryDAO.criarCaixaDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        caixaSession = (Caixa) session.getAttribute("Caixa");
        caixaitem.setCaixa(caixaSession);
        this.calculaSaldo(caixaitem, caixaSession);
        caDAO.atualizar(caixaSession);
        cDAO.salvar(caixaitem);
        this.setCaixaItem(new CaixaItem());
        session.setAttribute("CaixaItem",caixaitem);*/
        this.getCFN().salvarCaixaItem();
        return "listarCaixaItem";
    }
    /*
    public void calculaSaldo(CaixaItem cI, Caixa cS){
        if(cI.isTipo()){
            cS.setSaldodia_cheque(cS.getSaldodia_cheque() + cI.getValorch());
            cS.setSaldodia_dinheiro(cS.getSaldodia_dinheiro() + cI.getValordin());
            cS.setSaldogeral_cheque(cS.getSaldogeral_cheque() + cI.getValorch());
            cS.setSaldogeral_dinheiro(cS.getSaldogeral_dinheiro() + cI.getValordin());
        } else{
            cS.setSaldodia_cheque(cS.getSaldodia_cheque() - cI.getValorch());
            cS.setSaldodia_dinheiro(cS.getSaldodia_dinheiro() - cI.getValordin());
            cS.setSaldogeral_cheque(cS.getSaldogeral_cheque() - cI.getValorch());
            cS.setSaldogeral_dinheiro(cS.getSaldogeral_dinheiro() - cI.getValordin());
        }
    }
    
    public void recalcularSaldo(CaixaItem cI, Caixa cS){
        caDAO = FactoryDAO.criarCaixaDAO();
        if(cI.isTipo()){
            cS.setSaldodia_cheque(cS.getSaldodia_cheque() - cI.getValorch());
            cS.setSaldodia_dinheiro(cS.getSaldodia_dinheiro() - cI.getValordin());
            cS.setSaldogeral_cheque(cS.getSaldogeral_cheque() - cI.getValorch());
            cS.setSaldogeral_dinheiro(cS.getSaldogeral_dinheiro() - cI.getValordin());
        } else{
            cS.setSaldodia_cheque(cS.getSaldodia_cheque() + cI.getValorch());
            cS.setSaldodia_dinheiro(cS.getSaldodia_dinheiro() + cI.getValordin());
            cS.setSaldogeral_cheque(cS.getSaldogeral_cheque() + cI.getValorch());
            cS.setSaldogeral_dinheiro(cS.getSaldogeral_dinheiro() + cI.getValordin());
        }
        caDAO.atualizar(cS);
    }*/
    
    public String editarCaixaItem(){
        caixaitem = (CaixaItem) caixaitemModel.getRowData();
        return "editarCaixaItem";
    }
    
    public String atualizarCaixaItem() {
        caixaitem = cDAO.atualizar(caixaitem);
        msg.addSuccessMessage("CaixaItem atualizado com sucesso.");
        return "listarCaixaItem";
    }
    
    public String excluirCaixaItem() {
        /*
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        caixaSession = (Caixa) session.getAttribute("Caixa");
        this.recalcularSaldo(c,caixaSession);
        caDAO.atualizar(caixaSession);
        if(c.getPagamento() != null){
            Pagamento p = c.getPagamento();
            c.setPagamento(null);
            cDAO.atualizar(c);
            pDAO.remover(p);
        }
        cDAO.remover(c);*/
        
        //Pega do Model o CaixaItem selecionado
        CaixaItem c = (CaixaItem) caixaitemModel.getRowData();
        //Evnai para o CaixaItemFn fazer as operaçoes nescessarias
        this.getCFN().removerCaixaItem(c);
        //Envia uma mensgem ao usaurio assim como uma string para o Sistema
        msg.addSuccessMessage("CaixaItem excluido com sucesso.");
        return "listarCaixaItem";
    } 
}
