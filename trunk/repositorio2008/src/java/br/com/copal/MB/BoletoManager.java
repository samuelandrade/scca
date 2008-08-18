package br.com.copal.MB;


import br.com.copal.DAO.AvonDAO;
import br.com.copal.DAO.BoletoDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.DAO.ParcelasBoletoDAO;
import br.com.copal.DAO.TemplateBoletoDAO;
import br.com.copal.entity.Avon;
import br.com.copal.entity.Boleto;
import br.com.copal.entity.ParcelasBoleto;
import br.com.copal.entity.TemplateBoleto;
import br.com.copal.util.FacesMessages;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;
/**
 *
 * @author Jean
 */
public class BoletoManager {
    
    private DataModel BModel;
    private BoletoDAO BDAO;
    private Boleto boleto;
    private HttpSession session;
    private FacesMessages msg;
    private Integer instrucaoTid;
    private Avon avonSession;
    private AvonDAO ADAO;
    private TemplateBoleto templateSession;
    private TemplateBoletoDAO TempBDAO;
    private Integer idAvon;   
    private Integer idTemplate;
    private String urlRelatorio;
    private ParcelasBoletoDAO PBDAO;
    private String nomeGrupo;
    private Date dataVencimentoGrupo;
    private String nomeCobrador;
    private String cidadeAviso;
    private String bairroAviso;
    private String negociadorAviso;
    private String situacaoAviso;
    private String marcaAviso;
    private String setorAviso;
    private String statusA;
    private DataModel avonModel;
    private List<Avon> listaAvon;
    
    /** Creates a new instance of BoletoManager */
    public BoletoManager() {
        this.setBDAO(FactoryDAO.criarBoletoDAO());
        this.setADAO(FactoryDAO.criarAvonDAO());
        this.setPBDAO(FactoryDAO.criarParcelasBoletoDAO());
        this.setTempBDAO(FactoryDAO.criarTemplateBoletoDAO());
        this.setAvonSession(new Avon());
        this.setBoleto(new Boleto());
        FacesContext context = FacesContext.getCurrentInstance();
        setSession((HttpSession) context.getExternalContext().getSession(true));
    }
    
    
    public DataModel getListarBoleto(){
        setBModel(new ListDataModel(getBDAO().recuperarTodos()));
        return getBModel();
    }
    
    public String criarBoleto(){
        Integer idAv = Integer.valueOf(getIdAvon()).intValue();
        List<Avon> listAvon = getADAO().recuperarPorAviso(idAv);
        setAvonSession((Avon) listAvon.get(0));
        setBoleto(new Boleto());
        getSession().setAttribute("Boleto", getBoleto());
        return "criarBoleto";
    }
    public DataModel getListarPorFiltro(){
        List<Avon> listaavon = getADAO().recuperarPorFiltros(getCidadeAviso(),getBairroAviso(),getSetorAviso(),getNegociadorAviso(),getStatusA(),getNomeGrupo());
        
        List<Avon> listaavoniterator = new ArrayList<Avon>();
        listaavoniterator.addAll(listaavon);
        for(Iterator i = listaavoniterator.iterator();i.hasNext();){
        //for(int i;i<)
            
            Avon avoni = (Avon) i.next();
            boolean verificador = false;
            for(Iterator j = avoni.getBoletoLista().iterator();j.hasNext();){
                Boleto boletoj = (Boleto) j.next();
                if(boletoj.getNomeGrupo().equals(getNomeGrupo())){
                    verificador = true;
                    break;
                }
            }
            if(verificador){
                System.out.println(listaavon.indexOf(avoni));
                listaavon.remove(avoni);
                //listaavon.set(listaavon.indexOf(avoni),new Avon());
                //i.remove();
            }
        }
        setAvonModel(new ListDataModel(listaavon));
        return getAvonModel();
    }
    
    public String salvarAvisosBoleto(){
        /*setListaAvon((List<Avon>) getAvonModel().getWrappedData());
        Iterator i = getListaAvon().iterator();
        while(i.hasNext()){
            Avon avon = (Avon)i.next();
            if(avon.getStatusaviso()!= getStatusA()){
                avon = (Avon)getADAO().atualizar(avon);               
            }
        }*/
        
        for(Iterator i = getListaAvon().iterator();i.hasNext();){
            Avon avoni = (Avon)i.next();
            setBoleto(new Boleto());
            salvarBoleto(avoni);
        }
        setListaAvon(new ArrayList());
        return "";
    }
    
    
    public String Marcar(){
        boolean verificador = false;
        Avon avon = (Avon) getAvonModel().getRowData();
        if(getListaAvon()!=null){
             if(getListaAvon().size()>0){
                for(Iterator i = getListaAvon().iterator();i.hasNext();){
                    Avon avoni = (Avon)i.next();
                    if(avon.getAviso().equals(avoni.getAviso())){                        
                        getListaAvon().remove(avoni);
                        verificador = false;
                        break;
                    }else {
                        verificador = true;
                    }
                }
                if(verificador){
                    getListaAvon().add(avon);
                }               
            }
            else{
                this.getListaAvon().add(avon);
            }            
                    
        } else{
            setListaAvon(new ArrayList());
            addAvon((Avon) getAvonModel().getRowData());            
        }
        System.out.println(getListaAvon().size());    
        return "";
    }
    
    
    
    public String cadastrarBoleto(){
        return "salvarBoleto2";
    }
    
