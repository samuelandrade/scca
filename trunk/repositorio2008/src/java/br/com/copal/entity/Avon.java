/*
 * Avon.java
 *
 * Created on 4 de Dezembro de 2007, 15:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.model.DataModel;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jean
 */
@Entity

@NamedQueries({
    @NamedQuery(name="Avon.recuperarTodos", query="SELECT new Avon(a.nome,a.cidade,a.aviso,a.situacao) FROM Avon a WHERE a.situacao LIKE 'A'  ORDER BY a.aviso" ),
    @NamedQuery(name="Avon.recuperarTodos2", query="SELECT a FROM Avon a WHERE a.situacao LIKE 'A' and a.cobrador.nome =:cobrador  ORDER BY a.aviso" ),
    @NamedQuery(name="Avon.recuperarPorAviso", query="SELECT a FROM Avon a left join fetch a.adLista WHERE a.aviso =:aviso AND a.situacao LIKE 'A' ORDER BY a.aviso"),
    //query="SELECT new Avon(a.aviso,a.registro, a.asc2, a.nome, a.setor, a.nascimento, a.rg, a.cpf, a.ddd, a.fone, a.endereco, a.numerocasa, a.complemento, a.bairro, a.cep, a.cidade, a.uf, a.referencia, a.dataestabelecimento, a.apelido, a.nomepai, a.nomemae, a.modulo, a.secao, a.totalcampanhas, a.cpenvio, a.dataenvio, a.datadevolucao, a.ads, a.cobrador) FROM Avon a WHERE a.aviso =:aviso ORDER BY a.aviso"),
    @NamedQuery(name="Avon.recuperarPorNome", query="SELECT new Avon(a.nome,a.cidade,a.aviso,a.situacao) FROM Avon a WHERE a.nome LIKE concat(:nome,'%') AND a.situacao LIKE 'A' ORDER BY a.nome"),
    @NamedQuery(name="Avon.recuperarPorAvisoObjeto" , query="SELECT a FROM Avon a WHERE a.aviso =:aviso AND a.situacao LIKE 'A'" ),
    @NamedQuery(name="Avon.recuperarPorSetor", query="SELECT new Avon(a.nome, a.cidade, a.aviso,a.situacao) FROM Avon a WHERE a.setor =:setor AND a.situacao LIKE 'A' ORDER BY a.nome"),
    @NamedQuery(name="Avon.recuperarCidade", query="SELECT new Avon(a.nome, a.cidade, a.aviso,a.situacao) FROM Avon a WHERE a.cidade LIKE concat(:cidade,'%') AND a.situacao LIKE 'A' ORDER BY a.cidade,a.nome"),
    @NamedQuery(name="Avon.recuperarPorSituacaoCb", query="SELECT new Avon(a.nome, a.cidade, a.aviso,a.situacao) FROM Avon a WHERE a.situacaocb.nomeSituacao LIKE concat(:nomesituacao,'%') AND a.situacao LIKE 'A' ORDER BY a.nome"),
    @NamedQuery(name="Avon.recuperarPorStatusCobrador", query="SELECT a FROM Avon a WHERE a.cobrador.nome =:cobrador AND a.statusaviso =:statusaviso AND a.situacao LIKE 'A' ORDER BY a.aviso"),
    @NamedQuery(name="Avon.recuperarPorCobrador", query="SELECT new Avon(a.nome, a.cidade, a.aviso,a.situacao) FROM Avon a WHERE a.cobrador.nome LIKE concat(:cobrador,'%') AND a.situacao LIKE 'A' ORDER BY a.nome"),
    @NamedQuery(name="Avon.recuperarVencidos", query="SELECT a FROM Avon a WHERE a.datadevolucao < :devolucao AND a.cobrador.nome LIKE :cobrador" )
})  
    
