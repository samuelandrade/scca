package br.com.copal.SUDI;

import br.com.copal.DAO.AdDAO;
import br.com.copal.entity.Ad;
import java.util.List;

/**
 *
 * @author Jeyson
 */

public class AdSUDI extends ObjectSUDI implements AdDAO {
    
    public AdSUDI() {}

    //Metodos Padrões
    public void salvar(Ad ad) {
        super.salvar(ad);
    }

    public Ad atualizar(Ad ad) {
        return (Ad)super.atualizar(ad);
    }

    public void remover(Ad ad) {
        super.remover(ad);
    }
    
    // Pesquisas de Lista
    public List recuperarTodos() {
        List ls =  super.namedQueryListNone("Ad.recuperarTodos"); 
        return ls;
    }

    public List recuperarCampanha(Integer cp) {
        return super.namedQueryListOne("Ad.recuperarPorCp","cp",cp); 
    }
    
    public List recuperarPorAviso(Integer avon_aviso) {
       return super.namedQueryListOne("Ad.recuperarPorAviso","avon_aviso",avon_aviso); 
    }
    
    // Pesquisas de Valor
  
    // Pesquisas de Objetos
     public Ad recuperarPorAvisoDebito(Integer idad) {
         Ad adResultado = (Ad)super.namedQueryObjectOne("Ad.recuperarPorIdAd","idad",idad); 
         return adResultado;
    }  
}
