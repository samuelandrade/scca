package br.com.copal.SUDI;

import br.com.copal.DAO.BoletoDAO;
import br.com.copal.entity.Boleto;
import java.util.List;

/**
 *
 * @author Jeyson
 */
public class BoletoSUDI extends ObjectSUDI implements BoletoDAO{
    
    /** Creates a new instance of BoletoSUDI */
    public BoletoSUDI() {
    }

    public void salvar(Boleto boleto) {
        super.salvar(boleto);
    }

    public void remover(Boleto boleto) {
        super.remover(boleto);
    }

    public Boleto atualizar(Boleto boleto) {
        return (Boleto)super.atualizar(boleto);
    }

    public List recuperarTodos() {
        return super.namedQueryListNone("Boleto.recuperarTodos");
    }

    public Boleto recuperarporId(int idBoleto) {
        return(Boleto)super.namedQueryListOne("Boleto.recuperarPorId","idBoleto",idBoleto);
    }
    
}
