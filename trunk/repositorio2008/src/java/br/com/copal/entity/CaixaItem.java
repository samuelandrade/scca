package br.com.copal.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Jeyson
 */

@Entity

@NamedQueries({
    @NamedQuery(name="CaixaItem.recuperarTodos", query="SELECT a FROM CaixaItem a ORDER BY a.idcaixaitem" ),
    @NamedQuery(name="CaixaItem.recuperarPorIdCaixa", query="SELECT a FROM CaixaItem a WHERE a.caixa.idcaixa =:idcaixaitensP"),
    @NamedQuery(name="CaixaItem.recuperarPorId", query="SELECT a FROM CaixaItem a WHERE a.idcaixaitem =:idcaixa"),
    @NamedQuery(name="CaixaItem.recuperarSomaCheque", query="SELECT SUM(a.valorch) FROM CaixaItem a WHERE a.caixa.idcaixa =:idcaixa"),
    @NamedQuery(name="CaixaItem.recuperarSomaDinheiro", query="SELECT SUM(a.valordin) FROM CaixaItem a WHERE a.caixa.idcaixa =:idcaixa"),
    @NamedQuery(name="CaixaItem.recuperarPorTipo", query="SELECT a FROM CaixaItem a WHERE a.tipo =:entsai"),
    @NamedQuery(name="CaixaItem.recuperarSomaChequeTipo", query="SELECT SUM(a.valorch) FROM CaixaItem a WHERE a.tipo =:entsai AND a.caixa.idcaixa =:idcaixa"),
    @NamedQuery(name="CaixaItem.recuperarSomaDinheiroTipo", query="SELECT SUM(a.valordin) FROM CaixaItem a WHERE a.tipo =:entsai AND a.caixa.idcaixa =:idcaixa"),
    @NamedQuery(name="CaixaItem.recuperarPeloPagamento", query="SELECT a FROM CaixaItem a WHERE a.pagamento =:pagamento")
})

public class CaixaItem implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idcaixaitem;
    private String nome;
    private double valorch;
    private double valordin;
    private String descricao;
    private boolean tipo;
       
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    private Caixa caixa;
    
    @OneToOne(fetch=FetchType.LAZY, optional=true)
    private Pagamento pagamento;
    
    /** Creates a new instance of CaixaItem */
    public CaixaItem() {}

    public Integer getIdcaixaitem() {
        return idcaixaitem;
    }

    public void setIdcaixaitem(Integer idcaixaitem) {
        this.idcaixaitem = idcaixaitem;
    }

    public double getValorch() {
        return valorch;
    }

    public void setValorch(double valch) {
        this.valorch = valch;
    }

    public double getValordin() {
        return valordin;
    }

    public void setValordin(double valdin) {
        this.valordin = valdin;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
