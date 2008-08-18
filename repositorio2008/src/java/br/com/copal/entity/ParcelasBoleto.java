/*
 * ParcelasBoleto.java
 *
 * Created on 9 de Agosto de 2008, 10:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Jean
 */
@Entity

@NamedQueries({
    @NamedQuery(name="ParcelasBoleto.recuperarTodos", query="SELECT a FROM ParcelasBoleto a ORDER BY a.idParcela"),
    @NamedQuery(name="ParcelasBoleto.recuperarPorId", query="SELECT a FROM ParcelasBoleto a WHERE a.idParcela =:idParcela ORDER BY a.idParcela")
})
public class ParcelasBoleto {

    @Id@GeneratedValue
    private long idParcela;
    private double valorParcela;
    private String nParcela;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    private Boleto boleto;
    
    /** Creates a new instance of ParcelasBoleto */
    public ParcelasBoleto() {
    }

    public long getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(long idParcela) {
        this.idParcela = idParcela;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public String getNParcela() {
        return nParcela;
    }

    public void setNParcela(String nParcela) {
        this.nParcela = nParcela;
    }
    
    
    
    
}
