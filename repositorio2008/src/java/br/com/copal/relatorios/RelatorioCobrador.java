package br.com.copal.relatorios;

import br.com.copal.DAO.CobradorDAO;
import br.com.copal.DAO.FactoryDAO;
import br.com.copal.entity.Cobrador;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Jeyson
 */
public class RelatorioCobrador {

   private static List <Cobrador> listCobrador;
    private static CobradorDAO cDAO = FactoryDAO.criarCobradorDAO();
    
    /** Creates a new instance of RelatorioCobrador */
    public RelatorioCobrador() {
    }
    public static void relatarTodos(ServletContext sc, HttpServletRequest request, HttpServletResponse response) {
        try {
            //Envia para uma Lista a Consulta feita pelo DAO
            listCobrador = cDAO.recuperarTodos();
            //Adiciona a Lista para um JasperDataSource   
            JRDataSource jrds = new JRBeanCollectionDataSource(listCobrador);
            // parametros do relatorio
            HashMap parameters = new HashMap();
            // lendo arquivo jasper
            File reportFile = new File(sc.getRealPath("/rel/cobrador.jasper"));
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