     public void salvarBoleto(Avon avon){
        //Integer idAv = Integer.valueOf(getIdAvon()).intValue();
        //List<Avon> listAvon = getADAO().recuperarPorAviso(idAv);
        //setAvonSession((Avon) listAvon.get(0));
        setTemplateSession((TemplateBoleto)getSession().getAttribute("TemplateBoleto"));
        System.out.println(getTemplateSession().getId());        
        //Integer idTemp = Integer.valueOf(getIdTemplate().intValue());
        //setTemplateSession((TemplateBoleto)getTempBDAO().recuperarporId(idTemp));
        getBoleto().setDataDocumento(new Date());
        getBoleto().setDataProcessamento(new Date());
        getBoleto().setDataVencimento(getDataVencimentoGrupo());
        getBoleto().setNomeGrupo(getNomeGrupo());
        getBoleto().setInstrucao1(getTemplateSession().getInstrucoes().get(0).getFrase());
        getBoleto().setNoDocumento(""+getAvonSession().getSetor()+getAvonSession().getRegistro()+getAvonSession().getAsc2());
        getBoleto().setValorboleto(avon.getAdLista().get(0).getValordebito());        
        getBoleto().setTexto(getTemplateSession().getTextoboleto());
        getBoleto().setAvon(avon);        
        setBoleto(getBDAO().atualizar(getBoleto()));
        List<ParcelasBoleto> parcelas = new ArrayList();        
        int i = 1;
        while(getBoleto().getValorboleto()/i >= Double.valueOf(50) && i <= 15){
            ParcelasBoleto parcelai = new ParcelasBoleto();
            BigDecimal valorP = new BigDecimal(getBoleto().getValorboleto()/i);
            valorP.setScale(2,BigDecimal.ROUND_HALF_UP);
            parcelai.setValorParcela(valorP.doubleValue());
            parcelai.setBoleto(getBoleto());
            parcelai.setNParcela(String.valueOf(i));
            parcelai = getPBDAO().atualizar(parcelai);
            parcelas.add(parcelai);            
            i++;
        }
        getBoleto().setParcelas(parcelas);
        setBoleto(getBDAO().atualizar(getBoleto()));
        getSession().setAttribute("Boleto",getBoleto());
        setUrlRelatorio("&");
        
    }
    
    
     
    public String editarBoleto(){
        setBoleto((Boleto) getBModel().getRowData());
        getSession().setAttribute("Boleto", getBoleto());
        return "editarBoleto";
    }
    
    public String atualizarBoleto() {
        setBoleto(getBDAO().atualizar(getBoleto()));
        getMsg().addSuccessMessage("Boleto atualizado com sucesso.");
        return "listarBoleto";
    }
    
   
    
    public String excluirBoleto() {
        Boleto tb = (Boleto) getBModel().getRowData();        
        getBDAO().remover(tb);
        getMsg().addSuccessMessage("Template Boleto excluido com sucesso.");
        return "listarBoleto";
    }

    public DataModel getBModel() {
        return BModel;
    }

    public void setBModel(DataModel BModel) {
        this.BModel = BModel;
    }

    public BoletoDAO getBDAO() {
        return BDAO;
    }

    public void setBDAO(BoletoDAO BDAO) {
        this.BDAO = BDAO;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
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

    public Avon getAvonSession() {
        return avonSession;
    }

    public void setAvonSession(Avon avonSession) {
        this.avonSession = avonSession;
    }

    public AvonDAO getADAO() {
        return ADAO;
    }

    public void setADAO(AvonDAO ADAO) {
        this.ADAO = ADAO;
    }

    public TemplateBoleto getTemplateSession() {
        return templateSession;
    }

    public void setTemplateSession(TemplateBoleto templateSession) {
        this.templateSession = templateSession;
    }

    public TemplateBoletoDAO getTempBDAO() {
        return TempBDAO;
    }

    public void setTempBDAO(TemplateBoletoDAO TempBDAO) {
        this.TempBDAO = TempBDAO;
    }

    public Integer getIdAvon() {
        return idAvon;
    }

    public void setIdAvon(Integer idAvon) {
        this.idAvon = idAvon;
    }

    public Integer getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Integer idTemplate) {
        this.idTemplate = idTemplate;
    }

    public String getUrlRelatorio() {
        return urlRelatorio;
    }

    public void setUrlRelatorio(String urlRelatorio) {
        this.urlRelatorio = urlRelatorio;
    }

    private void setPBDAO(ParcelasBoletoDAO parcelasBoletoDAO) {
        this.PBDAO = parcelasBoletoDAO;
    }

    public ParcelasBoletoDAO getPBDAO() {
        return PBDAO;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public Date getDataVencimentoGrupo() {
        return dataVencimentoGrupo;
    }

    public void setDataVencimentoGrupo(Date dataVencimentoGrupo) {
        this.dataVencimentoGrupo = dataVencimentoGrupo;
    }

    public String getNomeCobrador() {
        return nomeCobrador;
    }

    public void setNomeCobrador(String nomeCobrador) {
        this.nomeCobrador = nomeCobrador;
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

    public String getStatusA() {
        return statusA;
    }

    public void setStatusA(String statusA) {
        this.statusA = statusA;
    }

    public DataModel getAvonModel() {
        return avonModel;
    }

    public void setAvonModel(DataModel avonModel) {
        this.avonModel = avonModel;
    }

    public List<Avon> getListaAvon() {
        return listaAvon;
    }

    public void setListaAvon(List<Avon> listaAvon) {
        this.listaAvon = listaAvon;
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


