/*
 * FactoryDAO.java
 *
 * Created on 4 de Dezembro de 2007, 16:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.DAO;

import br.com.copal.SUDI.AvonSUDI;
import br.com.copal.entity.Avon;

import br.com.copal.SUDI.ParcelasBoletoSUDI;
import br.com.copal.entity.ParcelasBoleto;

import br.com.copal.SUDI.BoletoSUDI;
import br.com.copal.entity.Boleto;

import br.com.copal.SUDI.CaixaItemPadraoSUDI;
import br.com.copal.entity.CaixaItem;

import br.com.copal.SUDI.InstrucaoBoletoSUDI;
import br.com.copal.entity.InstrucaoBoleto;

import br.com.copal.SUDI.TextoBoletoSUDI;
import br.com.copal.entity.TextoBoleto;

import br.com.copal.SUDI.AdSUDI;
import br.com.copal.entity.Ad;

import br.com.copal.SUDI.CaixaItemSUDI;
import br.com.copal.entity.CaixaItem;

import br.com.copal.SUDI.CaixaSUDI;
import br.com.copal.entity.Caixa;

import br.com.copal.SUDI.PagamentoSUDI;
import br.com.copal.entity.Pagamento;

import br.com.copal.SUDI.CobradorSUDI;
import br.com.copal.entity.Cobrador;

import br.com.copal.SUDI.SituacaoSUDI;
import br.com.copal.entity.Situacao;

import br.com.copal.SUDI.TemplateBoletoSUDI;
import br.com.copal.entity.TemplateBoleto;
/**
 *
 * @author copal
 */

public class FactoryDAO {
    
    /** Creates a new instance of FactoryDAO */
    public static AvonDAO criarAvonDAO(){
        return new AvonSUDI();
    }
    
    public static AdDAO criarAdDAO(){
        return new AdSUDI();
    }
    public static CaixaDAO criarCaixaDAO(){
        return new CaixaSUDI();
    }
 
    public static CaixaItemDAO criarCaixaItemDAO() {
        return new CaixaItemSUDI();
    }
    
    public static PagamentoDAO criarPagamentoDAO() {
        return new PagamentoSUDI();
    }
    
    public static CobradorDAO criarCobradorDAO(){
        return new CobradorSUDI();
    }
    
    public static CaixaItemPadraoDAO criarCaixaItemPadraoDAO(){
        return new CaixaItemPadraoSUDI();
    }
    public static SituacaoDAO criarSituacaoDAO(){
        return new SituacaoSUDI();
    }
    
    public static BoletoDAO criarBoletoDAO(){
        return new BoletoSUDI();
    }
    
    public static TextoBoletoDAO criarTextoBoletoDAO(){
        return new TextoBoletoSUDI();
    }
    
    public static InstrucaoBoletoDAO criarInstrucaoBoletoDAO(){
        return new InstrucaoBoletoSUDI();
    }
    
    public static TemplateBoletoDAO criarTemplateBoletoDAO(){
        return new TemplateBoletoSUDI();
    }
    
    public static ParcelasBoletoDAO criarParcelasBoletoDAO(){
        return new ParcelasBoletoSUDI();
    }
}