public class Avon implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer aviso;
    @Column(length=5)
    private String registro;
    @Column(length=3)
    private String asc2;
    private String nome;
    private String setor;
    @Temporal(value = TemporalType.DATE)
    private Date nascimento;
    @Column(length=13)
    private String rg;
    @Column(length=14)
    private String cpf;
    @Column(length=4)
    private String ddd;
    @Column(length=9)
    private String fone;
    private String endereco;
    private String numerocasa;
    private String complemento;   
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String referencia;
    @Temporal(value = TemporalType.DATE)
    private Date dataestabelecimento;
    private String apelido;
    private String nomepai;
    private String nomemae;
    @Column(length=3)
    private String modulo;
    @Column(length=1)
    private String secao;
    private Integer totalcampanhas;
    @Column(length=4)
    private String nagencia;
    private String nomeagencia;
    @Column(length=2)
    private String cpenvio;
    @Temporal(value = TemporalType.DATE)
    private Date dataenvio;
    @Temporal(value = TemporalType.DATE)
    private Date datadevolucao;    
    private String statusaviso;
    private String situacao;
    private Integer avisoant;
    private String bancoant;
    
    @OneToMany(mappedBy="avon", cascade=CascadeType.ALL)
    private List<Ad> adLista; 
    
    @OneToMany(mappedBy="avon", cascade=CascadeType.ALL)
    private List<Boleto> boletoLista;
        
    @ManyToOne(fetch=FetchType.LAZY)
    private Cobrador cobrador;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Situacao situacaocb;
    
    
    public Integer getAviso() {
        return aviso;
    }

    public void setAviso(Integer aviso) {
        this.aviso = aviso;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getAsc2() {
        return asc2;
    }

    public void setAsc2(String asc2) {
        this.asc2 = asc2;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumerocasa() {
        return numerocasa;
    }

    public void setNumerocasa(String numerocasa) {
        this.numerocasa = numerocasa;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getDataestabelecimento() {
        return dataestabelecimento;
    }

    public void setDataestabelecimento(Date dataestabelecimento) {
        this.dataestabelecimento = dataestabelecimento;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNomepai() {
        return nomepai;
    }

    public void setNomepai(String nomepai) {
        this.nomepai = nomepai;
    }

    public String getNomemae() {
        return nomemae;
    }

    public void setNomemae(String nomemae) {
        this.nomemae = nomemae;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public Integer getTotalcampanhas() {
        return totalcampanhas;
    }

    public void setTotalcampanhas(Integer totalcampanhas) {
        this.totalcampanhas = totalcampanhas;
    }

    public String getNagencia() {
        return nagencia;
    }

    public void setNagencia(String nagencia) {
        this.nagencia = nagencia;
    }

    public String getNomeagencia() {
        return nomeagencia;
    }

    public void setNomeagencia(String nomeagencia) {
        this.nomeagencia = nomeagencia;
    }

    public String getCpenvio() {
        return cpenvio;
    }

    public void setCpenvio(String cpenvio) {
        this.cpenvio = cpenvio;
    }

    public Date getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(Date dataenvio) {
        this.dataenvio = dataenvio;
    }

    public Date getDatadevolucao() {
        return datadevolucao;
    }

    public void setDatadevolucao(Date datadevolucao) {
        this.datadevolucao = datadevolucao;
    }

    public Cobrador getCobrador() {
        return cobrador;
    }

    public void setCobrador(Cobrador cobrador) {
        this.cobrador = cobrador;
    } 
    
    public List<Ad> getAdLista() {
        return adLista;
    }

    public void setAdLista(List<Ad> adLista) {
        this.adLista = adLista;
    }
    
    public void addAdLista(Ad ad){
        this.getAdLista().add(ad);
    }
    
    public Avon(){}
    public Avon(String nome, String cidade, int aviso, String situacao){
        this.setNome(nome);
        this.setCidade(cidade);
        this.setAviso(aviso);
        this.setSituacao(situacao);
        this.setCobrador(getCobrador());
    }
    
    public Avon(int aviso, String setor, String reg, String asc,  String nome, Date devolucao, List Ad){
        this.setNome(nome);
        this.setSetor(setor);
        this.setRegistro(reg);
        this.setAsc2(asc);
        this.setAviso(aviso);
        this.setDatadevolucao(devolucao);
        this.setAdLista(Ad);
    }
    
//    public Avon(
//               Integer registro,
//            Integer asc2,
//            String nome,
//            Integer setor,
//            Date nascimento,
//            String rg,
//            String cpf,
//            Integer ddd,
//            String fone,
//            String endereco,
//            String numerocasa,
//            String complemento,
//            String bairro,
//            String cep,
//            String cidade,
//            String uf,
//            String referencia,
//            Date dataestabelecimento,
//            String apelido,
//            String nomepai,
//            String nomemae,
//            Integer modulo,            
//            Integer secao,
//            Integer totalcampanhas,
//            String cpenvio,
//            Date dataenvio,
//            Date datadevolucao,
//            List<Ad> ads, 
//            Cobrador cobrador)
//    {      
//        this.setAviso(getAviso());
//        this.setRegistro(registro);
//        this.setAsc2(asc2);
//        this.setNome(nome);
//        this.setSetor(setor);
//        this.setNascimento(nascimento);
//        this.setRg(rg);
//        this.setCpf(cpf);
//        this.setDdd(ddd);
//        this.setFone(fone);
//        this.setEndereco(endereco);
//        this.setNumerocasa(numerocasa);
//        this.setComplemento(complemento);
//        this.setBairro(bairro);
//        this.setCep(cep);
//        this.setCidade(cidade);
//        this.setUf(uf);
//        this.setReferencia(referencia);
//        this.setDataestabelecimento(dataestabelecimento);
//        this.setApelido(apelido);
//        this.setNomepai(nomepai);
//        this.setNomemae(nomemae);
//        this.setModulo(modulo);
//        this.setSecao(secao);
//        this.setTotalcampanhas(totalcampanhas);
//        this.setCpenvio(cpenvio);
//        this.setDataenvio(dataenvio);
//        this.setDatadevolucao(datadevolucao);
//        this.setAdLista(getAdLista());
//        this.setCobrador(cobrador);
//    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Integer getAvisoant() {
        return avisoant;
    }

    public void setAvisoant(Integer avisoant) {
        this.avisoant = avisoant;
    }

    public String getBancoant() {
        return bancoant;
    }

    public void setBancoant(String bancoant) {
        this.bancoant = bancoant;
    }

    public Situacao getSituacaocb() {
        return situacaocb;
    }

    public void setSituacaocb(Situacao situacaocb) {
        this.situacaocb = situacaocb;
    }

    public String getStatusaviso() {
        return statusaviso;
    }

    public void setStatusaviso(String statusaviso) {
        this.statusaviso = statusaviso;
    }

    public List<Boleto> getBoletoLista() {
        return boletoLista;
    }

    public void setBoletoLista(List<Boleto> boletoLista) {
        this.boletoLista = boletoLista;
    }

   

    
    
}
