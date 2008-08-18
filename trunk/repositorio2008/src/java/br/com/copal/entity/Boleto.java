/*
 * Boleto.java
 *
 * Created on 6 de Dezembro de 2007, 16:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.jboleto.JBoletoBean;

/**
 *
 * @author jean
 */

@Entity

@NamedQueries({
    @NamedQuery(name="Boleto.recuperarTodos", query="SELECT a FROM Boleto a ORDER BY a.idBoleto"),
    @NamedQuery(name="Boleto.recuperarPorId", query="SELECT a FROM Boleto a WHERE a.idBoleto =:idBoleto ORDER BY a.idBoleto")
})
public class Boleto implements Serializable {
    //Informações únicas do boleto
    @Id@GeneratedValue
    private Long idBoleto;
    @Temporal(value = TemporalType.DATE)
    private Date dataDocumento;
    @Temporal(value = TemporalType.DATE)
    private Date dataProcessamento;
    @Temporal(value = TemporalType.DATE)
    private Date dataVencimento;
    private String nomeGrupo;
    
    //Informações que nunca poderão mudar sobre o aviso
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    private Avon avon;
    private String noDocumento;  //setor registro asc concatenado
    private double valorboleto;
    
   /* @ManyToOne(fetch=FetchType.LAZY, optional=true)
    private TemplateBoleto templateBoleto;*/
    private String instrucao1;
    private String instrucao2;
    private String instrucao3;
    private String instrucao4;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    private TextoBoleto texto;
    //private String textoSuperior;
    
    @OneToMany(mappedBy="boleto")
    private List<ParcelasBoleto> parcelas;
    
    private boolean promocao;
    
    private String valorpromocao;
    
    private boolean carne;
    
    
    /** Creates a new instance of Boleto */
    public Boleto() {}
    
    public Long getIdBoleto() {
        return idBoleto;
    }
    
    public void setIdBoleto(Long idBoleto) {
        this.idBoleto = idBoleto;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public Date getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }
    
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public Avon getAvon() {
        return avon;
    }

    public void setAvon(Avon avon) {
        this.avon = avon;
    }

    /*public TemplateBoleto getTemplateBoleto() {
        return templateBoleto;
    }

    public void setTemplateBoleto(TemplateBoleto templateBoleto) {
        this.templateBoleto = templateBoleto;
    }*/

    public String getNoDocumento() {
        return noDocumento;
    }

    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
    }

    public double getValorboleto() {
        return valorboleto;
    }

    public void setValorboleto(double valorboleto) {
        this.valorboleto = valorboleto;
    }

    public String getInstrucao1() {
        return instrucao1;
    }

    public void setInstrucao1(String instrucao1) {
        this.instrucao1 = instrucao1;
    }

    public String getInstrucao2() {
        return instrucao2;
    }

    public void setInstrucao2(String instrucao2) {
        this.instrucao2 = instrucao2;
    }

    public String getInstrucao3() {
        return instrucao3;
    }

    public void setInstrucao3(String instrucao3) {
        this.instrucao3 = instrucao3;
    }

    public String getInstrucao4() {
        return instrucao4;
    }

    public void setInstrucao4(String instrucao4) {
        this.instrucao4 = instrucao4;
    }

   /* public String getTextoSuperior() {
        return textoSuperior;
    }

    public void setTextoSuperior(String textoSuperior) {
        this.textoSuperior = textoSuperior;
    }*/

    public TextoBoleto getTexto() {
        return texto;
    }

    public void setTexto(TextoBoleto texto) {
        this.texto = texto;
    }

    public List<ParcelasBoleto> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<ParcelasBoleto> parcelas) {
        this.parcelas = parcelas;
    }
    
    public void addParcela(ParcelasBoleto parcelaBoleto){
        if(parcelaBoleto != null)
              if(!this.getParcelas().contains(parcelaBoleto))
                    this.getParcelas().add(parcelaBoleto);
    }
    
    public void removeParcela(ParcelasBoleto parcelaBoleto){
        if(parcelaBoleto != null){
            getParcelas().remove(parcelaBoleto);
        }
    }

    public boolean isPromocao() {
        return promocao;
    }

    public void setPromocao(boolean promocao) {
        this.promocao = promocao;
    }

    public String getValorpromocao() {
        return valorpromocao;
    }

    public void setValorpromocao(String valorpromocao) {
        this.valorpromocao = valorpromocao;
    }

    public boolean isCarne() {
        return carne;
    }

    public void setCarne(boolean carne) {
        this.carne = carne;
    }

    

}
