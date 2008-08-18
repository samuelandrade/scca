/*
 * SituacaoFN.java
 *
 * Created on 26 de Julho de 2008, 09:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.MB.SituacaoManager;
import br.com.copal.entity.Situacao;

/**
 *
 * @author Silva
 */

public class SituacaoFN {
    
    private SituacaoManager situacaoManager;
    
    /** Creates a new instance of SituacaoFN */
    public SituacaoFN() {
        this.setSituacaoManager(new SituacaoManager(this));
        this.criarSituacoesPadrao();
    }
    
    public SituacaoFN(SituacaoManager sManager) {
        this.setSituacaoManager(sManager);
    }

    private void criarSituacoesPadrao() {
        if(this.getSituacaoManager().getSDAO().recuperarTodos().isEmpty()){
            this.getSituacaoManager().setSituacao(new Situacao());
            this.getSituacaoManager().getSituacao().setNomeSituacao("Imprimir");
            this.getSituacaoManager().getSituacao().setDescricaoSituacao("Avisos para impressão");
            this.getSituacaoManager().getSDAO().salvar(this.getSituacaoManager().getSituacao());
            this.getSituacaoManager().setSituacao(new Situacao());
            this.getSituacaoManager().getSituacao().setNomeSituacao("Cobrável");
            this.getSituacaoManager().getSituacao().setDescricaoSituacao("Aviso em situação cobrável");
            this.getSituacaoManager().getSDAO().salvar(this.getSituacaoManager().getSituacao());
            this.getSituacaoManager().setSituacao(new Situacao());
            this.getSituacaoManager().getSituacao().setNomeSituacao("Incobrável");
            this.getSituacaoManager().getSituacao().setDescricaoSituacao("Aviso em situação incobrável");
            this.getSituacaoManager().getSDAO().salvar(this.getSituacaoManager().getSituacao());
            this.getSituacaoManager().setSituacao(new Situacao());
            this.getSituacaoManager().getSituacao().setNomeSituacao("SPC");
            this.getSituacaoManager().getSituacao().setDescricaoSituacao("Aviso mandado ao SPC");
            this.getSituacaoManager().getSDAO().salvar(this.getSituacaoManager().getSituacao());
        }
    }

    public SituacaoManager getSituacaoManager() {
        return situacaoManager;
    }

    public void setSituacaoManager(SituacaoManager situacaoManager) {
        this.situacaoManager = situacaoManager;
    }
}
