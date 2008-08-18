/*
 * AvonManager.java
 *
 * Created on 4 de Dezembro de 2007, 16:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.MB;

import br.com.copal.DAO.AdDAO;
import br.com.copal.DAO.AvonDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.FN.AvonFN;
import br.com.copal.entity.Ad;
import br.com.copal.entity.Avon;
import br.com.copal.entity.Cobrador;
import br.com.copal.util.FacesMessages;
import br.com.copal.util.FileUploadServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author copal
 */

public class AvonManager {
    
    private Avon avon;
    private List<Ad> listaAdManager;
    private DataModel avonModel;
    private DataModel cobradorModel;
    private AvonDAO aDAO;
    private FacesMessages msg;
    private Cobrador cobradorSession;
    private HttpSession session;
    private AvonFN aFN;
    private int setor;
    private int aviso;
    private String cidade;
    private String nome;
    private int vTotal = 0;
    private String urlRelatorio;
    private String nomeCobrador;
    private String cidadeAviso;
    private String bairroAviso;
    private String negociadorAviso;
    private String situacaoAviso;
    private String marcaAviso;
    private String setorAviso;
    private String statusA;
    private List<Avon> listaAvon;
    private boolean marcado;
    
    /** Creates a new instance of AvonManager */
    public AvonManager() {
        setAvon(new Avon());
        setADAO(FactoryDAO.criarAvonDAO());
        setAFN(new AvonFN(this));
        FacesContext context = FacesContext.getCurrentInstance();
        setSession((HttpSession) context.getExternalContext().getSession(true));
    }
    
    //Contrutor chamado pelo AvonFN
    public AvonManager(AvonFN avonFN){
        this.setAFN(avonFN);
        this.setAvon(new Avon());
        this.setADAO(FactoryDAO.criarAvonDAO());
    }
    
    public Avon getAvon() {
        return avon;
    }
    
    public void setAvon(Avon avon) {
        this.avon = avon;
    }
    
    public AvonDAO getADAO() {
        return aDAO;
    }
    
    public void setADAO(AvonDAO aDAO) {
        this.aDAO = aDAO;
    }
    
    public AvonFN getAFN() {
        return aFN;
    }
    
    public void setAFN(AvonFN aFN) {
        this.aFN = aFN;
    }
    
    // Metodos de Model
    
    public DataModel getListarAvon() {
        setAvonModel(new ListDataModel(getADAO().recuperarTodos()));
        return getAvonModel();
    }
    
