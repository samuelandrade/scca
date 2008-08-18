/*
 * Pagamento.java
 *
 * Created on 8 de Janeiro de 2008, 16:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToOne;

/**
 *
 * @author jean
 */
@Entity

@NamedQueries({
    @NamedQuery(name="Pagamento.recuperarTodos", query="SELECT a FROM Pagamento a ORDER BY a.nrecibo" ),
    @NamedQuery(name="Pagamento.recuperarPorId", query="SELECT a FROM Pagamento a WHERE a.idrecibo =:idreciboA"),
    @NamedQuery(name="Pagamento.recuperarPorAd", query="SELECT a FROM Pagamento a WHERE a.ad.idad =:idAd ORDER BY a.nrecibo"),    
    @NamedQuery(name="Pagamento.recuperarPorPeriodo", query="SELECT a FROM Pagamento a WHERE a.datapc BETWEEN :data_ini AND :data_fim AND a.negociador LIKE concat(:nomeCob,'%') ORDER BY a.nrecibo")    
})
public class Pagamento implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)    
    private Integer idrecibo;
    @Column(unique=true)
    private String nrecibo;
    private String nomevendedora;
    @Temporal(value = TemporalType.DATE)
    private Date datapagamento;
    @Temporal(value = TemporalType.DATE)
    private Date datapc;
    private String negociador;
    private Double valor;
    private Double comissaocobrador;
    private Double valorcopal;
    private Double valoravon;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    private Ad ad;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    private Cobrador cobrador;  
    
    @OneToOne(mappedBy = "pagamento")
    private CaixaItem caixaItem;
      
    public Pagamento(){}
    
    public Pagamento(String nrecibo,String nomevendedora,Date datapagamento,Date datapc,String negociador,double valor,double comissaocobrador){
        this.setNrecibo(nrecibo);
        this.setNomevendedora(nomevendedora);
        this.setDatapagamento(datapagamento);
        this.setDatapc(datapc);
        this.setNegociador(negociador);
        this.setValor(valor); 
        this.setComissaocobrador(comissaocobrador);
    }
    
    public Integer getIdrecibo() {
        return idrecibo;
    }

    public void setIdrecibo(Integer idrecibo) {
        this.idrecibo = idrecibo;
    }
    
    public String getNrecibo() {
        return nrecibo;
    }

    public void setNrecibo(String nrecibo) {
        this.nrecibo = nrecibo;
    }

    public String getNomevendedora() {
        return nomevendedora;
    }

    public void setNomevendedora(String nomevendedora) {
        this.nomevendedora = nomevendedora;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    public Date getDatapc() {
        return datapc;
    }

    public void setDatapc(Date datapc) {
        this.datapc = datapc;
    }

    public String getNegociador() {
        return negociador;
    }

    public void setNegociador(String negociador) {
        this.negociador = negociador;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Cobrador getCobrador() {
        return cobrador;
    }

    public void setCobrador(Cobrador cobrador) {
        this.cobrador = cobrador;
    }

    public double getComissaocobrador() {
        return comissaocobrador;
    }

    public void setComissaocobrador(double comissaocobrador) {
        this.comissaocobrador = comissaocobrador;
    }

    public Double getValorcopal() {
        return valorcopal;
    }

    public void setValorcopal(Double valorcopal) {
        this.valorcopal = valorcopal;
    }

    public Double getValoravon() {
        return valoravon;
    }

    public void setValoravon(Double valoravon) {
        this.valoravon = valoravon;
    }
}
