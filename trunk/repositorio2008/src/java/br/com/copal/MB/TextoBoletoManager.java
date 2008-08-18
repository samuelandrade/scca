/*
 * TextoBoletoManager.java
 *
 * Created on 28 de Dezembro de 2008, 14:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.MB;

import br.com.copal.DAO.FactoryDAO;
import br.com.copal.DAO.TemplateBoletoDAO;
import br.com.copal.DAO.TextoBoletoDAO;
import br.com.copal.entity.TemplateBoleto;
import br.com.copal.entity.TextoBoleto;
import br.com.copal.util.FacesMessages;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
public class TextoBoletoManager {
    private DataModel tbModel;
    private TemplateBoletoDAO TempBDAO;
    private TemplateBoleto tempbSession;
    private TextoBoletoDAO TBDAO;
    private TextoBoleto textoBoleto;
    private HttpSession session;
    private FacesMessages msg;
    
    /** Creates a new instance of TextoBoletoManager */
   
    public TextoBoletoManager() {
        this.setTBDAO(FactoryDAO.criarTextoBoletoDAO());
        this.setTextoBoleto(new TextoBoleto());
        FacesContext context = FacesContext.getCurrentInstance();
        setSession((HttpSession) context.getExternalContext().getSession(true));
    }
    
   
    public DataModel getListarTextoBoleto(){
        setTbModel(new ListDataModel(getTBDAO().recuperarTodos()));
        return getTbModel();
    }
    
    public String criarTextoBoleto(){
        setTextoBoleto(new TextoBoleto());
        return "criarTextoBoleto";
    }
    
     public String salvarTextoBoleto(){
        getTBDAO().salvar(getTextoBoleto());
        this.setTextoBoleto(new TextoBoleto());
        return "listarTextoBoleto";
    }
    
    public String editarTextoBoleto(){
        setTextoBoleto((TextoBoleto) getTbModel().getRowData());
        return "editarTextoBoleto";
    }
    
    public String atualizarTextoBoleto() {
        setTextoBoleto(getTBDAO().atualizar(getTextoBoleto()));
        getMsg().addSuccessMessage("TextoBoleto atualizado com sucesso.");
        return "listarTextoBoleto";
    }
    
   public String selecionarTextoBoleto(){
        //setADAO(FactoryDAO.criarAvonDAO());
        setTempBDAO(FactoryDAO.criarTemplateBoletoDAO());
        //InstrucaoBoleto instrucaoB = ;
        //List<InstrucaoBoleto> listInstrucaoB = new ArrayList();
        //List<TemplateBoleto> listTemplateB = new ArrayList();
        setTempbSession((TemplateBoleto) getSession().getAttribute("TemplateBoleto"));
        //listInstrucaoB.add(instrucaoB);
        //listTemplateB.add(getTbSession());
        getTempbSession().setTextoBoleto((TextoBoleto) getTbModel().getRowData());
        //instrucaoB.setTemplatesBoleto(listTemplateB);
        getTempBDAO().atualizar(getTempbSession());
        return "editarTemplateBoleto";
    }
    
    public String excluirTextoBoleto() {
        TextoBoleto tb = (TextoBoleto) getTbModel().getRowData();        
        getTBDAO().remover(tb);
        getMsg().addSuccessMessage("Texto Boleto excluido com sucesso.");
        return "listarTextoBoleto";
    }

    public DataModel getTbModel() {
        return tbModel;
    }

    public void setTbModel(DataModel tbModel) {
        this.tbModel = tbModel;
    }

    public TextoBoletoDAO getTBDAO() {
        return TBDAO;
    }

    public void setTBDAO(TextoBoletoDAO TBDAO) {
        this.TBDAO = TBDAO;
    }

    public TextoBoleto getTextoBoleto() {
        return textoBoleto;
    }

    public void setTextoBoleto(TextoBoleto textoBoleto) {
        this.textoBoleto = textoBoleto;
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

    public TemplateBoletoDAO getTempBDAO() {
        return TempBDAO;
    }

    public void setTempBDAO(TemplateBoletoDAO TempBDAO) {
        this.TempBDAO = TempBDAO;
    }

    public TemplateBoleto getTempbSession() {
        return tempbSession;
    }

    public void setTempbSession(TemplateBoleto tempbSession) {
        this.tempbSession = tempbSession;
    }

}
