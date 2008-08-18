/*
 * RelatorioAvon.java
 *
 * Created on 10 de Junho de 2008, 11:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.relatorios;



import br.com.copal.DAO.AvonDAO;
import br.com.copal.DAO.AdDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.entity.Avon;
import br.com.copal.entity.Ad;
import br.com.copal.entity.Pagamento;
import br.com.copal.util.FormatacaoValores;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nhandeara
 */
public class RelatorioAvon {
    
    /** Creates a new instance of RelatorioAvon */
    public RelatorioAvon() {}
    
    private static AvonDAO aDAO = FactoryDAO.criarAvonDAO();
    private static AdDAO adDAO = FactoryDAO.criarAdDAO();
    private static List <Avon> listAvon;
    private static List <List <Ad>> listAd;
    
    public static void relateTodos(ServletContext sc,HttpServletRequest request, HttpServletResponse response) {
        try {
            //Envia para uma Lista a Consulta feita pelo DAO
            listAvon = aDAO.recuperarTodos2("Agencia");
            //listAd = adDAO.recuperarTodos();
            HashMap MapAd = new HashMap();
            List <JRDataSource> ads = new ArrayList();          
            List <Double> valoresTotal = new ArrayList();
            valoresTotal.add(null);
            ads.add(null);
            for(int i = 0; i < listAvon.size(); i++){
                double valorTotal = 0.00;
                JRDataSource adjrds = new JRBeanCollectionDataSource(listAvon.get(i).getAdLista());
                for(int in = 0; in < listAvon.get(i).getAdLista().size(); in++){
                    valorTotal = valorTotal + listAvon.get(i).getAdLista().get(in).getValordebito();
                }
                valoresTotal.add(valorTotal);
                ads.add(adjrds);
            }
            
            //Adiciona a Lista para um JasperDataSource   
            JRDataSource jrds = new JRBeanCollectionDataSource(listAvon);
            JRDataSource adjrds = new JRBeanCollectionDataSource(listAd);
            // parametros do relatorio
            HashMap parameters = new HashMap();
            parameters.put("SUBREPORT_DIR",sc.getRealPath("/rel/ad.jasper"));
            parameters.put("LISTA_AD",ads); 
            parameters.put("VALORES_TOTAL",valoresTotal);
            // lendo arquivo jasper
            File reportFile = new File(sc.getRealPath("/rel/avisoAvon.jasper"));
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
       
    public static void liquidado(ServletContext sc,HttpServletRequest request, HttpServletResponse response) {
        try {
            //Envia para uma Lista a Consulta feita pelo DAO
            listAvon = aDAO.recuperarTodos();
            //Adiciona a Lista para um JasperDataSource   
            JRDataSource jrds = new JRBeanCollectionDataSource(listAvon);
            // parametros do relatorio
            HashMap parameters = new HashMap();
            // lendo arquivo jasper
            File reportFile = new File(sc.getRealPath("/rel/controleRevendendora.jasper"));
            //Inicia um Array de Bytes
            byte[] bytes = null;
            try {
                JasperPrint print = JasperFillManager.fillReport(reportFile.getPath(), parameters, jrds);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                JRXlsExporter exporter = new JRXlsExporter();  

                OutputStream outputfile= new FileOutputStream(new File("c:/controleRevendendora.xls"));  

                // coding For Excel:  
                JRXlsExporter exporterXLS = new JRXlsExporter();  
                exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);  
                exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);  
                exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);  
                exporterXLS.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);  
                exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);  
                exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);  
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
    
    public static void vencidos(ServletContext sc, HttpServletResponse response, String p1, String p2,String p3) {
        try {
            FormatacaoValores fVal = new FormatacaoValores(); 
            Date d1 = fVal.tratarData(p1);
            Date d2 = fVal.tratarData(p2);
            //Envia para uma Lista a Consulta feita pelo DAO
            listAvon = aDAO.recuperarVencidos(d1,p3);
            ArrayList<Avon> listAvonOK = new ArrayList();
            ArrayList<Date> listDate = new ArrayList();
            
            for (int i = 0; i < listAvon.size(); i++){
                boolean flag = true;
                for (int in = 0; in < listAvon.get(i).getAdLista().size(); in++){
                    Pagamento maior = null;
                    if(listAvon.get(i).getAdLista().get(in).getPagamentos().size() == 0){
                       if(in == listAvon.get(i).getAdLista().size() - 1 && flag == true){
                            listAvonOK.add(listAvon.get(i));
                            listDate.add(fVal.tratarData("000000"));
                       }
                    }else{
                        for (int ind = 0; ind < listAvon.get(i).getAdLista().get(in).getPagamentos().size(); ind++){
                            if(maior == null){
                                maior = listAvon.get(i).getAdLista().get(in).getPagamentos().get(ind);
                            }else{
                                if(maior.getDatapagamento().after(listAvon.get(i).getAdLista().get(in).getPagamentos().get(ind).getDatapagamento())){
                                    maior = listAvon.get(i).getAdLista().get(in).getPagamentos().get(ind);
                                }
                            }
                        }if(maior.getDatapagamento().before(d2) || maior.getDatapagamento().equals(d2)){
                            listAvonOK.add(listAvon.get(i));
                            listDate.add(maior.getDatapagamento());
                            flag = false;
                        } else{
                            flag = false;
                        }
                    }
                }
            }
            //Adiciona a Lista para um JasperDataSource   
            JRDataSource jrds = new JRBeanCollectionDataSource(listAvonOK);
            // parametros do relatorio
            HashMap parameters = new HashMap();
            parameters.put("DTSPGTS",listDate);
            // lendo arquivo jasper
            File reportFile = new File(sc.getRealPath("/rel/vencidos.jasper"));
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
    
    
    public static void relatePorCobrador(ServletContext sc, HttpServletResponse response,String p1) {
        try {
            //Envia para uma Lista a Consulta feita pelo DAO
            listAvon = aDAO.recuperarTodos2(p1);
            //Adiciona a Lista para um JasperDataSource   
            JRDataSource jrds = new JRBeanCollectionDataSource(listAvon);
            // parametros do relatorio
            HashMap parameters = new HashMap();
            // lendo arquivo jasper
            File reportFile = new File(sc.getRealPath("/rel/avisosNotificador.jasper"));
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
    
    public static void relatePorAviso(ServletContext sc,HttpServletResponse response,String p1) {
        try {
            //Envia para uma Lista a Consulta feita pelo DAO
            listAvon = aDAO.recuperarPorAviso(Integer.parseInt(p1));
            //listAvon = aDAO.recuperarTodos2("Agencia");
            //listAd = adDAO.recuperarTodos();
            HashMap MapAd = new HashMap();
            List <JRDataSource> ads = new ArrayList();          
            List <Double> valoresTotal = new ArrayList();
            valoresTotal.add(null);
            ads.add(null);
            for(int i = 0; i < listAvon.size(); i++){
                double valorTotal = 0.00;
                JRDataSource adjrds = new JRBeanCollectionDataSource(listAvon.get(i).getAdLista());
                for(int in = 0; in < listAvon.get(i).getAdLista().size(); in++){
                    valorTotal = valorTotal + listAvon.get(i).getAdLista().get(in).getValordebito();
                }
                valoresTotal.add(valorTotal);
                ads.add(adjrds);
            }
            
            //Adiciona a Lista para um JasperDataSource   
            JRDataSource jrds = new JRBeanCollectionDataSource(listAvon);
            //JRDataSource adjrds = new JRBeanCollectionDataSource(listAd);
            // parametros do relatorio
            HashMap parameters = new HashMap();
            parameters.put("SUBREPORT_DIR",sc.getRealPath("/rel/ad.jasper"));
            parameters.put("LISTA_AD",ads); 
            parameters.put("VALORES_TOTAL",valoresTotal);
            // lendo arquivo jasper
            File reportFile = new File(sc.getRealPath("/rel/avisoAvon.jasper"));
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
    
    public static void relateRelAvisos(ServletContext sc,HttpServletResponse response, String p1, String p2) {
        try {
            //Envia para uma Lista a Consulta feita pelo DAO
            listAvon = aDAO.recuperarPorStatusCobrador(p1,p2);
            //Adiciona a Lista para um JasperDataSource   
            JRDataSource jrds = new JRBeanCollectionDataSource(listAvon);
            // parametros do relatorio
            HashMap parameters = new HashMap();
            // lendo arquivo jasper
            File reportFile = new File(sc.getRealPath("/rel/avisosNotificador.jasper"));
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
