/*
 * FacesMessages.java
 *
 * Created on 25 de Abril de 2007, 15:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author jean
 */
public class FacesMessages {
    
    /** Creates a new instance of FacesMessages */
    public FacesMessages() {
    }
    
    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("successInfo", facesMsg);
    }
    
    public static void addAlertMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, "");
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("alertInfo", facesMsg);
    }
}//end class FacesMessages
