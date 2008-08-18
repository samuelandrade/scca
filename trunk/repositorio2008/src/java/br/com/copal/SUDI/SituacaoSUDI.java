package br.com.copal.SUDI;

import br.com.copal.DAO.SituacaoDAO;
import br.com.copal.entity.Situacao;
import java.util.List;

/**
 *
 * @author Jean
 */
public class SituacaoSUDI extends ObjectSUDI implements SituacaoDAO {
    
    /** Creates a new instance of SituacaoSUDI */
    public SituacaoSUDI() {
    }

    public void salvar(Situacao situacao) {
        super.salvar(situacao);
    }

    public void remover(Situacao situacao) {
        super.remover(situacao);
    }

    public Situacao atualizar(Situacao situacao) {
       return (Situacao)super.atualizar(situacao);
    }

    public List recuperarTodos() {
        List ls = super.namedQueryListNone("Situacao.recuperarTodos");
        return ls;
    }

    public Situacao recuperarporId(int idSituacao) {
        return (Situacao) super.namedQueryListOne("Situacao.recuperarPorId","idSituacao",idSituacao);         
    }
    
}
