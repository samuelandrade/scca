/*
 * CaixaItemPadraoManager.java
 *
 * Created on 6 de Maio de 2008, 07:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.MB;

import br.com.copal.DAO.CaixaItemPadraoDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.FN.CaixaItemPadraoFN;
import br.com.copal.entity.CaixaItemPadrao;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/*
 * @author Nhandeara
 */

public class CaixaItemPadraoManager {
    
    private CaixaItemPadrao caixaitempadrao;
    private DataModel caixaitempadraoModel;
    private CaixaItemPadraoDAO ciDAO;
    private CaixaItemPadraoFN ciFN;
    
    /** Creates a new instance of CaixaItemPadraoManager */
    public CaixaItemPadraoManager() {
        this.setCaixaItemPadrao(new CaixaItemPadrao());
        this.setCiDAO(FactoryDAO.criarCaixaItemPadraoDAO());
        this.setCiFN(new CaixaItemPadraoFN(this));
    }
    
     public CaixaItemPadraoManager(CaixaItemPadraoFN caixaitempadraoFN) {
        this.setCaixaItemPadrao(new CaixaItemPadrao());
        this.setCiDAO(FactoryDAO.criarCaixaItemPadraoDAO());
        this.setCiFN(caixaitempadraoFN);
    }
    
    public CaixaItemPadrao getCaixaItemPadrao() {
        return caixaitempadrao;
    }

    public void setCaixaItemPadrao(CaixaItemPadrao caixaitempadrao) {
        this.caixaitempadrao = caixaitempadrao;
    }

    public CaixaItemPadraoDAO getCiDAO() {
        return ciDAO;
    }

    public void setCiDAO(CaixaItemPadraoDAO ciDAO) {
        this.ciDAO = ciDAO;
    }

    public CaixaItemPadraoFN getCiFN() {
        return ciFN;
    }

    public void setCiFN(CaixaItemPadraoFN ciFN) {
        this.ciFN = ciFN;
    }
    
    //Metodo de Model
     public DataModel getListarCaixaItemPadrao() {
        caixaitempadraoModel = new ListDataModel(ciDAO.recuperarTodos());
        return caixaitempadraoModel;
    }
    
    public String salvarCaixaItemPadrao(){
        ciDAO.salvar(caixaitempadrao);
        this.setCaixaItemPadrao(new CaixaItemPadrao());
        return "listarCaixaItemPadrao";
    }
   
    public String criarCaixaItem(){
        caixaitempadrao = (CaixaItemPadrao) caixaitempadraoModel.getRowData();
        this.getCiFN().gerarCaixaItem();
        return "listarCaixaItem";
    }
}
