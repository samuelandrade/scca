/*
 * PagamentoFN.java
 *
 * Created on 29 de Fevereiro de 2008, 15:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.MB.PagamentoManager;
import br.com.copal.entity.Pagamento;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nhandeara
 */
public class PagamentoFN {
    
    private PagamentoManager pagamentoManager;
    
    public PagamentoFN(){
        this.setPagamentoManager(new PagamentoManager());
    }
    
    public PagamentoFN(PagamentoManager pM) {
        this.setPagamentoManager(pM);
    }
    
    public PagamentoManager getPagamentoManager() {
        return pagamentoManager;
    }
    
    public void setPagamentoManager(PagamentoManager pagamentoManager) {
        this.pagamentoManager = pagamentoManager;
    }
    
    //Esse Metodo e Feito para Salvar o Pagamento, criar um Caixa Item e diminuir o valor do AD
    public void salvarPagamento(){
        //Cria um relacaoFn
        RelacaoFN relacaoFN = new RelacaoFN();
        //Converte a Data do Pagamento (String -> Date)
        //this.getPagamentoManager().getPagamento().setDatapagamento(this.tratarData(this.getPagamentoManager().getDataTemp()));
        //Salva o Pagamento antes de tudo pra se Criar uma ID
        this.getPagamentoManager().getPDAO().salvar(this.getPagamentoManager().getPagamento());
        //Instancia os FNs que seraum nescessarios (CaiaItem, Ad e Cobrador)
        relacaoFN.instanciarCaixaItemFN();
        relacaoFN.instanciarAdFN();
        relacaoFN.instanciarCobradorFN();
        //Chama o caixaitemFN para criar o caixaitem com base no pagamento
        //relacaoFN.getCaixaItemFN().criarCaixaItemPeloPagamento(this.getPagamentoManager().getPagamento());
        //Chama o metodo pra subtrair o valor do pagamento do Ad
        relacaoFN.getAdFN().subtraePagamentoAd(this.getPagamentoManager().getPagamento());
        //Define qual Cobrador recebu o pagamento
        relacaoFN.getCobradorFN().setCobradorPagamento(this.getPagamentoManager().getPagamento());
        //Atualiza o Pagamento com todas as modificaçoes que ele recebeu
        this.getPagamentoManager().getPDAO().atualizar(this.getPagamentoManager().getPagamento());
    }
    
    public void apagarPagamento(){
        RelacaoFN relacaoFN = new RelacaoFN();
        relacaoFN.instanciarCobradorFN();
        relacaoFN.instanciarAdFN();
        
        List<Pagamento> lista = this.getPagamentoManager().getPagamento().getAd().getPagamentos();
        int tamanho = lista.size();
        for(int i = 1; i < tamanho; i++ ){
            if(lista.get(i).getIdrecibo() == getPagamentoManager().getPagamento().getIdrecibo()){
                lista.remove(i);
                tamanho = lista.size();
                
                getPagamentoManager().getPagamento().getAd().setValordebito(
                        AtualizacaoFN.atualizarValor(
                        getPagamentoManager().getPagamento().getAd().getDataad(),
                        new Date(),
                        (  getPagamentoManager().getPagamento().getAd().getPrincipal() +
                           getPagamentoManager().getPagamento().getAd().getEncargos() +
                           getPagamentoManager().getPagamento().getAd().getComissao() )
                        ));
                
                getPagamentoManager().getPagamento().getAd().setDataAtualizacao(new Date());
                
                
                for(int in = 1; in < tamanho; in++){
                    if(lista.get(in) != null){
                        double pagamentoValor =
                                AtualizacaoFN.atualizarValor(
                                lista.get(in).getDatapagamento(),
                                new Date(),
                                lista.get(in).getValor()
                                );
                        
                        getPagamentoManager().getPagamento().getAd().setValordebito(
                                getPagamentoManager().getPagamento().getAd().getValordebito() - pagamentoValor
                                );
                    }
                }
                getPagamentoManager().getPagamento().getAd().getPagamentos().remove(getPagamentoManager().getPagamento());
                relacaoFN.getAdFN().getAdManager().getADAO().atualizar(getPagamentoManager().getPagamento().getAd());
                getPagamentoManager().getPagamento().setAd(null);
                getPagamentoManager().getPagamento().getCobrador().getPagamento().remove(getPagamentoManager().getPagamento());
                relacaoFN.getCobradorFN().getCobradorManager().getCDAO().atualizar(getPagamentoManager().getPagamento().getCobrador());
                getPagamentoManager().getPagamento().setCobrador(null);
            }
        }
    }
    
    public void editarPagamento(){
        RelacaoFN relacaoFN = new RelacaoFN();
        relacaoFN.instanciarCobradorFN();
        relacaoFN.instanciarAdFN();
        
        List<Pagamento> lista = this.getPagamentoManager().getPagamento().getAd().getPagamentos();
        int tamanho = lista.size();
        
        getPagamentoManager().getPagamento().getAd().setValordebito(
                AtualizacaoFN.atualizarValor(
                getPagamentoManager().getPagamento().getAd().getDataad(),
                new Date(),
                (  getPagamentoManager().getPagamento().getAd().getPrincipal() +
                           getPagamentoManager().getPagamento().getAd().getEncargos() +
                           getPagamentoManager().getPagamento().getAd().getComissao() )
                ));
        
        getPagamentoManager().getPagamento().getAd().setDataAtualizacao(new Date());
        
        for(int i = 0; i < tamanho; i++ ){
            double pagamentoValor =
                    AtualizacaoFN.atualizarValor(
                    lista.get(i).getDatapagamento(),
                    new Date(),
                    lista.get(i).getValor()
                    );

            getPagamentoManager().getPagamento().getAd().setValordebito(
                    getPagamentoManager().getPagamento().getAd().getValordebito() - pagamentoValor
                    );
        }
        relacaoFN.getAdFN().getAdManager().getADAO().atualizar(getPagamentoManager().getPagamento().getAd());
    }
    
    public Date tratarData(String dataTexto){
        String dia = dataTexto.substring(0,2);
        String mes = dataTexto.substring(2,4);
        String ano = dataTexto.substring(4,8);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = (Date) formatter.parse(dia + "/" + mes + "/" + ano);
            return date;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
