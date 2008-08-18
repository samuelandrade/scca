package br.com.copal.util;

import br.com.copal.entity.Boleto;
import br.com.copal.entity.ParcelasBoleto;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;

/**
 *
 * @author Jeyson
 */
public class BoletoServlet extends HttpServlet{
    
     
    /** Creates a new instance of BoletoServlet */
    public BoletoServlet() {
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        Date date = new Date();
        HttpSession session = request.getSession();
        Boleto boleto = new Boleto();
        boleto = (Boleto)session.getAttribute("Boleto");
        JBoletoBean jBoletoBean = new JBoletoBean();
        jBoletoBean.setDataDocumento("31/07/2008");
        jBoletoBean.setDataProcessamento("31/07/2008");
        //jBoletoBean.setDataDocumento(boleto.getDataDocumento().toString());
        //jBoletoBean.setDataProcessamento(boleto.getDataProcessamento().toString());
        
        jBoletoBean.setCedente("Copal Ltda");
        
        jBoletoBean.setNomeSacado(boleto.getAvon().getNome());
        jBoletoBean.setEnderecoSacado(boleto.getAvon().getEndereco());
        jBoletoBean.setBairroSacado(boleto.getAvon().getBairro());
        jBoletoBean.setCidadeSacado(boleto.getAvon().getCidade());
        jBoletoBean.setUfSacado(boleto.getAvon().getUf());
        jBoletoBean.setCepSacado(boleto.getAvon().getCep());
        jBoletoBean.setCpfSacado(boleto.getAvon().getCpf());
        
        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO PAGAVEL EM QUALQUER AGENCIA BANCARIA");
        //jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NA CAIXA ECONOMICA");
        
        Vector descricoes = new Vector();
        descricoes.add(boleto.getTexto().getTexto());
        //descricoes.add();
        //descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
        //descricoes.add("Extra - teste de descricao4 - R$ 78,90");
        jBoletoBean.setDescricoes(descricoes);
        
        jBoletoBean.setInstrucao1(boleto.getInstrucao1());
        jBoletoBean.setInstrucao2(boleto.getInstrucao2());
        jBoletoBean.setInstrucao3(boleto.getInstrucao3());
        jBoletoBean.setInstrucao4(boleto.getInstrucao4());
        
        jBoletoBean.setAgencia("0022");
        jBoletoBean.setContaCorrente("506262");
        jBoletoBean.setDvContaCorrente("9");
        
        jBoletoBean.setCarteira("88"); //pode ser 80 a 89
        jBoletoBean.setCodigoOperacao("870");
        jBoletoBean.setCodigoFornecidoAgencia("00000049");
        jBoletoBean.setDvCodigoFornecidoAgencia("05");
        
        jBoletoBean.setNossoNumero(boleto.getIdBoleto().toString(),14);
        jBoletoBean.setNoDocumento(boleto.getNoDocumento());
        jBoletoBean.setValorBoleto("5.00");
        List<String> parcelas = new ArrayList();
        for(Iterator i = boleto.getParcelas().iterator(); i.hasNext();){
            ParcelasBoleto parcelai = (ParcelasBoleto) i.next();
            BigDecimal valorP = new BigDecimal(parcelai.getValorParcela());
            valorP.setScale(2,BigDecimal.ROUND_HALF_EVEN);
            parcelai.setValorParcela(valorP.doubleValue());
            parcelas.add(String.valueOf(parcelai.getValorParcela()));
        }
        jBoletoBean.setParcelas(parcelas);
        //jBoletoBean.setValorBoleto(String.valueOf(boleto.getValorboleto()));
        //jBoletoBean.setDataVencimento(boleto.getDataVencimento().toString());
        jBoletoBean.setDataVencimento("05/08/2008");
        Generator generator = new PDFGenerator(jBoletoBean, JBoleto.CAIXA_ECONOMICA);
        JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.CAIXA_ECONOMICA);
        jBoleto.addBoleto();
        
        jBoleto.closeBoleto(getServletContext().getRealPath("boleto.pdf"));
        
        URL url = request.getSession().getServletContext().getResource("/boleto.pdf");
        System.out.println("URL: "+url);
        BufferedInputStream leitor = new BufferedInputStream(url.openStream(), 4*1024);
        ServletOutputStream escritor = response.getOutputStream();
        
        byte[]buffer = new byte[4 * 1024];
        int size;
        
        while((size = leitor.read(buffer, 0, buffer.length)) != -1 ){
            escritor.write(buffer, 0, size);
        }
        
        escritor.close();
        leitor.close();
        
        
    }
    
}
