/*
 * TemplateBoletoManager.java
 *
 * Created on 29 de Julho de 2008, 14:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.MB;

import br.com.copal.DAO.FactoryDAO;
import br.com.copal.DAO.TemplateBoletoDAO;
import br.com.copal.entity.Boleto;
import br.com.copal.entity.InstrucaoBoleto;
import br.com.copal.entity.TemplateBoleto;
import br.com.copal.util.FacesMessages;
import java.util.Iterator;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
public class TemplateBoletoManager {
    
    private DataModel TBModel;
    private TemplateBoletoDAO TBDAO;
    private TemplateBoleto templateBoleto;
    private HttpSession session;
    private FacesMessages msg;
    private Integer instrucaoTid;
    private Boleto boletoSession;
    
    /** Creates a new instance of TemplateBoletoManager */
    public TemplateBoletoManager() {
        this.setTBDAO(FactoryDAO.criarTemplateBoletoDAO());
        this.setTemplateBoleto(new TemplateBoleto());
        FacesContext context = FacesContext.getCurrentInstance();
        setSession((HttpSession) context.getExternalContext().getSession(true));
    }
    
    
    public DataModel getListarTemplateBoleto(){
        setTBModel(new ListDataModel(getTBDAO().recuperarTodos()));
        return getTBModel();
    }
    
    public String criarTemplateBoleto(){
        setTemplateBoleto(new TemplateBoleto());
        return "criarTemplateBoleto";
    }
    
     public String salvarTemplateBoleto(){
        getTBDAO().salvar(getTemplateBoleto());
        this.setTemplateBoleto(new TemplateBoleto());
        return "listarTemplateBoleto";
    }
    
    public String editarTemplateBoleto(){
        setTemplateBoleto((TemplateBoleto) getTBModel().getRowData());
        getSession().setAttribute("TemplateBoleto", getTemplateBoleto());
        return "editarTemplateBoleto";
    }
    
    public String atualizarTemplateBoleto() {
        setTemplateBoleto(getTBDAO().atualizar(getTemplateBoleto()));
        getMsg().addSuccessMessage("TemplateBoleto atualizado com sucesso.");
        return "listarTemplateBoleto";
    }
    
   public String selecionarTemplateBoleto(){
        
        //setADAO(FactoryDAO.criarAvonDAO());
        //setTBDAO(FactoryDAO.criarTemplateBoletoDAO());
        //InstrucaoBoleto instrucaoB = ;
        //List<InstrucaoBoleto> listInstrucaoB = new ArrayList();
        //List<TemplateBoleto> listTemplateB = new ArrayList();
        //setBoletoSession((Boleto) getSession().getAttribute("Boleto"));
        //listInstrucaoB.add(instrucaoB);
        //listTemplateB.add(getTbSession());
        setTemplateBoleto((TemplateBoleto) getTBModel().getRowData());
        getSession().setAttribute("TemplateBoleto", getTemplateBoleto());
        //instrucaoB.setTemplatesBoleto(listTemplateB);
        //getTempBDAO().atualizar(getTempbSession());
        //getSession().setAttribute("Boleto", getBoletoSession());
        return "criarBoleto";
    }
    
    
    public String excluirIntrucaoTemplateBoleto(){
        //InstrucaoBoleto insRem = new InstrucaoBoleto();
        Iterator i = getTemplateBoleto().getInstrucoes().iterator();
        //Integer idRem = (Integer) getSession().getAttribute("instrucaoTemplateId");
        System.out.println(instrucaoTid);
        while(i.hasNext()){
            InstrucaoBoleto ib = (InstrucaoBoleto)i.next();
            if(ib.getId().equals(getInstrucaoTid())){
                ib.removeTemplate(getTemplateBoleto());
                getTemplateBoleto().removeInstrucao(ib);                
                getTBDAO().atualizar(getTemplateBoleto());
                break;
            }
        }         
        
        return "editarTemplateBoleto";
    }
    
    public String excluirTemplateBoleto() {
        TemplateBoleto tb = (TemplateBoleto) getTBModel().getRowData();        
        getTBDAO().remover(tb);
        getMsg().addSuccessMessage("Template Boleto excluido com sucesso.");
        return "listarTemplateBoleto";
    }

    public DataModel getTBModel() {
        return TBModel;
    }

    public void setTBModel(DataModel TBModel) {
        this.TBModel = TBModel;
    }

    public TemplateBoletoDAO getTBDAO() {
        return TBDAO;
    }

    public void setTBDAO(TemplateBoletoDAO TBDAO) {
        this.TBDAO = TBDAO;
    }

    public TemplateBoleto getTemplateBoleto() {
        return templateBoleto;
    }

    public void setTemplateBoleto(TemplateBoleto templateBoleto) {
        this.templateBoleto = templateBoleto;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public FacesMessages getMsg() {
        return msg;
    }

    public void setMsg(FacesMessages msg) {
        this.msg = msg;
    }

    public Integer getInstrucaoTid() {
        return instrucaoTid;
    }

    public void setInstrucaoTid(Integer instrucaoTid) {
        this.instrucaoTid = instrucaoTid;
    }

    private void setBoletoSession(Boleto boletoSession) {
        this.boletoSession = boletoSession;
    }
    
    private Boleto getBoletoSession() {
       return this.boletoSession;
    }
    
}
