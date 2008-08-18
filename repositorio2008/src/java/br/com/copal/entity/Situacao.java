package br.com.copal.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;

/**
 *
 * @author Jean
 */
@Entity

@NamedQueries({
    @NamedQuery(name="Situacao.recuperarTodos", query="SELECT a FROM Situacao a ORDER BY a.idsituacao"),
    @NamedQuery(name="Situacao.recuperarPorId", query="SELECT a FROM Situacao a WHERE a.idsituacao =:idsituacao ORDER BY a.idsituacao")
})
public class Situacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idsituacao; 
    
    @Column(unique=true)
    private String nomeSituacao;    
    private String descricaoSituacao;
    
    @OneToMany(mappedBy="situacao")
    private List<Avon> avon;
    /** Creates a new instance of Situacao */
    public Situacao() {
    }

    public Integer getIdsituacao() {
        return idsituacao;
    }

    public void setIdsituacao(Integer idsituacao) {
        this.idsituacao = idsituacao;
    }

    public String getNomeSituacao() {
        return nomeSituacao;
    }

    public void setNomeSituacao(String nomeSituacao) {
        this.nomeSituacao = nomeSituacao;
    }

    public String getDescricaoSituacao() {
        return descricaoSituacao;
    }

    public void setDescricaoSituacao(String descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
    }
    
}
