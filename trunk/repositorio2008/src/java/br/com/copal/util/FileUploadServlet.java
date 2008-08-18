/*
 * FileUploadServlet.java
 *
 * Created on 3 de Março de 2008, 07:14
 */

package br.com.copal.util;

//import br.com.copal.FN.SessionFN;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Nhandeara
 * @version
 */
public class FileUploadServlet extends HttpServlet implements Servlet {
    private static final long serialVersionUID = 274693677625051632L;
    public static HttpServletRequest requestStatic;
    public static List uploadedItemsStatic;
    public static ServletFileUpload uploadStatic;
    public static FileItem fileItemStatic;
    public static String filePathStatic;
    
    public static ArrayList<ArrayList> linhaStatic;
            
    //public SessionFN sessao = new SessionFN();
     
    public FileUploadServlet(){
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        FileUploadListener listener = null;
        StringBuffer buffy = new StringBuffer();
        long bytesRead = 0,
                contentLength = 0;
        //Assegurar que a Sessao foi Iniciada
        if(session == null){
            return;
        } else if(session != null){
            //Pega o Listener da Sessao
            listener = (FileUploadListener) session.getAttribute("LISTENER");
            //confirma se o Listener foi pego
            if(listener == null){
                return;
            } else{
                bytesRead = listener.getBytesRead();
                contentLength = listener.getContentLength();
            }
        }
        response.setContentType("text/xml");
        
        buffy.append("<?xml version = \"1.0\"encoding=\"ISO-8859-1\"?>\n");
        buffy.append("<response>\n");
        buffy.append("\t<bytes_read>" + bytesRead + "</bytes_read>\n");
        buffy.append("\t<content_length>" + contentLength + "</content_length>\n");
        
        if(bytesRead == contentLength){
            buffy.append("\t<finished />\n");
            session.setAttribute("LISTENER",null);
        } else{
            long percentComplete = ((100 * bytesRead) / contentLength);
            buffy.append("\t<percent_complete>" + percentComplete + "</percent_complete>\n");
        }
        
        buffy.append("</response>\n");
        
        out.println(buffy.toString());
        out.flush();
        out.close();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        FileUploadListener listener = new FileUploadListener();
        HttpSession session = request.getSession();
        session.setAttribute("LISTENER",listener);
        upload.setProgressListener(listener);
        List uploadedItems = null;
        FileItem fileItem = null;
        String filePath = "c:\\teste";
        requestStatic = request;
        try {
            this.passaItem(request,upload);
        } catch (FileUploadException ex) {
            ex.printStackTrace();
        }
        //uploadedItemsStatic = uploadedItems;
        uploadStatic = upload;
        fileItemStatic = fileItem;
        filePathStatic = filePath;
        
        //this.terminarUpload(request,uploadedItems,upload,fileItem,filePath);
        
    }
    
    public void passaItem(HttpServletRequest request, ServletFileUpload upload) throws FileUploadException{
        uploadedItemsStatic = upload.parseRequest(request);
    }    
}
