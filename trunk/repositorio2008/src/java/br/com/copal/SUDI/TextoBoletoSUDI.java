package br.com.copal.SUDI;

import br.com.copal.DAO.TextoBoletoDAO;
import br.com.copal.entity.TextoBoleto;
import java.util.List;

/**
 *
 * @author Jeyson
 */
public class TextoBoletoSUDI extends ObjectSUDI implements TextoBoletoDAO{
    
    /** Creates a new instance of TextoBoletoSUDI */
    public TextoBoletoSUDI() {
    }

    public void salvar(TextoBoleto textoBoleto) {
        super.salvar(textoBoleto);
    }

    public void remover(TextoBoleto textoBoleto) {
        super.remover(textoBoleto);
    }

    public TextoBoleto atualizar(TextoBoleto textoBoleto) {
       return(TextoBoleto) super.atualizar(textoBoleto);
    }

    public List recuperarTodos() {
       return super.namedQueryListNone("TextoBoleto.recuperarTodos");
    }

    public TextoBoleto recuperarporId(int id) {
        return (TextoBoleto)super.namedQueryListOne("TextoBoleto.recuperarPorId","id",id);
    }
    
}
