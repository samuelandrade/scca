/*
 * TemplateBoleto.java
 *
 * Created on 29 de Julho de 2008, 13:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Jean
 */
@Entity
@NamedQueries({
    @NamedQuery(name="TemplateBoleto.recuperarTodos", query="SELECT a FROM TemplateBoleto a ORDER BY a.id"),
    @NamedQuery(name="TemplateBoleto.recuperarPorId", query="SELECT a FROM TemplateBoleto a WHERE a.id =:id ORDER BY a.id")
})
public class TemplateBoleto implements java.io.Serializable {
    
    @Id@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(length=20)
    private String nometemplate;
    
    
    
    @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinTable(
    name="TEMPLATE_INSTRUCAO",
            joinColumns=@JoinColumn(name="TEMPLATE_ID"),
            inverseJoinColumns=@JoinColumn(name="INSTRUCAO_ID"))
    private List<InstrucaoBoleto> instrucoes;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private TextoBoleto textoboleto;
    
    /*@OneToMany(mappedBy="templateBoleto", cascade=CascadeType.ALL)
    private List<Boleto> boletoLista;*/
    
    /** Creates a new instance of TemplateBoleto */
    public TemplateBoleto() {
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNometemplate() {
        return nometemplate;
    }
    
    public void setNometemplate(String nometemplate) {
        this.nometemplate = nometemplate;
    }
    
   
    public void addInstrucao(InstrucaoBoleto instrucao){
        if(instrucao != null)
            if(this.getInstrucoes().size() < 4)
                if(!this.getInstrucoes().contains(instrucao))
                    this.getInstrucoes().add(instrucao);
    }
    
    public void removeInstrucao(InstrucaoBoleto instrucao){
        if(instrucao != null){
            getInstrucoes().remove(instrucao);
        }
    }

    public List<InstrucaoBoleto> getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(List<InstrucaoBoleto> instrucoes) {
        this.instrucoes = instrucoes;
    }

    

    public void setTextoBoleto(TextoBoleto textoboleto) {
        this.setTextoboleto(textoboleto);
    }

    public TextoBoleto getTextoboleto() {
        return textoboleto;
    }

    public void setTextoboleto(TextoBoleto textoboleto) {
        this.textoboleto = textoboleto;
    }

    /*public List<Boleto> getBoletoLista() {
        return boletoLista;
    }

    public void setBoletoLista(List<Boleto> boletoLista) {
        this.boletoLista = boletoLista;
    }*/
    
}
