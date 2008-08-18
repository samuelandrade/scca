/*
 * InstrucaoBoleto.java
 *
 * Created on 28 de Dezembro de 2008, 15:01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.MB;

import br.com.copal.DAO.FactoryDAO;
import br.com.copal.DAO.InstrucaoBoletoDAO;
import br.com.copal.DAO.TemplateBoletoDAO;
import br.com.copal.entity.InstrucaoBoleto;
import br.com.copal.entity.TemplateBoleto;
import br.com.copal.util.FacesMessages;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
public class InstrucaoBoletoManager {
    
    /** Creates a new instance of InstrucaoBoleto */
    private DataModel IBModel;
    private InstrucaoBoletoDAO IBDAO;
    private InstrucaoBoleto instrucaoBoleto;
    private TemplateBoletoDAO TBDAO;
    private TemplateBoleto tbSession;
    private HttpSession session;
    private FacesMessages msg;
    
    /** Creates a new instance of InstrucaoBoletoManager */
   
    public InstrucaoBoletoManager() {
        this.setIBDAO(FactoryDAO.criarInstrucaoBoletoDAO());
        this.setInstrucaoBoleto(new InstrucaoBoleto());
        FacesContext context = FacesContext.getCurrentInstance();
        setSession((HttpSession) context.getExternalContext().getSession(true));
    }
    
   
    public DataModel getListarInstrucaoBoleto(){
        setIBModel(new ListDataModel(getIBDAO().recuperarTodos()));
        return getIBModel();
    }
    
    public String criarInstrucaoBoleto(){
        setInstrucaoBoleto(new InstrucaoBoleto());
        return "criarInstrucaoBoleto";
    }
    
     public String salvarInstrucaoBoleto(){
        getIBDAO().salvar(getInstrucaoBoleto());
        this.setInstrucaoBoleto(new InstrucaoBoleto());
        return "listarInstrucaoBoleto";
    }
    
    public String editarInstrucaoBoleto(){
        setInstrucaoBoleto((InstrucaoBoleto) getIBModel().getRowData());
        return "editarInstrucaoBoleto";
    }
    
    public String atualizarInstrucaoBoleto() {
        setInstrucaoBoleto(getIBDAO().atualizar(getInstrucaoBoleto()));
        getMsg().addSuccessMessage("InstrucaoBoleto atualizado com sucesso.");
        return "listarInstrucaoBoleto";
    }
    
   public String selecionarInstrucaoBoleto(){
        //setADAO(FactoryDAO.criarAvonDAO());
        setTBDAO(FactoryDAO.criarTemplateBoletoDAO());
        //InstrucaoBoleto instrucaoB = ;
        //List<InstrucaoBoleto> listInstrucaoB = new ArrayList();
        //List<TemplateBoleto> listTemplateB = new ArrayList();
        setTbSession((TemplateBoleto) getSession().getAttribute("TemplateBoleto"));
        //listInstrucaoB.add(instrucaoB);
        //listTemplateB.add(getTbSession());
        getTbSession().addInstrucao((InstrucaoBoleto) getIBModel().getRowData());
        //instrucaoB.setTemplatesBoleto(listTemplateB);
        getTBDAO().atualizar(getTbSession());
        return "editarTemplateBoleto";
    }
    
    public String excluirInstrucaoBoleto() {
        InstrucaoBoleto ib = (InstrucaoBoleto) getIBModel().getRowData();        
        getIBDAO().remover(ib);
        getMsg().addSuccessMessage("Instrucao Boleto excluido com sucesso.");
        return "listarInstrucaoBoleto";
    }

    public DataModel getIBModel() {
        return IBModel;
    }

    public void setIBModel(DataModel IBModel) {
        this.IBModel = IBModel;
    }

    public InstrucaoBoletoDAO getIBDAO() {
        return IBDAO;
    }

    public void setIBDAO(InstrucaoBoletoDAO IBDAO) {
        this.IBDAO = IBDAO;
    }

    public InstrucaoBoleto getInstrucaoBoleto() {
        return instrucaoBoleto;
    }

    public void setInstrucaoBoleto(InstrucaoBoleto instrucaoBoleto) {
        this.instrucaoBoleto = instrucaoBoleto;
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

    public TemplateBoletoDAO getTBDAO() {
        return TBDAO;
    }

    public void setTBDAO(TemplateBoletoDAO TBDAO) {
        this.TBDAO = TBDAO;
    }

    public TemplateBoleto getTbSession() {
        return tbSession;
    }

    public void setTbSession(TemplateBoleto tbSession) {
        this.tbSession = tbSession;
    }

    
}
