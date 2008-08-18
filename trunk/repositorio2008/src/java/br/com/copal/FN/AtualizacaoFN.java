/*
 * AtualizacaoFN.java
 *
 * Created on 24 de Março de 2008, 07:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.entity.Ad;
import br.com.copal.entity.Pagamento;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

/**
 *
 * @author Nhandeara
 */
public class AtualizacaoFN {
    
    
    
    /** Creates a new instance of AtualizacaoFN */
    public AtualizacaoFN() {
    }
    
//    public void atualizarSaldoPagamento(Ad ad, Pagamento pg){       
//        Calendar c1 = Calendar.getInstance();
//        c1.setTime(ad.getDataad());
//        Calendar c2 = Calendar.getInstance(); 
//        c2.setTime(pg.getDatapagamento());
//            
//        long m1 = c1.getTimeInMillis();
//        long m2 = c2.getTimeInMillis();
//        
//        int resultado = (int) ((m2 - m1) / (24*60*60*1000));
//        
//        BigDecimal taxaJuros = new BigDecimal(0.0011);
//        BigDecimal debitoAtual = new BigDecimal(ad.getValordebitoatual());
//        BigDecimal diferencaDias = new BigDecimal(resultado);
//        BigDecimal debitoAtualizado = new BigDecimal(0);
//        BigDecimal pagamento = new BigDecimal(pg.getValor());
//        
//        debitoAtualizado = debitoAtual.multiply(taxaJuros);      
//        double teste = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.multiply(diferencaDias);
//        teste = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.add(debitoAtual);
//        teste = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.subtract(pagamento);
//        teste = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.setScale(2,BigDecimal.ROUND_HALF_EVEN);
//        teste = debitoAtualizado.doubleValue();
//        
//        ad.setValordebitoatual(teste);
//    }
//    
//    public static void atualizarSaldo(Ad ad){       
//        Calendar c1 = Calendar.getInstance();
//        c1.setTime(ad.getDataad());
//        Calendar c2 = Calendar.getInstance(); 
//        c2.setTime(new Date());
//            
//        long m1 = c1.getTimeInMillis();
//        long m2 = c2.getTimeInMillis();
//        
//        int resultado = (int) ((m2 - m1) / (24*60*60*1000));
//        
//        BigDecimal taxaJuros = new BigDecimal(0.0011);
//        BigDecimal debitoAtual = new BigDecimal(ad.getValordebitoatual());
//        BigDecimal diferencaDias = new BigDecimal(resultado);
//        BigDecimal debitoAtualizado = new BigDecimal(0);
//        
//        debitoAtualizado = debitoAtual.multiply(taxaJuros);      
//        double teste = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.multiply(diferencaDias);
//        teste = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.add(debitoAtual);
//        teste = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.setScale(2,BigDecimal.ROUND_HALF_EVEN);
//        teste = debitoAtualizado.doubleValue();
//        
//        ad.setValordebitoatual(teste);
//    }
    
    public static void atualizarSaldo(Ad ad){       
        Calendar c1 = Calendar.getInstance();
        c1.setTime(ad.getDataad());
        Calendar c2 = Calendar.getInstance(); 
        c2.setTime(new Date());
            
        long m1 = c1.getTimeInMillis();
        long m2 = c2.getTimeInMillis();
        
        int resultado = (int) ((m2 - m1) / (24*60*60*1000));
        
        BigDecimal taxaJuros = new BigDecimal(0.0011);
        BigDecimal debitoAtual = new BigDecimal(ad.getValordebito());
        BigDecimal diferencaDias = new BigDecimal(resultado);
        BigDecimal debitoAtualizado = new BigDecimal(0);
        
        debitoAtualizado = debitoAtual.multiply(taxaJuros);      
        double teste = debitoAtualizado.doubleValue();
        debitoAtualizado = debitoAtualizado.multiply(diferencaDias);
        teste = debitoAtualizado.doubleValue();
        debitoAtualizado = debitoAtualizado.add(debitoAtual);
        teste = debitoAtualizado.doubleValue();
        debitoAtualizado = debitoAtualizado.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        teste = debitoAtualizado.doubleValue();
        
        ad.setValordebito(teste);
        ad.setDataAtualizacao(new Date());
    }
    
     public static double atualizarValor(Date d1, Date d2, double valor){       
        //Poe as Datas nos Calendarios, sendo que D2 > D1
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance(); 
        c2.setTime(d2);
        //Calcula quantos MiliSegundos se Passaram desde 01/01/1970    
        long m1 = c1.getTimeInMillis();
        long m2 = c2.getTimeInMillis();
        //Calcula a Diferença entre as datas
        int resultado = (int) ((m2 - m1) / (24*60*60*1000));
        //Poe o Juros Diario
        BigDecimal taxaJuros = new BigDecimal(0.0011);
        double flag = taxaJuros.doubleValue();
        //Poe o Valor atual
        BigDecimal debitoAtual = new BigDecimal(valor);
        debitoAtual = debitoAtual.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        //Por a diferença de dias
        BigDecimal diferencaDias = new BigDecimal(resultado);
        //Inicializa a variavel que vai guardar o valor atualizado
        BigDecimal debitoAtualizado = new BigDecimal(0);
        
        //Multiplica o valorInicial com a Taxa de Juros (tira a porcentagem do Valor)
        debitoAtualizado = debitoAtual.multiply(taxaJuros);
        debitoAtualizado = debitoAtualizado.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        flag = debitoAtualizado.doubleValue();
        //Multiplica a valorAtualizado com a Diferença de dias
        debitoAtualizado = debitoAtualizado.multiply(diferencaDias);
        flag = debitoAtualizado.doubleValue();
        //Soma o Juros com o Valor Inicial
        debitoAtualizado = debitoAtualizado.add(debitoAtual);
        flag = debitoAtualizado.doubleValue();
        //Limita o valor a apenas duas casas depois da virgula
        debitoAtualizado = debitoAtualizado.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        flag = debitoAtualizado.doubleValue();
        
        //retorna o valor atualizado em Double
        return debitoAtualizado.doubleValue();
    }
     //Método mais generalizao para calcular a atualização de um débito no decorrer de um 
     //tempo por um determinado juros
//     public double atualizar(Date dataInicio, Date dataFim, Double valorInicio){
//        Calendar dataInicial = Calendar.getInstance();
//        dataInicial.setTime(dataInicio);
//        Calendar dataFinal = Calendar.getInstance(); 
//        dataFinal.setTime(dataFim);
//            
//        long milliInicial = dataInicial.getTimeInMillis();
//        long milliFinal = dataFinal.getTimeInMillis();
//        
//        int tempoCalcular = (int) ((milliFinal - milliInicial) / (24*60*60*1000));
//        
//        BigDecimal taxaJuros = new BigDecimal(0.0011);
//        BigDecimal debitoInicial = new BigDecimal(valorInicio);
//        BigDecimal diferencaDias = new BigDecimal(tempoCalcular);
//        BigDecimal debitoAtualizado = new BigDecimal(0);
//        
//        debitoAtualizado = debitoInicial.multiply(taxaJuros);      
//        double debitoAtual = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.multiply(diferencaDias);
//        debitoAtual = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.add(debitoInicial);
//        debitoAtual = debitoAtualizado.doubleValue();
//        debitoAtualizado = debitoAtualizado.setScale(2,BigDecimal.ROUND_HALF_EVEN);
//        debitoAtual = debitoAtualizado.doubleValue();
//        
//        return debitoAtual;
//                 
//     }
}
