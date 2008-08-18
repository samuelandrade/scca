package br.com.copal.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
 * @author Jeyson
 */

@Entity

@NamedQueries({
    @NamedQuery(name="Cobrador.recuperarTodos", query="SELECT a FROM Cobrador a ORDER BY a.idcobrador" ),
    @NamedQuery(name="Cobrador.recuperarPorId", query="SELECT a FROM Cobrador a WHERE a.idcobrador =:idcobrador")
})

public class Cobrador implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idcobrador;
    private String nome;
    private String telefone;
    private double comissao;
    private double salario;
    private Integer senha;
    
    @OneToMany(mappedBy="cobrador")
    private List<Avon> avon;
    
    @OneToMany(mappedBy="cobrador", cascade=CascadeType.ALL)
    private List<Pagamento> pagamento;

    public Cobrador() {}

    public Integer getIdcobrador() {
        return idcobrador;
    }

    public void setIdcobrador(Integer idcobrador) {
        this.idcobrador = idcobrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Pagamento> getPagamento() {
        return pagamento;
    }

    public void setPagamento(List<Pagamento> pagamento) {
        this.pagamento = pagamento;
    }

    public Integer getSenha() {
        return senha;
    }

    public void setSenha(Integer senha) {
        this.senha = senha;
    }
}
