

package br.com.copal.MB;

import br.com.copal.FN.CaixaFN;
import br.com.copal.util.FacesMessages;
import java.util.List;
import javax.faces.context.FacesContext;
import br.com.copal.DAO.CaixaDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.entity.Caixa;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

/*
 * @author Jeyson
 */

public class CaixaManager {
    private CaixaDAO cDAO;
    private Caixa caixa;
    private CaixaFN cFN;
    private DataModel caixaModel;
    private FacesMessages msg;
    //private List<Caixa> ListaCaixa;
    
    public CaixaManager() {
        setCDAO(FactoryDAO.criarCaixaDAO());
        setCaixa(new Caixa());
        this.setCFN(new CaixaFN(this));
        cFN.criarCaixaPadrao();
    }
    
    public CaixaManager(CaixaFN caixaFN) {
        setCDAO(FactoryDAO.criarCaixaDAO());
        setCaixa(new Caixa());
        this.setCFN(caixaFN);
    }
    
    public CaixaDAO getCDAO() {
        return cDAO;
    }
    
    public void setCDAO(CaixaDAO cDAO) {
        this.cDAO = cDAO;
    }
    
    public Caixa getCaixa() {
        return caixa;
    }
    
    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
       
    //Metodos de Model
    
    public DataModel getListarCaixa() {
        caixaModel = new ListDataModel(cDAO.recuperarTodos());
        return caixaModel;
    }
    
    //Metodos de DAO
    
    public String criarCaixa(){
        setCaixa(new Caixa());
        return "criarCaixa";
    }
    
    public String criarCaixaItemSaida() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        caixa = (Caixa) session.getAttribute("Caixa");
        boolean marca = false;
        session.setAttribute("MarcadorEntSai",marca);
        return "criarCaixaItem";
    }
    
    public String criarCaixaItemEntrada() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        caixa = (Caixa) session.getAttribute("Caixa");
        boolean marca = true;
        session.setAttribute("MarcadorEntSai",marca);
        return "criarCaixaItem";
    }
    
    public String salvarCaixa(){
        Caixa caixaAnt = cDAO.recuperarPorId(cDAO.recuperarUltimoCaixa());
        caixa.setIdcaixaant(cDAO.recuperarUltimoCaixa());
        caixa.setSaldogeral_cheque(caixaAnt.getSaldogeral_cheque());
        caixa.setSaldogeral_dinheiro(caixaAnt.getSaldogeral_dinheiro());
        cDAO.salvar(caixa);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.setAttribute("Caixa", caixa);
        msg.addSuccessMessage("Caixa salvo com sucesso.");
        return "listarCaixa";
    }
    
    public String editarCaixa(){
        caixa = (Caixa) caixaModel.getRowData();
        return "editarCaixa";
    }
    
    public String atualizarCaixa() {
        caixa = cDAO.atualizar(caixa);
        msg.addSuccessMessage("Caixa atualizado com sucesso.");
        return "listarCaixa";
    }
    
    public String excluirCaixa() {
        Caixa a = (Caixa) caixaModel.getRowData();
        cDAO.remover(a);
        msg.addSuccessMessage("Caixa excluido com sucesso.");
        return "listarCaixa";
    }
    
    public String listarCaixaItemporCaixa() {
        caixa = (Caixa) caixaModel.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.setAttribute("Caixa", caixa);
        return "listarCaixaItemporCaixa";
    }

    public CaixaFN getCFN() {
        return cFN;
    }

    public void setCFN(CaixaFN cFN) {
        this.cFN = cFN;
    }
    
    public String finalizarCaixa(){
        caixa = (Caixa) caixaModel.getRowData();
        this.getCFN().finalizarCaixa();
        return "finalizarCaixa";
    }
}
