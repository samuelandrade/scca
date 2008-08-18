/*
 * AvonFN.java
 *
 * Created on 29 de Fevereiro de 2008, 14:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.MB.AvonManager;
import br.com.copal.entity.Avon;
import br.com.copal.entity.Cobrador;
import br.com.copal.util.FileUploadServlet;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author Nhandeara
 */

public class AvonFN extends SessionFN{
    
    private AvonManager avonManager; 
    private RelacaoFN relacaoFN;
    
    public AvonFN(){
        this.setAvonManager(new AvonManager(this));
    }
    
    public AvonFN(AvonManager aM) {
        this.setAvonManager(aM);
    }
    
    public AvonManager getAvonManager() {
        return avonManager;
    }

    public void setAvonManager(AvonManager avonManager) {
        this.avonManager = avonManager;
    }
    
    public void salvarRemessa() throws ServletException, IOException{
        relacaoFN = new RelacaoFN();
        relacaoFN.instanciarTratamentoUploadFN();
        relacaoFN.getUploadFN().terminarUpload(FileUploadServlet.requestStatic,FileUploadServlet.uploadedItemsStatic,FileUploadServlet.uploadStatic,FileUploadServlet.fileItemStatic,FileUploadServlet.filePathStatic);
    }
    
    public void atualizarAdporAvon(){
        RelacaoFN relacaoFN = new RelacaoFN();
        relacaoFN.instanciarAdFN();
        for(int i = 0; i < this.getAvonManager().getAvon().getAdLista().size();i++){
            relacaoFN.getAdFN().atualizarSaldo(this.getAvonManager().getAvon().getAdLista().get(i));
        }
    }
    
    public void selecionarCobrador(Object cobrador){
        Cobrador cob = (Cobrador) cobrador;
    }
    
}
