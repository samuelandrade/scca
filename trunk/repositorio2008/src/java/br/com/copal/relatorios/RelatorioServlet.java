/*
 * RelatorioServlet.java
 *
 * Created on 12 de Junho de 2008, 07:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.relatorios;

import java.io.*;
import java.net.*;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Nhandeara
 */
public class RelatorioServlet extends HttpServlet {
    
    private ServletContext sc;
    private String jsp = "";
    
   
    
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sc = config.getServletContext();
    }
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
     
    public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String classe = request.getParameter("classe");
        String pesquisa = request.getParameter("pesquisa");
        String p1 = "";
        String p2 = "";
        String p3 = "";
        
        if(classe.equals("avon")){
            if(pesquisa.equals("todos")){
                RelatorioAvon.relateTodos(sc,request,response);
            }
            else if(pesquisa.equals("cobrador")){
                p1 = request.getParameter("p1");
                RelatorioAvon.relatePorCobrador(sc,response,p1);
            }
            else if(pesquisa.equals("aviso")){
                p1 = request.getParameter("p1");
                RelatorioAvon.relatePorAviso(sc,response,p1);
            }
            else if(pesquisa.equals("relcobrador")){
                p1 = request.getParameter("p1");
                p2 = request.getParameter("p2");
                RelatorioAvon.relateRelAvisos(sc,response,p1,p2);
            }
            else if(pesquisa.equals("liquidado")){
                RelatorioAvon.liquidado(sc,request,response);
            } 
            else if(pesquisa.equals("vencidos")){
                p1 = request.getParameter("p1");
                p2 = request.getParameter("p2");
                p3 = request.getParameter("p3");
                RelatorioAvon.vencidos(sc,response,p1,p2,p3);
            }
        }
        else if(classe.equals("pagamento")){
            if(pesquisa.equals("pconta")){
                p1 = request.getParameter("p1");
                p2 = request.getParameter("p2");
                p3 = request.getParameter("p3");
                RelatorioPagamentos.prestacaoConta(sc,response,p1,p2,p3);
            }
        }
        else if(classe.equals("ad")){
            if(pesquisa.equals("SPC")){
                RelatorioAd.spc(sc,request,response);
            }
        }
        
        //Redirecionando pagina
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
}    
