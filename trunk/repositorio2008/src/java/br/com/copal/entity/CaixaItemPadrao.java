/*
 * CaixaItemPadrao.java
 *
 * Created on 6 de Maio de 2008, 07:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

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
 * @author Nhandeara
 */

@Entity

@NamedQuery(name="CaixaItemPadrao.recuperarTodos", query="SELECT a FROM CaixaItemPadrao a ORDER BY a.idcaixaitempadrao" )

public class CaixaItemPadrao implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idcaixaitempadrao;
    private String nome;
    private String descricao;
    private boolean tipo;
    
    public CaixaItemPadrao() {
    }

    public Integer getIdcaixaitempadrao() {
        return idcaixaitempadrao;
    }

    public void setIdcaixaitempadrao(Integer idcaixaitempadrao) {
        this.idcaixaitempadrao = idcaixaitempadrao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
