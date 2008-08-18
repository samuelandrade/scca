package br.com.copal.SUDI;

import br.com.copal.DAO.InstrucaoBoletoDAO;
import br.com.copal.entity.InstrucaoBoleto;
import java.util.List;

/**
 *
 * @author Jeyson
 */
public class InstrucaoBoletoSUDI extends ObjectSUDI implements InstrucaoBoletoDAO {
    
    /** Creates a new instance of InstrucaoSUDI */
    public InstrucaoBoletoSUDI() {
    }

    public void salvar(InstrucaoBoleto instrucaoBoleto) {
        super.salvar(instrucaoBoleto);
    }

    public void remover(InstrucaoBoleto instrucaoBoleto) {
        super.remover(instrucaoBoleto);
    }

    public InstrucaoBoleto atualizar(InstrucaoBoleto instrucaoBoleto) {
        return (InstrucaoBoleto) super.atualizar(instrucaoBoleto);
    }

    public List recuperarTodos() {
        return super.namedQueryListNone("InstrucaoBoleto.recuperarTodos");
    }

    public InstrucaoBoleto recuperarporId(int id) {
        return (InstrucaoBoleto)super.namedQueryListOne("InstrucaoBoleto.recuperarPorId","id",id);
    }
    
}
