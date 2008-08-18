/*
 * CaixaItemPadraoFN.java
 *
 * Created on 6 de Maio de 2008, 07:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.MB.CaixaItemPadraoManager;

/**
 *
 * @author Nhandeara
 */
public class CaixaItemPadraoFN {
    
    private CaixaItemPadraoManager ciMB;
    
    
    public CaixaItemPadraoFN(){
      this.setCiMB(new CaixaItemPadraoManager());  
    }
    
    public CaixaItemPadraoFN(CaixaItemPadraoManager cM) {
      this.setCiMB(cM);
    }  

    public CaixaItemPadraoManager getCiMB() {
        return ciMB;
    }

    public void setCiMB(CaixaItemPadraoManager ciMB) {
        this.ciMB = ciMB;
    }
    
    public void gerarCaixaItem(){
        RelacaoFN relacaoFN = new RelacaoFN();
        relacaoFN.instanciarCaixaItemFN();
        relacaoFN.getCaixaItemFN().getCaixaitemManager().getCaixaItem().setNome(this.getCiMB().getCaixaItemPadrao().getNome());
        relacaoFN.getCaixaItemFN().getCaixaitemManager().getCaixaItem().setDescricao(this.getCiMB().getCaixaItemPadrao().getDescricao());
        relacaoFN.getCaixaItemFN().getCaixaitemManager().getCDAO().salvar(relacaoFN.getCaixaItemFN().getCaixaitemManager().getCaixaItem());
    }
}
