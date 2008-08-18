/*
 * RelatorioAd.java
 *
 * Created on 12 de Junho de 2008, 09:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.relatorios;

import br.com.copal.DAO.AdDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.entity.Ad;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nhandeara
 */
public class RelatorioAd {
    
    /** Creates a new instance of RelatorioAd */
    public RelatorioAd() {
    }
    
    private static List <Ad> listAd;
    private static AdDAO aDAO = FactoryDAO.criarAdDAO();
    
    public static void spc(ServletContext sc,HttpServletRequest request, HttpServletResponse response) {
        try {
            //Envia para uma Lista a Consulta feita pelo DAO
            listAd = aDAO.recuperarTodos();
            //Adiciona a Lista para um JasperDataSource   
            JRDataSource jrds = new JRBeanCollectionDataSource(listAd);
            // parametros do relatorio
            HashMap parameters = new HashMap();
            // lendo arquivo jasper
            File reportFile = new File(sc.getRealPath("/rel/relSPC.jasper"));
            //Inicia um Array de Bytes
            byte[] bytes = null;
            try {
                JasperPrint print = JasperFillManager.fillReport(reportFile.getPath(), parameters, jrds);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                JRXlsExporter exporter = new JRXlsExporter();  

                OutputStream outputfile= new FileOutputStream(new File("c:/spc.xls"));  

                // coding For Excel:  
                JRXlsExporter exporterXLS = new JRXlsExporter();  
                exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);  
                exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile);  
                exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);  
                exporterXLS.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.FALSE); 
                exporterXLS.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);  
                exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);  
                exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE); 
                exporterXLS.exportReport();  
                outputfile.write(output.toByteArray());  
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (bytes != null && bytes.length > 0) {
                response.setContentType("application/xls");
                response.setHeader("Content-disposition","attachment; filename=file.xls");
                response.setContentLength(bytes.length);
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes,0,bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}