    public DataModel getListarAvonPorCobrador() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        //cobradorSession = (Cobrador) session.getAttribute("Cobrador");
        setAvonModel(new ListDataModel(getADAO().recuperarPorCobrador(getNome())));
        return getAvonModel();
    }
    
    public DataModel getListarAvonPorSetor() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        //int setor = (Integer) session.getAttribute("Setor");
        setAvonModel(new ListDataModel(getADAO().recuperarPorSetor(getSetor())));
        return getAvonModel();
    }
    
    public DataModel getListarAvonPorCidade() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        //String cidade = (String) session.getAttribute("Cidade");
        
        setAvonModel(new ListDataModel(getADAO().recuperarPorCidade(getCidade())));
        return getAvonModel();
    }
    
    public DataModel getListarAvonPorNome() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        //String cidade = (String) session.getAttribute("Cidade");
        setAvonModel(new ListDataModel(getADAO().recuperarPorNome(getNome())));
        return getAvonModel();
    }
    
    public DataModel getListarAvonPorAviso() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        //String cidade = (String) session.getAttribute("Cidade");
        setAvonModel(new ListDataModel(getADAO().recuperarPorAviso(getAviso())));
        return getAvonModel();
    }
    
    // Metodos do DAO
    
    public String criarAvon(){
        setAvon(new Avon());
        return "criarAvon";
    }
    
    public String criarAd() {
        setAvon((Avon) getAvonModel().getRowData());
        getSession().setAttribute("Avon", getAvon());
        return "criarAd";
    }
    
    public String salvarAvon(){
        getADAO().salvar(getAvon());
        getSession().setAttribute("Avon", getAvon());
        getMsg().addSuccessMessage("Avon salvo com sucesso.");
        return "criarAd";
    }
    
    public String editarAvon(){
        //avon = (Avon) avonModel.getRowData();
        return "editarAvon";
    }
    
    public String atualizarAvon() {
        
        setAvon(getADAO().atualizar(getAvon()));
        getMsg().addSuccessMessage("Avon atualizado com sucesso.");
        return "listarAvon";
    }
    
    public String excluirAvon() {
        //Avon a = (Avon) avonModel.getRowData();
        //avon = (Avon) aDAO.recuperarPorAvisoObjeto(avon.getAviso());
        getAvon().setSituacao("I");
        getADAO().atualizar(getAvon());
        getMsg().addSuccessMessage("Avon excluido com sucesso.");
        return "listarAvon";
    }
    
    public String listarAdporAviso() {
        setAvon((Avon) getAvonModel().getRowData());
        getSession().setAttribute("Avon", getAvon());
        return "listarAdporAviso";
    }
    
    public String visualizarAvon() {
        //AdDAO adDAO = null;
        
        setAvon((Avon) getAvonModel().getRowData());
        Avon avon2 = (Avon) getADAO().recuperarPorAviso(getAvon().getAviso()).get(0);
        //avon2.setAdLista(adDAO.recuperarPorAviso(avon2.getAviso()));
        setAvon(avon2);
        
        setListaAdManager(avon2.getAdLista());
        getAvon().setAdLista(getListaAdManager());
        this.getAFN().atualizarAdporAvon();
        //Iterator i = avon2.getAdLista().iterator();
       /* while(i.hasNext()){
            Ad a = (Ad) i.next();
            Float b = a.getValordebito();
            int c = b.intValue();
            vTotal = getVTotal() + c;
            setVTotal(getVTotal() + a.getValordebito());
        }*/
        
        getSession().setAttribute("Avon", getAvon());
        setUrlRelatorio("&");
        getUrlRelatorio().concat("Jean");
        return "visualizarAvon";
    }
    
    
    public String selecionarCobrador() {
        setAvon((Avon) getAvonModel().getRowData());
        getSession().setAttribute("Avon", getAvon());
        return "selecionarCobrador";
    }
    
    public void associarCobrador(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        getAvon().setCobrador((Cobrador)getCobradorModel().getRowData());
    }
    
    public String tratarRenessa() throws ServletException, IOException{
        getAFN().salvarRemessa();
        //ArrayList<ArrayList> linhasAvon = FileUploadServlet.linhaStatic;
        return "uploadConcluido";
    }
    
    public String salvarVariosStatus(){
        setListaAvon((List<Avon>) getAvonModel().getWrappedData());
        Iterator i = getListaAvon().iterator();
        while(i.hasNext()){
            Avon avon = (Avon)i.next();
            if(avon.getStatusaviso()!= getStatusA()){
                avon = (Avon)getADAO().atualizar(avon);
                this.getAFN().atualizarAdporAvon();
            }
        }
        return "mudarStatusAvon";
    }
    
    
    
    
    public String salvarStatus(){
        setAvon((Avon) getAvonModel().getRowData());
        //setIraja((Iraja) getIDAO().recuperarPorAviso(getIraja().getAviso()).get(0));
        setAvon((Avon) getADAO().atualizar(getAvon()));
        this.getAFN().atualizarAdporAvon();
        //System.out.println("sdfadasdasdasdasd");
        return "mudarStatusAvon";
    }
    
    public DataModel getListarPorStatusCobrador(){
        setUrlRelatorio("&");
        setAvonModel(new ListDataModel(getADAO().recuperarPorStatusCobrador(getNomeCobrador(),getStatusA())));
        //setIrajaModel(new ListDataModel(getIrajas()));
        return getAvonModel();
    }
    
    
   /* public DataModel getListarPorFiltro(){
        setAvonModel(new ListDataModel(getADAO().recuperarPorFiltros(getCidadeAviso(),getBairroAviso(),getSetorAviso(),getNegociadorAviso(),getStatusA(),get)));
        return getAvonModel();
    }*/
    
    public int getSetor() {
        return setor;
    }
    
    public void setSetor(int setor) {
        this.setor = setor;
    }
    
    public int getAviso() {
        return aviso;
    }
    
    public void setAviso(int aviso) {
        this.aviso = aviso;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String concatenarAtributo(String concatenado){
        return concatenado.concat("%");
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Ad> getListaAdManager() {
        return listaAdManager;
    }
    
    public void setListaAdManager(List<Ad> listaAdManager) {
        this.listaAdManager = listaAdManager;
    }
    
    public DataModel getAvonModel() {
        return avonModel;
    }
    
    public void setAvonModel(DataModel avonModel) {
        this.avonModel = avonModel;
    }
    
    public DataModel getCobradorModel() {
        return cobradorModel;
    }
    
    public void setCobradorModel(DataModel cobradorModel) {
        this.cobradorModel = cobradorModel;
    }
    
    public FacesMessages getMsg() {
        return msg;
    }
    
    public void setMsg(FacesMessages msg) {
        this.msg = msg;
    }
    
    public Cobrador getCobradorSession() {
        return cobradorSession;
    }
    
    public void setCobradorSession(Cobrador cobradorSession) {
        this.cobradorSession = cobradorSession;
    }
    
    public HttpSession getSession() {
        return session;
    }
    
    public void setSession(HttpSession session) {
        this.session = session;
    }
    
    public int getVTotal() {
        return vTotal;
    }
    
    public void setVTotal(int vTotal) {
        this.vTotal = vTotal;
    }
    
    public String getUrlRelatorio() {
        return urlRelatorio;
    }
    
    public void setUrlRelatorio(String urlRelatorio) {
        this.urlRelatorio = urlRelatorio;
    }
    
    public String getNomeCobrador() {
        return nomeCobrador;
    }
    
    public void setNomeCobrador(String nomeCobrador) {
        this.nomeCobrador = nomeCobrador;
    }
    
    public String getStatusA() {
        return statusA;
    }
    
    public void setStatusA(String statusA) {
        this.statusA = statusA;
    }
    
    public List<Avon> getListaAvon() {
        return listaAvon;
    }
    
    public void setListaAvon(List<Avon> listaAvon) {
        this.listaAvon = listaAvon;
    }
    
    public String getCidadeAviso() {
        return cidadeAviso;
    }
    
    public void setCidadeAviso(String cidadeAviso) {
        this.cidadeAviso = cidadeAviso;
    }
    
    public String getBairroAviso() {
        return bairroAviso;
    }
    
    public void setBairroAviso(String bairroAviso) {
        this.bairroAviso = bairroAviso;
    }
    
    public String getNegociadorAviso() {
        return negociadorAviso;
    }
    
    public void setNegociadorAviso(String negociadorAviso) {
        this.negociadorAviso = negociadorAviso;
    }
    
    public String getSituacaoAviso() {
        return situacaoAviso;
    }
    
    public void setSituacaoAviso(String situacaoAviso) {
        this.situacaoAviso = situacaoAviso;
    }
    
    public String getMarcaAviso() {
        return marcaAviso;
    }
    
    public void setMarcaAviso(String marcaAviso) {
        this.marcaAviso = marcaAviso;
    }
    
    public String getSetorAviso() {
        return setorAviso;
    }
    
    public void setSetorAviso(String setorAviso) {
        this.setorAviso = setorAviso;
    }
    
    public void addAvon(Avon avon){
        if(avon != null)
              if(!this.getListaAvon().contains(avon))
                    this.getListaAvon().add(avon);
    }
    
    
    public void removeAvon(Avon avon){
        if(avon != null){
            this.getListaAvon().remove(avon);
        }
    }
   
}
