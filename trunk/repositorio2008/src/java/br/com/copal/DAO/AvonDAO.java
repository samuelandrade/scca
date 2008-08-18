/*
 * AvonDAO.java
 *
 * Created on 4 de Dezembro de 2007, 15:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.DAO;

import br.com.copal.entity.Avon;
import br.com.copal.entity.Cobrador;
import java.util.Date;
import java.util.List;

/**
 *
 * @author copal
 */
public interface AvonDAO {
    
    //Metodos Padrões
    public void salvar(Avon avon);
    public Avon atualizar(Avon avon);
    public void remover(Avon avon);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    public List recuperarTodos2(String cobrador);
    public List recuperarPorCidade(String cidade);
    public List recuperarPorSetor(int setor);
    public List recuperarPorNome(String nome);
    public List recuperarPorCobrador(String nomeCobrador);
    public List recuperarPorSituacaoCb(String nomeSituacao);
    public List recuperarPorAviso(Integer aviso); 
    public List recuperarPorStatusCobrador(String nomeCobrador,String statusAviso);
    public List recuperarVencidos(Date devolucao, String cobrador);
    
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
    public Avon recuperarPorAvisoObjeto(Integer aviso);      

    public List recuperarPorFiltros(String cidade, String bairro, String setor, String negociador, String situacao,String boletognome);
}
