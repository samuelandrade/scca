/*
 * CaixaItemFN.java
 *
 * Created on 29 de Fevereiro de 2008, 15:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.MB.CaixaItemManager;
import br.com.copal.entity.Caixa;
import br.com.copal.entity.CaixaItem;
import br.com.copal.entity.Pagamento;

/**
 *
 * @author Nhandeara
 */
public class CaixaItemFN {
    
    private CaixaItemManager caixaitemManager;

    public CaixaItemFN(){
      this.setCaixaitemManager(new CaixaItemManager());  
    }
    
    public CaixaItemFN(CaixaItemManager cM) {
      this.setCaixaitemManager(cM);
    }  

    public CaixaItemManager getCaixaitemManager() {
        return caixaitemManager;
    }

    public void setCaixaitemManager(CaixaItemManager caixaitemManager) {
        this.caixaitemManager = caixaitemManager;
    }
    
    //Metodo para se Gerar Automaticamente um CaixaItem com base em um Pagamento
    public void criarCaixaItemPeloPagamento(Pagamento pagamento){
        RelacaoFN relacaoFN = new RelacaoFN();
        //Inicia os FNs para fazer o Relacionamento entre as Funcionalidades
        relacaoFN.instanciarCaixaFN();
        //Inicia um novo CaixaItem
        this.getCaixaitemManager().setCaixaItem(new CaixaItem());
        //Seta o Pagamento dentro do CaixaItem
        this.getCaixaitemManager().getCaixaItem().setPagamento(pagamento);
        //Poe o Valor do Pagamento no campo de Valor de Dinehiro do CaixaItem
        this.getCaixaitemManager().getCaixaItem().setValordin(pagamento.getValor());
        //Seta o Tipo do CaixaItem para Entrada
        this.getCaixaitemManager().getCaixaItem().setTipo(true);
        //Recupera o ultimo Caixa e joga o CaixaItem como parte desse Caixa
        this.getCaixaitemManager().getCaixaItem().setCaixa(relacaoFN.getCaixaFN().getUltimoCaixa());
        //Soma o Valor do CaixaItem (ou seja do Pagamento) no Saldo do Caixa
        relacaoFN.getCaixaFN().modValorSaldo(this.getCaixaitemManager().getCaixaItem(),this.getCaixaitemManager().getCaixaItem().getCaixa(),true);
        //Persiste o CaixaItem
        this.getCaixaitemManager().getCDAO().salvar(this.getCaixaitemManager().getCaixaItem());
        //Atualiza o Caixa
        relacaoFN.getCaixaFN().getCaixaManager().getCDAO().atualizar(this.getCaixaitemManager().getCaixaItem().getCaixa());
    }
    
    public void salvarCaixaItem(){  
        SessionFN sessionFN = new SessionFN();
        RelacaoFN relacaoFN = new RelacaoFN();
        relacaoFN.instanciarCaixaFN();
        //Verifica se o Ultimo caixa disponivel n esta finalizado, se estiver cria outro.
        if(relacaoFN.getCaixaFN().getCaixaManager().getCDAO().recuperarPorId(relacaoFN.getCaixaFN().getCaixaManager().getCDAO().recuperarUltimoCaixa()).isFinalizado()){
            relacaoFN.getCaixaFN().getCaixaManager().setCaixa(new Caixa());
            relacaoFN.getCaixaFN().getCaixaManager().getCDAO().salvar(relacaoFN.getCaixaFN().getCaixaManager().getCaixa());
        }
        //Seta o caixaitem no ultimo caixa feito
        this.getCaixaitemManager().getCaixaItem().setCaixa(relacaoFN.getCaixaFN().getUltimoCaixa());
        //Marca se e entrada ou saida
        boolean marcador = Boolean.parseBoolean(String.valueOf(sessionFN.getSession("MarcadorEntSai")));
        this.getCaixaitemManager().getCaixaItem().setTipo(marcador);
        //soma ou subtrae o saldo do caixa certo o tipo do caixaitem(entrada ou saida)
        relacaoFN.getCaixaFN().modValorSaldo(this.getCaixaitemManager().getCaixaItem(),this.getCaixaitemManager().getCaixaItem().getCaixa(),this.getCaixaitemManager().getCaixaItem().isTipo());
        //Persiste o CaixaItem
        this.getCaixaitemManager().getCDAO().salvar(this.getCaixaitemManager().getCaixaItem());
        //Atualiza o Caixa
        relacaoFN.getCaixaFN().getCaixaManager().getCDAO().atualizar(this.getCaixaitemManager().getCaixaItem().getCaixa());
        //Cria um novo CaixaItem
        this.getCaixaitemManager().setCaixaItem(new CaixaItem());
    }
    
    public void removerCaixaItem(CaixaItem c){
        RelacaoFN relacaoFN = new RelacaoFN();
        relacaoFN.instanciarCaixaFN();        
        //soma ou subtrae o saldo do caixa certo o tipo do caixaitem(entrada ou saida)
        relacaoFN.getCaixaFN().modValorSaldo(c,c.getCaixa(),!c.isTipo());
        //apaga o pagamento dentro do CaixaItem
        this.removerPagamentodoCaixaItem(c);
        //apaga o CaixaItem
        this.getCaixaitemManager().getCDAO().remover(c);
    }
    
    //Esse metodo e nescessario para poder deletar um pagamento dentro de um caixa item 
    //burlando a relacao inter-depedente entre o Pagamento e o CaixaItem
    public void removerPagamentodoCaixaItem(CaixaItem c){
        //Metodo so é executado se tiver um pagamento relacionado ao caixaitem
        if(c.getPagamento() != null){
            //Inicia o RelacaoFN, e Instancia o PagamentoFN
            RelacaoFN relacaoFN = new RelacaoFN();
            relacaoFN.instanciarPagamentoFN();
            //aponta o Pagamento dentro do PagamentoFN para o Pagamento dentro do CaixaItem
            relacaoFN.getPagamentoFN().getPagamentoManager().setPagamento(c.getPagamento());
            //anula o pagamento do CaixaItem
            c.setPagamento(null);
            //Atualiza o CaixaItem (agora sem o Pagamento)
            this.getCaixaitemManager().getCDAO().atualizar(c);
            //Remove o Pagamento do Banco
            relacaoFN.getPagamentoFN().getPagamentoManager().getPDAO().remover(relacaoFN.getPagamentoFN().getPagamentoManager().getPagamento());
        }
    }    
}
