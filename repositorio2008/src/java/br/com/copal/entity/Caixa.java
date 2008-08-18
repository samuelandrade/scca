package br.com.copal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


        
@Entity

@NamedQueries({
    @NamedQuery(name="Caixa.recuperarTodos", query="SELECT a FROM Caixa a ORDER BY a.idcaixa" ),
    @NamedQuery(name="Caixa.recuperarPorId", query="SELECT a FROM Caixa a WHERE a.idcaixa =:idcaixa"),
    @NamedQuery(name="Caixa.recuperarData", query="SELECT a FROM Caixa a WHERE a.data_caixa =:data_caixa"),
    @NamedQuery(name="Caixa.recuperarUltimoCaixa", query="SELECT Max(a.idcaixa) FROM Caixa a")
})

public class Caixa implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idcaixa;
    private Integer idcaixaant;
    private double saldodia_dinheiro;
    private double saldogeral_dinheiro;
    private double saldodia_cheque;
    private double saldogeral_cheque;
    private boolean finalizado;
    @Temporal(value = TemporalType.DATE)
    private Date data_caixa;
    @Temporal(value = TemporalType.DATE)
    private Date data_caixaant;
       
    @OneToMany(mappedBy="caixa", cascade=CascadeType.ALL)
    private List<CaixaItem> caixaitem;
    
    public Caixa() {}

    public Integer getIdcaixa() {
        return idcaixa;
    }

    public void setIdcaixa(Integer idcaixa) {
        this.idcaixa = idcaixa;
    }

    public Integer getIdcaixaant() {
        return idcaixaant;
    }

    public void setIdcaixaant(Integer idcaixaant) {
        this.idcaixaant = idcaixaant;
    }

    public Date getData_caixa() {
        return data_caixa;
    }

    public void setData_caixa(Date data_caixa) {
        this.data_caixa = data_caixa;
    }

    public Date getData_caixaant() {
        return data_caixaant;
    }

    public void setData_caixaant(Date data_caixaant) {
        this.data_caixaant = data_caixaant;
    }

    public List<CaixaItem> getCaixaItem() {
        return caixaitem;
    }

    public void setCaixaIteM(List<CaixaItem> caixaitem) {
        this.caixaitem = caixaitem;
    }
    
    public double getSaldodia_dinheiro() {
        return saldodia_dinheiro;
    }

    public void setSaldodia_dinheiro(double saldodia) {
        this.saldodia_dinheiro = saldodia;
    }

    public double getSaldogeral_dinheiro() {
        return saldogeral_dinheiro;
    }

    public void setSaldogeral_dinheiro(double saldogeral) {
        this.saldogeral_dinheiro = saldogeral;
    }

       public double getSaldodia_cheque() {
        return saldodia_cheque;
    }

    public void setSaldodia_cheque(double saldodia_cheque) {
        this.saldodia_cheque = saldodia_cheque;
    }

    public double getSaldogeral_cheque() {
        return saldogeral_cheque;
    }

    public void setSaldogeral_cheque(double saldogeral_cheque) {
        this.saldogeral_cheque = saldogeral_cheque;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    
    
}
