/*
 * RelacaoFN.java
 *
 * Created on 29 de Fevereiro de 2008, 14:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;


/*
 * @author Nhandeara
 */

/*Funçao do RelacaoFN:
 *  - o RelacaoFn serve para fazer a inteligaçao entre um FN e qualquer outro FN de outra classe que seja nescessario
 *  esse classe existe para eliminar a instancias de DAOs e Entidades de outras classes dentro de MBs de uma certa classe
 */

public class RelacaoFN {
    
    private AdFN adFN;
    private AvonFN avonFN;
    private CaixaFN caixaFN;
    private CaixaItemFN caixaitemFN;
    private CaixaItemPadraoFN caixaitempadraoFN;
    private CobradorFN cobradorFN;
    private PagamentoFN pagamentoFN;
    private TratamentoUploadFN uploadFN;
    private SituacaoFN situacaoFN;
    
    /** Creates a new instance of RelacaoFN */
    public RelacaoFN() {}
        
    public void instanciarAdFN(){
        setAdFN(new AdFN());
    }
    
    public void instanciarSituacaodFN(){
        setSituacaoFN(new SituacaoFN());
    }
    
    public void instanciarAvonFN(){
        setAvonFN(new AvonFN());
    }
    
    public void instanciarCaixaFN(){
        setCaixaFN(new CaixaFN()) ;
    }
    
    public void instanciarCaixaItemFN(){
        setCaixaitemFN(new CaixaItemFN());
    }
    
    public void instanciarCaixaItemPadraoFN(){
        setCaixaitempadraoFN(new CaixaItemPadraoFN());
    }
    
    public void instanciarCobradorFN(){
        setCobradorFN(new CobradorFN());
    }
    
    public void instanciarPagamentoFN(){
        setPagamentoFN(new PagamentoFN());
    }
    
    public void instanciarTratamentoUploadFN(){
        setUploadFN(new TratamentoUploadFN());
    }
    
    public AdFN getAdFN(){
        return adFN;
    }
    
    public AvonFN getAvonFN(){
        return avonFN;
    }
    
    public CaixaFN getCaixaFN(){
        return caixaFN;
    }
    
    public CaixaItemFN getCaixaItemFN(){
        return getCaixaitemFN();
    }
    
    public CobradorFN getCobradorFN(){
        return cobradorFN;
    }
    
    public PagamentoFN getPagamentoFN(){
        return pagamentoFN;
    }

    public TratamentoUploadFN getUploadFN() {
        return uploadFN;
    }

    public CaixaItemPadraoFN getCaixaitempadraoFN() {
        return caixaitempadraoFN;
    }

    public void setAdFN(AdFN adFN) {
        this.adFN = adFN;
    }

    public void setAvonFN(AvonFN avonFN) {
        this.avonFN = avonFN;
    }

    public void setCaixaFN(CaixaFN caixaFN) {
        this.caixaFN = caixaFN;
    }

    public CaixaItemFN getCaixaitemFN() {
        return caixaitemFN;
    }

    public void setCaixaitemFN(CaixaItemFN caixaitemFN) {
        this.caixaitemFN = caixaitemFN;
    }

    public void setCaixaitempadraoFN(CaixaItemPadraoFN caixaitempadraoFN) {
        this.caixaitempadraoFN = caixaitempadraoFN;
    }

    public void setCobradorFN(CobradorFN cobradorFN) {
        this.cobradorFN = cobradorFN;
    }

    public void setPagamentoFN(PagamentoFN pagamentoFN) {
        this.pagamentoFN = pagamentoFN;
    }

    public void setUploadFN(TratamentoUploadFN uploadFN) {
        this.uploadFN = uploadFN;
    }

    public SituacaoFN getSituacaoFN() {
        return situacaoFN;
    }

    public void setSituacaoFN(SituacaoFN situacaoFN) {
        this.situacaoFN = situacaoFN;
    }
}
