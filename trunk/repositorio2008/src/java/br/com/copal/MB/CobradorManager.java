package br.com.copal.MB;

import br.com.copal.DAO.AvonDAO;
import br.com.copal.DAO.CobradorDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.FN.CobradorFN;
import br.com.copal.entity.Avon;
import br.com.copal.entity.Cobrador;
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

public class CobradorManager {
    private Cobrador cobrador;
    private Avon avonSession;
    private DataModel cobradorModel;
    private CobradorDAO cDAO;
    private CobradorFN cFN;
    private AvonDAO aDAO;
    private FacesMessages msg;
    private HttpSession session;
        
    /** Creates a new instance of CobradorManager */
    public CobradorManager() {
        this.setCDAO(FactoryDAO.criarCobradorDAO());
        this.setADAO(FactoryDAO.criarAvonDAO());
        this.setCobrador(new Cobrador());
        this.setCFN(new CobradorFN(this));
        this.getCFN().criarCobradorPadrao();
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(true);
    }
    
     public CobradorManager(CobradorFN cobradorFN) {
        this.setCDAO(FactoryDAO.criarCobradorDAO());
        this.setADAO(FactoryDAO.criarAvonDAO());
        this.setCobrador(new Cobrador());
        this.setCFN(cobradorFN);
        FacesContext context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(true);
    }
    
    public Cobrador getCobrador() {
        return cobrador;
    }
    
    public void setCobrador(Cobrador cobrador) {
        this.cobrador = cobrador;
    }
       
    public CobradorDAO getCDAO() {
        return cDAO;
    }
    
    public void setCDAO(CobradorDAO cDAO) {
        this.cDAO = cDAO;
    }
    
    public AvonDAO getADAO() {
        return aDAO;
    }
    
    public void setADAO(AvonDAO cDAO) {
        this.aDAO = aDAO;
    }
    
     public CobradorFN getCFN() {
        return cFN;
    }

    public void setCFN(CobradorFN cFN) {
        this.cFN = cFN;
    }
    
    //Metodos de Model
    
    public DataModel getListarCobrador(){
        cobradorModel = new ListDataModel(cDAO.recuperarTodos());
        return cobradorModel;
    }
    
    //Metodos de DAO
    
    public String criarCobrador(){
        setCobrador(new Cobrador());
        return "criarCobrador";
    }
    
    public String salvarCobrador(){
        cDAO.salvar(cobrador);
        this.setCobrador(new Cobrador());
        return "listarCobrador";
    }
    
    public String editarCobrador(){
        cobrador = (Cobrador) cobradorModel.getRowData();
        return "editarCobrador";
    }
    
    public String atualizarCobrador() {
        cobrador = cDAO.atualizar(cobrador);
        msg.addSuccessMessage("Cobrador atualizado com sucesso.");
        return "listarCobrador";
    }

    public String excluirCobrador() {
        Cobrador c = (Cobrador) cobradorModel.getRowData();
        Cobrador agencia = cDAO.recuperarporId(1);
        List<Avon> ls = aDAO.recuperarPorCobrador(c.getNome());
        if(!ls.isEmpty()){
        for (int i = 0; i < ls.size(); i++){
            List<Avon> aviso = aDAO.recuperarPorAviso(ls.get(i).getAviso());
            aviso.get(0).setCobrador(agencia);
            aDAO.atualizar(aviso.get(0));
        }}
        cDAO.remover(c);
        msg.addSuccessMessage("Cobrador excluido com sucesso.");
        return "listarCobrador";
    }
    
    public String selecionarCobrador(){
        aDAO = FactoryDAO.criarAvonDAO();
        Cobrador c = (Cobrador) cobradorModel.getRowData();
        avonSession = (Avon) session.getAttribute("Avon");
        avonSession.setCobrador(c);
        aDAO.atualizar(avonSession);
        return "listarAvon";
    }
    
    public String listarAvonporCobrador() {
        Cobrador c = (Cobrador) cobradorModel.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.setAttribute("Cobrador", c);
        return "listarAvonporCobrador";
    }

   
    
}
