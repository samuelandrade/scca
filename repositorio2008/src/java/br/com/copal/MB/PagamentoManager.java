/*
 * PagamentoManager.java
 *
 * Created on 9 de Janeiro de 2008, 15:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.MB;

import br.com.copal.DAO.AdDAO;
import br.com.copal.DAO.CaixaItemDAO;
import br.com.copal.DAO.PagamentoDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.FN.PagamentoFN;
import br.com.copal.entity.Caixa;
import br.com.copal.entity.CaixaItem;
import br.com.copal.DAO.CaixaDAO;
import br.com.copal.entity.Ad;
import br.com.copal.entity.Cobrador;

import br.com.copal.entity.Pagamento;
import br.com.copal.util.FacesMessages;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

/*
 * @author copal
 */

public class PagamentoManager {
    private Pagamento pagamento;
    private Cobrador cobradorSession;
    private CaixaDAO cDAO;
    private CaixaItemDAO caDAO;
    private PagamentoFN pFN;
    private PagamentoDAO pDAO;
    private CaixaItemManager ciMB;
    private AdDAO aDAO;
    private Caixa caixaSession;
    private DataModel pagamentoModel;
    private FacesMessages msg;
    private Ad adSession;
    private String idAd;
    private String nomeCobrador;
    private Date dataIni;
    private Date dataFim;
    private List<Pagamento> pagamentos;
    private String dataTemp;
    private Double totalPago;
    private Double totalCom;
    private String urlRelatorio;
    
    
    /** Creates a new instance of PagamentoManager */
    public PagamentoManager() {
        setPagamento(new Pagamento());
        setPDAO(FactoryDAO.criarPagamentoDAO());
        this.setPFN(new PagamentoFN(this));
        setCDAO(FactoryDAO.criarCaixaDAO());
        setCaDAO(FactoryDAO.criarCaixaItemDAO());
        setADAO(FactoryDAO.criarAdDAO());
        setCiMB(new CaixaItemManager());
        setIdAd("0");
    }
    
    public PagamentoManager(PagamentoFN pagamentoFN) {
        setPagamento(new Pagamento());
        setPDAO(FactoryDAO.criarPagamentoDAO());
        this.setPFN(pagamentoFN);
        setCDAO(FactoryDAO.criarCaixaDAO());
        setCaDAO(FactoryDAO.criarCaixaItemDAO());
        setADAO(FactoryDAO.criarAdDAO());
        setCiMB(new CaixaItemManager());
    }
    
    public Pagamento getPagamento() {
        return pagamento;
    }
    
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    
    public PagamentoDAO getPDAO() {
        return pDAO;
    }
    
    public void setPDAO(PagamentoDAO pDAO) {
        this.pDAO = pDAO;
    }
    
    public Caixa getCaixaSession(){
        caixaSession = getCDAO().recuperarPorId(getCDAO().recuperarUltimoCaixa());
        return caixaSession;
    }
    
    public Ad getAdSession() {
        return adSession;
    }
    
    public void setAdSession(Ad adSession) {
        this.adSession = adSession;
    }
    
    public PagamentoFN getPFN() {
        return pFN;
    }
    
    public void setPFN(PagamentoFN pFN) {
        this.pFN = pFN;
    }
    
     public String getDataTemp() {
        return dataTemp;
    }

    public void setDataTemp(String dataTemp) {
        this.dataTemp = dataTemp;
    }
    
    //Metodos de Modelo
    
    public DataModel getListarPagamentoPorAd(){
        Integer idN = Integer.valueOf(getIdAd()).intValue();
        setPagamentos(getPDAO().recuperarPorAd(idN));
        setPagamentoModel(new ListDataModel(getPagamentos()));
        return getPagamentoModel();
    }
    
    public DataModel getListarPagamento() {
        setPagamentoModel(new ListDataModel(getPDAO().recuperarTodos()));
        return getPagamentoModel();
    }
    
    public DataModel getListarPagamentoPorCobradorePeriodo(){
        setUrlRelatorio("&");
        setPagamentos(getPDAO().recuperarPorCobradorePeriodo(getDataFim(),getDataIni(),getNomeCobrador()));
        Iterator i = getPagamentos().iterator();  
        setTotalPago(0.0);
        setTotalCom(0.0);
        while(i.hasNext()){
            Pagamento pg = (Pagamento) i.next();
            setTotalPago(getTotalPago() + pg.getValor());
            setTotalCom(getTotalCom() + pg.getComissaocobrador());
        }
        setPagamentoModel(new ListDataModel(getPagamentos()));
        return getPagamentoModel();
    }
    //Metodos de DAO
    
   /* public String  criarPagamento(){
        setPagamento(new Pagamento());         
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        setAdSession((Ad) session.getAttribute("adPagto"));
        return "criarPagamento";
    }*/
    
