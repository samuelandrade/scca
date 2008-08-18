package br.com.copal.util;

/**
 *
 * @author Jeyson
 */
public class navegadorPag {
    private String centro;
    private String menuSuperior;
    private String menuLateral;
    private String conteudo = "/Avon/listarAvon.xhtml";
    /** Creates a new instance of navegadorPag */
    public navegadorPag() {
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getMenuSuperior() {
        return menuSuperior;
    }

    public void setMenuSuperior(String menuSuperior) {
        this.menuSuperior = menuSuperior;
    }

    public String getMenuLateral() {
        return menuLateral;
    }

    public void setMenuLateral(String menuLateral) {
        this.menuLateral = menuLateral;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
}
