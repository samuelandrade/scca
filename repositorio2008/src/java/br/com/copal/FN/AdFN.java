/*
 * AdFN.java
 *
 * Created on 29 de Fevereiro de 2008, 15:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.MB.AdManager;
import br.com.copal.entity.Ad;
import br.com.copal.entity.Pagamento;
import java.util.Date;

/*
 * @author Nhandeara
 */

public class AdFN {
    
    private AdManager adManager;
    
    /** Creates a new instance of AdFN */
    public AdFN() {
        this.setAdManager(new AdManager(this));
    }
    
    public AdFN(AdManager adManager){
        this.setAdManager(adManager);
    }
    
    public AdManager getAdManager() {
        return adManager;
    }
    
    public void setAdManager(AdManager adManager) {
        this.adManager = adManager;
    }
    
    public Double atualizaPrincipal(Ad ad, Date d1, Date d2){      
        double juros = AtualizacaoFN.atualizarValor(d1,d2,ad.getPrincipal());
        ad.setJuros( juros - ad.getPrincipal());
        ad.setValordebito(ad.getPrincipal() + ad.getComissao() + ad.getEncargos() + ad.getJuros());
        
        for(int i = 0; i < ad.getPagamentos().size(); i++){
            Double vlrPgt = AtualizacaoFN.atualizarValor(ad.getPagamentos().get(i).getDatapagamento(),d2,ad.getPagamentos().get(i).getValor());
            ad.setValordebito(ad.getValordebito() - vlrPgt);
        }
        
        return ad.getValordebito();
    }
    
    
    //Metodo para retirar um valor do Ad certo o pagamento
    public void subtraePagamentoAd(Pagamento pagamento){
        //Instancia o FN que faz a Atualizaçao dos Valores
        AtualizacaoFN atualizacaoFN = new AtualizacaoFN();
        //Cria o FN que faz o controle de Sessao
        SessionFN sessionFN = new SessionFN();
        //Pega o Ad da Sessao e coloca no Ad do Manager
        this.getAdManager().setAd(pagamento.getAd());
        //Poe na sessao o Cobrador desse Ad
        sessionFN.setSession(this.getAdManager().getAd().getAvon().getCobrador(),"Cobrador");
        //Add o pagamento na lista de pagamentos do Ad
        this.getAdManager().getAd().getPagamentos().add(pagamento);
        //Atualiza o Ad e os Pagamentos e os subtrae
        this.atualizaPrincipal(this.getAdManager().getAd(),this.getAdManager().getAd().getDataAtualizacao(),pagamento.getDatapagamento());
        //Seta o Ad no Pagamento
        pagamento.setAd(this.getAdManager().getAd());
        //Atualiza o AD
        this.getAdManager().getADAO().atualizar(this.getAdManager().getAd());
    }
    
    //Metodo pra Atualizar o Saldo do Ad
    public void atualizarSaldo(Ad ad){
        //Instancia o Fn que faz as Atualizaçoes
        AtualizacaoFN atualizarFN = new AtualizacaoFN();
        //Atualiza o Valor enviado a Data do Ad, a Data de hoje e o Valor do Ad
        this.atualizaPrincipal(ad,ad.getAvon().getDataenvio(),new Date());
        ad.setDataAtualizacao(new Date());
        this.adManager.getADAO().atualizar(ad);
    }
}