    public String salvarPagamento(){
        //FacesContext context = FacesContext.getCurrentInstance();  
        //HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        //setAdSession((Ad) session.getAttribute("adPagto"));
        getPagamento().setAd(getAdSession());
        
        //getPagamento().setDatapagamento()
        //getPDAO().salvar(getPagamento());
        
        this.getPFN().salvarPagamento();
        /*
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        CaixaItem cI = new CaixaItem();
        cI.setPagamento(getPagamento());
        cI.setValordin(getPagamento().getValor());
        cI.setTipo(true);
        cI.setCaixa(this.getCaixaSession());
        ciMB.calculaSaldo(cI,caixaSession);
        adSession = (Ad)session.getAttribute("Ad");
        adSession.setValordebitoatual(adSession.getValordebitoatual() - pagamento.getValor());
        pagamento.setAd(adSession);
        aDAO.atualizar(adSession);
        cobradorSession = (Cobrador) session.getAttribute("Cobrador");
        pagamento.setCobrador(cobradorSession);
        pagamento.setComissaocobrador((cobradorSession.getComissao() * pagamento.getValor()) / 100);
        pDAO.salvar(pagamento);
        caDAO.salvar(cI);
        cDAO.atualizar(caixaSession);
        session.setAttribute("Pagamento", pagamento);
        this.setPagamento(new Pagamento());*/
        
        return "listarPagamento";
        
    }
    
    public String editarPagamento(){
        setPagamento(getPDAO().atualizar(getPagamento()));
        getPFN().editarPagamento();
        setPagamento((Pagamento) getPagamentoModel().getRowData());
        return "editarPagamento";
    }
    
    public String atualizarPagamento() {
        setPagamento(getPDAO().atualizar(getPagamento()));
        getPFN().editarPagamento();
        getMsg().addSuccessMessage("Pagamento atualizado com sucesso.");
        return "listarPagamento";
    }
    
    public String excluirPagamento() {
        Pagamento p = (Pagamento) getPagamentoModel().getRowData();
        //CaixaItem c = getCaDAO().recuperarPeloPagamento(p);
        //c.setPagamento(null);
        //ciMB.recalcularSaldo(c,c.getCaixa());
        //getCaDAO().remover(c);
        getPFN().apagarPagamento();
        getPDAO().remover(p);
        getMsg().addSuccessMessage("Pagamento excluido com sucesso.");
        return "listarPagamento";
    }
    
    public String lancarPagamento(){
        //FacesContext context = FacesContext.getCurrentInstance();  
        //HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Integer idN = Integer.valueOf(getIdAd()).intValue();
        setAdSession((Ad) getADAO().recuperarPorAvisoDebito(idN));        
       
        setPagamento(new Pagamento());
        return "criarPagamento";
    } 

    public Cobrador getCobradorSession() {
        return cobradorSession;
    }

    public void setCobradorSession(Cobrador cobradorSession) {
        this.cobradorSession = cobradorSession;
    }

    public CaixaDAO getCDAO() {
        return cDAO;
    }

    public void setCDAO(CaixaDAO cDAO) {
        this.cDAO = cDAO;
    }

    public CaixaItemDAO getCaDAO() {
        return caDAO;
    }

    public void setCaDAO(CaixaItemDAO caDAO) {
        this.caDAO = caDAO;
    }

    public CaixaItemManager getCiMB() {
        return ciMB;
    }

    public void setCiMB(CaixaItemManager ciMB) {
        this.ciMB = ciMB;
    }

    public AdDAO getADAO() {
        return aDAO;
    }

    public void setADAO(AdDAO aDAO) {
        this.aDAO = aDAO;
    }

    public void setCaixaSession(Caixa caixaSession) {
        this.caixaSession = caixaSession;
    }

    public DataModel getPagamentoModel() {
        return pagamentoModel;
    }

    public void setPagamentoModel(DataModel pagamentoModel) {
        this.pagamentoModel = pagamentoModel;
    }

    public FacesMessages getMsg() {
        return msg;
    }

    public void setMsg(FacesMessages msg) {
        this.msg = msg;
    }

    public String getIdAd() {
        return idAd;
    }

    public void setIdAd(String idAd) {
        this.idAd = idAd;
    }

    public String getNomeCobrador() {
        return nomeCobrador;
    }

    public void setNomeCobrador(String nomeCobrador) {
        this.nomeCobrador = nomeCobrador;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public List getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Double getTotalCom() {
        return totalCom;
    }

    public void setTotalCom(Double totalCom) {
        this.totalCom = totalCom;
    }

    public String getUrlRelatorio() {
        return urlRelatorio;
    }

    public void setUrlRelatorio(String urlRelatorio) {
        this.urlRelatorio = urlRelatorio;
    }
    
}
