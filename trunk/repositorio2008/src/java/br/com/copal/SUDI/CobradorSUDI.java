package br.com.copal.SUDI;

import br.com.copal.DAO.CobradorDAO;
import br.com.copal.entity.Cobrador;
import java.util.List;

/**
 *
 * @author Jeyson
 */
public class CobradorSUDI extends ObjectSUDI implements CobradorDAO {
    
    /** Creates a new instance of CobradorSUDI */
    public CobradorSUDI() {
    }

    public void salvar(Cobrador cobrador) {
        super.salvar(cobrador);
    }

    public void remover(Cobrador cobrador) {
        super.remover(cobrador);
    }

    public Cobrador atualizar(Cobrador cobrador) {
        return (Cobrador) super.atualizar(cobrador);
    }
    
    public List recuperarTodos(){
        return super.namedQueryListNone("Cobrador.recuperarTodos");
    }
      
    public Cobrador recuperarporId(int id){
        return (Cobrador)super.namedQueryObjectOne("Cobrador.recuperarPorId","idcobrador",id);
    }
    
    
    
}
