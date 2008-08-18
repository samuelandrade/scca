/*
 * RelatorioAvon.java
 *
 * Created on 10 de Junho de 2008, 11:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.relatorios;

import br.com.copal.DAO.PagamentoDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.entity.Avon;
import br.com.copal.entity.Pagamento;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nhandeara
 */
public class RelatorioPagamentos {
    
    /** Creates a new instance of RelatorioAvon */
    public RelatorioPagamentos() {}
    
    private static PagamentoDAO pDAO = FactoryDAO.criarPagamentoDAO();
    private static List <Pagamento> listPagamento;
    
    public static void prestacaoConta(ServletContext sc, HttpServletResponse response,String p1,String p2, String p3) {
        try {
            Date dataIni = new Date();
            dataIni.setTime(Long.valueOf(p1));
            Date dataFim = new Date();
            dataFim.setTime(Long.valueOf(p2));
            //Envia para uma Lista a Consulta feita pelo DAO
            listPagamento = pDAO.recuperarPorCobradorePeriodo(dataFim,dataIni,p3);
            //Adiciona a Lista para um JasperDataSource   
            JRDataSource jrds = new JRBeanCollectionDataSource(listPagamento);
            // parametros do relatorio
            HashMap parameters = new HashMap();
            // lendo arquivo jasper
            File reportFile = new File(sc.getRealPath("/rel/PC.jasper"));
            //Inicia um Array de Bytes
            byte[] bytes = null;
            try {
                //Cria um Relatorio em PDF com o DataSource com base no Modelo do .jasper
                bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, jrds);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            //
            if (bytes != null && bytes.length > 0) {
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}