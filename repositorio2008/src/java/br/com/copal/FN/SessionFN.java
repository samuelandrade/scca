/*
 * SessionFN.java
 *
 * Created on 29 de Fevereiro de 2008, 13:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nhandeara
 */

public class SessionFN {
    
    private FacesContext context;
    private HttpSession session;
    
    /** Creates a new instance of SessionFN */
    public SessionFN() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(true);
    }
    
    public Object getSession(String flag){
        return session.getAttribute(flag);
    }
    
    public void setSession(Object object, String flag){
        session.setAttribute(flag,object);
    }
} 
