/*
 * CobradorFN.java
 *
 * Created on 29 de Fevereiro de 2008, 15:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.MB.CobradorManager;
import br.com.copal.entity.Cobrador;
import br.com.copal.entity.Pagamento;

/**
 *
 * @author Nhandeara
 */
public class CobradorFN {
    
    private CobradorManager cobradorManager;
    
    /** Creates a new instance of CobradorFN */
    public CobradorFN() {
        this.setCobradorManager(new CobradorManager(this));
        this.criarCobradorPadrao();
    }
    
    public CobradorFN(CobradorManager cManager) {
        this.setCobradorManager(cManager);
    }

    public CobradorManager getCobradorManager() {
        return cobradorManager;
    }

    public void setCobradorManager(CobradorManager cobradorManager) {
        this.cobradorManager = cobradorManager;
    }
    
    //Metodo para acomplar o Cobrador ao Pagamento
    public void setCobradorPagamento(Pagamento pagamento){
        //Instancia o SessionFN
        SessionFN sessionFN = new SessionFN();
        //Pega o Cobrador do Ad pela Sessao
        this.getCobradorManager().setCobrador((Cobrador) sessionFN.getSession("Cobrador"));
        //Acopla o Cobrador ao Pagamento
        pagamento.setCobrador(this.getCobradorManager().getCobrador());
        //Colocar no pagamento o valor da comissao do cobrador certo a taxa definida na Entidade Cobrador
        pagamento.setComissaocobrador((this.getCobradorManager().getCobrador().getComissao() * pagamento.getValor()) / 100);
        //Adiciona o Pagamento ao Cobrador
        this.getCobradorManager().getCobrador().getPagamento().add(pagamento);
        //Atualiza o Cobrador
        this.getCobradorManager().getCDAO().atualizar(this.getCobradorManager().getCobrador());
    }
    
    public void criarCobradorPadrao(){
        if(this.getCobradorManager().getCDAO().recuperarTodos().isEmpty()){
            this.getCobradorManager().setCobrador(new Cobrador());
            this.getCobradorManager().getCobrador().setNome("Agencia");
            this.getCobradorManager().getCDAO().salvar(this.getCobradorManager().getCobrador());
        }
    }
}
