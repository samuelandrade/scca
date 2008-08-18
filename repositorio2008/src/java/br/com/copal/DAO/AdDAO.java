package br.com.copal.DAO;

import br.com.copal.entity.Ad;
import java.util.List;

public interface AdDAO {
    
    //Metodos Padrões
    public void salvar(Ad ad);
    public Ad atualizar(Ad ad);
    public void remover(Ad ad);
    
    // Pesquisas de Lista
    public List recuperarTodos();
    public List recuperarCampanha(Integer cp);
    public List recuperarPorAviso(Integer avon_aviso); 
    
    // Pesquisas de Valor
    
    // Pesquisas de Objetos
    public Ad recuperarPorAvisoDebito(Integer idad);
       
}

