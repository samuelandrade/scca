package br.com.copal.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Jean
 */
public class FormatacaoValores {
    
    /** Creates a new instance of FormatacaoValores */
    
    
//    public double formatarDecimal(Double valor){
//         
//        //numero = "50.000000";  
//         Double nr = valor;  
//           
//         NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);  
//         nf.setMinimumFractionDigits(2);  
//         nr2 = nf.format(nr);  
//                   
//         System.out.println(nr2);  
//   }
//    
    
     public Date tratarData(String dataTexto){
        
        String dia = "";
        String mes = "";
        String ano = "";
        
        if(dataTexto == null){
            dataTexto = "";
        }
        
        if (dataTexto.length() == 6){
            dia = dataTexto.substring(0,2);
            mes = dataTexto.substring(2,4);
            ano = dataTexto.substring(4,6);
        } else if(dataTexto.length() == 8){
            dia = dataTexto.substring(0,2);
            mes = dataTexto.substring(2,5);
            if(mes.substring(0,1).equals("/")){
                mes = mes.substring(1,3);
            } else {
                mes = mes.substring(0,2);
            }
            ano = dataTexto.substring(6,8);
        } else if (dataTexto.length() == 10){
            dia = dataTexto.substring(0,2);
            mes = dataTexto.substring(3,5);
            ano = dataTexto.substring(8,10);
        }
        
        if(dia.equals("00")){
            dia = "";
            mes = "";
            ano = "";
        } 
        
        if( ano.equals("00")){
            ano = "2000";
        }
         else if( ano.equals("01")){
            ano = "2001";
        }
        else if( ano.equals("02")){
            ano = "2002";
        }
        else if( ano.equals("03")){
            ano = "2003";
        }
        else if( ano.equals("04")){
            ano = "2004";
        }
        else if( ano.equals("05")){
            ano = "2005";
        }
        else if( ano.equals("06")){
            ano = "2006";
        }
        else if( ano.equals("07")){
            ano = "2007";
        }
        else if( ano.equals("08")){
            ano = "2008";
        } else if(!ano.equals("")) {
            ano = "19" + ano;
        }
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = (Date) formatter.parse(dia + "/" + mes + "/" + ano);
            return date;
        } catch (ParseException ex) {}
        return null;
    }
}
