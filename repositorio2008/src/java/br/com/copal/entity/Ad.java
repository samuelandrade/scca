package br.com.copal.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.model.DataModel;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;

/**
 *  @author Jean
 */

@Entity

@NamedQueries({
    @NamedQuery(name="Ad.recuperarTodos"   , query="SELECT a FROM Ad a ORDER BY a.avon.aviso"         ),
    @NamedQuery(name="Ad.recuperarPorIdAd" , query="SELECT a FROM Ad a WHERE a.idad =:idad"            ),
    @NamedQuery(name="Ad.recuperarPorAviso", query="SELECT a FROM Ad a WHERE a.avon.aviso =:avon_aviso"),
    @NamedQuery(name="Ad.recuperarPorCp"   , query="SELECT a FROM Ad a WHERE a.cp =:cp"                )
})

public class Ad implements Serializable  {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idad;
    @Column(length=5)
    private String avisodebito;
    @Temporal(value = TemporalType.DATE)
    private Date dataad;
    @Column(length=2)
    private String cp;
    @Column(length=2)
    private Integer pricecp;
    @Column(length=1)
    private Integer sinal;
    //valor do debito se dar por principal+comissao+encargos ps.: apenas para constar
    private double valordebito;
    private String notafiscal;
    @Temporal(value = TemporalType.DATE)
    private Date datanf;
    @Temporal(value = TemporalType.DATE)
    private Date dataAtualizacao;
    private double comissao;
    private double encargos;    
    private double principal;
    private double juros;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    private Avon avon;
    
    @OneToMany(mappedBy="ad", cascade=CascadeType.ALL)
    private List<Pagamento> pagamentos;
            
    public Integer getIdad() {
        return idad;
    }

    public void setIdad(Integer idad) {
        this.idad = idad;
    }

    public String getAvisodebito() {
        return avisodebito;
    }

    public void setAvisodebito(String avisodebito) {
        this.avisodebito = avisodebito;
    }

    public Date getDataad() {
        return dataad;
    }

    public void setDataad(Date dataad) {
        this.dataad = dataad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Integer getPricecp() {
        return pricecp;
    }

    public void setPricecp(Integer pricecp) {
        this.pricecp = pricecp;
    }

    public Integer getSinal() {
        return sinal;
    }

    public void setSinal(Integer sinal) {
        this.sinal = sinal;
    }

    public double getValordebito() {
        return valordebito;
    }

    public void setValordebito(double valordebito) {
        this.valordebito = valordebito;
    }

    public String getNotafiscal() {
        return notafiscal;
    }

    public void setNotafiscal(String notafiscal) {
        this.notafiscal = notafiscal;
    }

    public Date getDatanf() {
        return datanf;
    }

    public void setDatanf(Date datanf) {
        this.datanf = datanf;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getEncargos() {
        return encargos;
    }

    public void setEncargos(double encargos) {
        this.encargos = encargos;
    }

    public Avon getAvon() {
        return avon;
    }

    public void setAvon(Avon avon) {
        this.avon = avon;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }
}
