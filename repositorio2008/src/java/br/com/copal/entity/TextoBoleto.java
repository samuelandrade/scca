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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Jean
 */

@Entity
@NamedQueries({
    @NamedQuery(name="TextoBoleto.recuperarTodos", query="SELECT a FROM TextoBoleto a ORDER BY a.id"),
    @NamedQuery(name="TextoBoleto.recuperarPorId", query="SELECT a FROM TextoBoleto a WHERE a.id =:id ORDER BY a.id")
})
public class TextoBoleto implements Serializable {

    @Id@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(length=20)
    private String descricao;
    @Lob
    private String texto;
    
    @OneToMany(mappedBy="textoboleto")
    private List<TemplateBoleto> templates;   
    
    /** Creates a new instance of TextoBoleto */
    public TextoBoleto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
