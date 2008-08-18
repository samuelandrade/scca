/*
 * CaixaFN.java
 *
 * Created on 29 de Fevereiro de 2008, 15:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.MB.CaixaManager;
import br.com.copal.entity.Caixa;
import br.com.copal.entity.CaixaItem;

/**
 *
 * @author Nhandeara
 */
public class CaixaFN {
    
    private CaixaManager caixaManager;
    
    public CaixaFN(){
        this.setCaixaManager(new CaixaManager(this));
        this.criarCaixaPadrao();   
    }
    
    public CaixaFN(CaixaManager cMA) {
        this.setCaixaManager(cMA);
    }

    public CaixaManager getCaixaManager() {
        return caixaManager;
    }

    public void setCaixaManager(CaixaManager caixaManager) {
        this.caixaManager = caixaManager;
    }
    
    public Caixa getUltimoCaixa(){
        //Retorna o ultimo Caixa Feito
        return this.getCaixaManager().getCDAO().recuperarPorId(this.getCaixaManager().getCDAO().recuperarUltimoCaixa());
    }
    
    //metodo para atualizar o valor do Caixa 
    public void modValorSaldo(CaixaItem cI, Caixa cX, boolean Marca){
        //Marca e a booleana que marca se e pra Somar o Valor do CaixaItem (true) ou retirar (false)
        if(Marca){
            //Soma os Valores do Dinheiro e do Cheque do Caixa Item no Caixa, tanto no Geral quanto no Dia
            cX.setSaldodia_cheque    (cX.getSaldodia_cheque()     +  cI.getValorch());
            cX.setSaldodia_dinheiro  (cX.getSaldodia_dinheiro()   +  cI.getValordin());
            cX.setSaldogeral_cheque  (cX.getSaldogeral_cheque()   +  cI.getValorch());
            cX.setSaldogeral_dinheiro(cX.getSaldogeral_dinheiro() +  cI.getValordin());
        } else{
            //Retira os Valores do Dinheiro e do Cheque do Caixa Item no Caixa, tanto no Geral quanto no Dia
            cX.setSaldodia_cheque    (cX.getSaldodia_cheque()     -  cI.getValorch());
            cX.setSaldodia_dinheiro  (cX.getSaldodia_dinheiro()   -  cI.getValordin());
            cX.setSaldogeral_cheque  (cX.getSaldogeral_cheque()   -  cI.getValorch());
            cX.setSaldogeral_dinheiro(cX.getSaldogeral_dinheiro() -  cI.getValordin());
        } 
        //atualiza o Caixa
        this.getCaixaManager().getCDAO().atualizar(cX);
    }
    
     public void criarCaixaPadrao(){
        if(this.getCaixaManager().getCDAO().recuperarTodos().isEmpty()){
            this.getCaixaManager().setCaixa(new Caixa());
            this.getCaixaManager().getCDAO().salvar(this.getCaixaManager().getCaixa());
        }
    }
     
     public void finalizarCaixa(){
         this.getCaixaManager().getCaixa().setFinalizado(true);
         this.getCaixaManager().getCDAO().atualizar(this.getCaixaManager().getCaixa());
     }
}
