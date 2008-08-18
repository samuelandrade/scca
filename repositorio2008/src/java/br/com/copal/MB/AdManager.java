package br.com.copal.MB;

import br.com.copal.DAO.AdDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.FN.AdFN;
import br.com.copal.entity.Ad;
import br.com.copal.entity.Avon;
import br.com.copal.SUDI.AvonSUDI;
import br.com.copal.util.FacesMessages;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import java.util.Calendar;
import java.util.Date;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jeyson
 */

public class AdManager {
    
    private Ad ad;
    private DataModel adModel;
    private AdDAO aDAO;
    private AdFN aFN;
    private Avon avonSession;
    private FacesMessages msg;
    private HttpSession session;
    
    //Metodo Construtor
      
    public AdManager() {
        setAd(new Ad());
        setADAO(FactoryDAO.criarAdDAO());
        this.setAFN(new AdFN(this));
    }
    
    public AdManager(AdFN adFN){
        setAd(new Ad());
        setADAO(FactoryDAO.criarAdDAO());
        this.setAFN(adFN);
    }
    
    //Metodos Sets e Gets

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
    
    public AdDAO getADAO() {
        return aDAO;
    }

    public void setADAO(AdDAO aDAO) {
        this.aDAO = aDAO;
    }
    
    public AdFN getAFN() {
        return aFN;
    }

    public void setAFN(AdFN aFN) {
        this.aFN = aFN;
    }
    
    // Metodos de Session
    
    public Avon getAvonSession(){
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);         
        avonSession = (Avon) session.getAttribute("Avon"); 
        return avonSession;
    }
       
    // Metodos de Model 
    
    public DataModel getListarAd() {   
        List lista = getADAO().recuperarTodos();
        for (int i = 0; i < lista.size(); i++){
            this.getAFN().atualizarSaldo((Ad) lista.get(i));
        }
        setAdModel(new ListDataModel(lista));
        return getAdModel();
    }
    
     public DataModel getListarAdPorAviso() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);         
        setAvonSession((Avon) session.getAttribute("Avon")); 
        List lista = getADAO().recuperarPorAviso(getAvonSession().getAviso());
        for (int i = 0; i < lista.size(); i++){
            this.getAFN().atualizarSaldo((Ad) lista.get(i));
        }
        setAdModel(new ListDataModel(lista));
        return getAdModel();
     }
    
    //Metodos de DAO
    
    public String criarAd(){
        setAd(new Ad());
        return "criarAd";
    }
    
    public String criarPagamento() {
        setAd((Ad) getAdModel().getRowData());
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);   
        //session.setAttribute("Cobrador",ad.getAvon().getCobrador());
        session.setAttribute("Ad", getAd());       
        return "criarPagamento";
     }
    
    public String salvarAd(){
        getAd().setAvon(this.getAvonSession());
        getADAO().salvar(getAd());
        this.setAd(new Ad());  
        getMsg().addSuccessMessage("Ad salvo com sucesso.");     
        return "listarAd";
    }
   
     public String editarAd(){
        setAd((Ad) getAdModel().getRowData());
        return "editarAd";
     }
     
     public String atualizarAd() {
        setAd(getADAO().atualizar(getAd()));
        getMsg().addSuccessMessage("Ad atualizado com sucesso.");        
        return "listarAd";
     }
     
     public String excluirAd() {
        Ad a = (Ad) getAdModel().getRowData();
        getADAO().remover(a);
        getMsg().addSuccessMessage("Ad excluido com sucesso.");
        return "listarAd";
     } 

    public DataModel getAdModel() {
        return adModel;
    }
     
     public String atualizarSaldoAd(){
         setAd((Ad) getAdModel().getRowData());
         //this.getAFN().atualizar();
         return "atualizado";
     }
     
     public String pagarAd(){
         getSession().setAttribute("adPagto", getAd());
         return "cadastrarPagamento";
     }

    public void setAdModel(DataModel adModel) {
        this.adModel = adModel;
    }

    public void setAvonSession(Avon avonSession) {
        this.avonSession = avonSession;
    }

    public FacesMessages getMsg() {
        return msg;
    }

    public void setMsg(FacesMessages msg) {
        this.msg = msg;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
