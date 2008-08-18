package br.com.copal.MB;

import br.com.copal.DAO.AvonDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.DAO.SituacaoDAO;
import br.com.copal.FN.SituacaoFN;
import br.com.copal.entity.Avon;
import br.com.copal.entity.Situacao;
import br.com.copal.util.FacesMessages;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jean
 */
public class SituacaoManager {
    
    private DataModel situacaoModel;
    private SituacaoDAO sDAO;
    private Situacao situacao;
    private HttpSession session;
    private FacesMessages msg;
    private SituacaoFN SFN;
    private AvonDAO aDAO;

    private Avon avonSession;
    
    /** Creates a new instance of SituacaoMB */
    public SituacaoManager() {
        this.setSDAO(FactoryDAO.criarSituacaoDAO());
        //this.setADAO(FactoryDAO.criarAvonDAO());
        this.setSituacao(new Situacao());
        //this.setCFN(new CobradorFN(this));
        //this.getCFN().criarCobradorPadrao();
        FacesContext context = FacesContext.getCurrentInstance();
        setSession((HttpSession) context.getExternalContext().getSession(true));
    }
    
    public SituacaoManager(SituacaoFN situacaoFN) {
        this.setSDAO(FactoryDAO.criarSituacaoDAO());
        //this.setADAO(FactoryDAO.criarAvonDAO());
        this.setSituacao(new Situacao());
        //this.setCFN(new CobradorFN(this));
        //this.getCFN().criarCobradorPadrao();
        this.setSFN(situacaoFN);
        FacesContext context = FacesContext.getCurrentInstance();
        setSession((HttpSession) context.getExternalContext().getSession(true));
    }

    public DataModel getCobradorModel() {
        return getSituacaoModel();
    }

    public void setCobradorModel(DataModel situacaoModel) {
        this.setSituacaoModel(situacaoModel);
    }

    public SituacaoDAO getSDAO() {
        return sDAO;
    }

    public void setSDAO(SituacaoDAO sDAO) {
        this.sDAO = sDAO;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public DataModel getSituacaoModel() {
        return situacaoModel;
    }

    public void setSituacaoModel(DataModel situacaoModel) {
        this.situacaoModel = situacaoModel;
    }

    public DataModel getListarSituacao(){
        setSituacaoModel(new ListDataModel(getSDAO().recuperarTodos()));
        return getSituacaoModel();
    }
    
    public String criarSituacao(){
        setSituacao(new Situacao());
        return "criarSituacao";
    }
    
     public String salvarSituacao(){
        getSDAO().salvar(getSituacao());
        this.setSituacao(new Situacao());
        return "listarSituacao";
    }
    
    public String editarSituacao(){
        setSituacao((Situacao) getSituacaoModel().getRowData());
        return "editarSituacao";
    }
    
    public String atualizarSituacao() {
        setSituacao(getSDAO().atualizar(getSituacao()));
        getMsg().addSuccessMessage("Situacao atualizado com sucesso.");
        return "listarSituacao";
    }
    
    public String selecionarSituacao(){
        setADAO(FactoryDAO.criarAvonDAO());
        Situacao s = (Situacao) getSituacaoModel().getRowData();
        setAvonSession((Avon) getSession().getAttribute("Avon"));
        getAvonSession().setSituacaocb(s);
        getADAO().atualizar(getAvonSession());
        return "listarAvon";
    }
    
    public String excluirSituacao() {
        Situacao s = (Situacao) getSituacaoModel().getRowData();
        //Situacao  = cDAO.recuperarporId(1);
        List<Avon> ls = getADAO().recuperarPorSituacaoCb(s.getNomeSituacao());
        if(!ls.isEmpty()){
        for (int i = 0; i < ls.size(); i++){
            List<Avon> aviso = getADAO().recuperarPorAviso(ls.get(i).getAviso());
            aviso.get(0).setSituacaocb(null);
            getADAO().atualizar(aviso.get(0));
        }}
        getSDAO().remover(s);
        getMsg().addSuccessMessage("Situacao excluida com sucesso.");
        return "listarCobrador";
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

    public SituacaoFN getSFN() {
        return SFN;
    }

    public void setSFN(SituacaoFN SFN) {
        this.SFN = SFN;
    }

    public AvonDAO getADAO() {
        return aDAO;
    }

    public void setADAO(AvonDAO aDAO) {
        this.aDAO = aDAO;
    }

    public Avon getAvonSession() {
        return avonSession;
    }

    public void setAvonSession(Avon avonSession) {
        this.avonSession = avonSession;
    }
    
}
