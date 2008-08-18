package br.com.copal.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Jeyson
 */
@Entity
@NamedQueries({
    @NamedQuery(name="InstrucaoBoleto.recuperarTodos", query="SELECT a FROM InstrucaoBoleto a ORDER BY a.id"),
    @NamedQuery(name="InstrucaoBoleto.recuperarPorId", query="SELECT a FROM InstrucaoBoleto a WHERE a.id =:id ORDER BY a.id")
})
public class InstrucaoBoleto implements java.io.Serializable {
    
    @Id@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(length=20)
    private String descricao;
    @Column(length=50)
    private String frase;
    @ManyToMany(cascade = CascadeType.ALL,  
        mappedBy = "instrucoes")
    private List<TemplateBoleto> templatesBoleto;
    
    
    /** Creates a new instance of IntrucaoBoleto */
    public InstrucaoBoleto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<TemplateBoleto> getTemplatesBoleto() {
        return templatesBoleto;
    }

    public void setTemplatesBoleto(List<TemplateBoleto> templatesBoleto) {
        this.templatesBoleto = templatesBoleto;
    }
    
    public void addTemplate(TemplateBoleto template){
        if(template != null)
            if(!this.templatesBoleto.contains(template))
                this.templatesBoleto.add(template);
    }
    
    public void removeTemplate(TemplateBoleto template){
        if(template != null){
            getTemplatesBoleto().remove(template);
        }
    }

    
}
