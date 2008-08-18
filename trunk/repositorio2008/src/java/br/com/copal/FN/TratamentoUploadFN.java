/*
 * SessionFN.java
 *
 * Created on 29 de Fevereiro de 2008, 13:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.FN;

import br.com.copal.entity.Cobrador;
import br.com.copal.entity.Situacao;
import br.com.copal.util.FormatacaoValores;
import br.com.copal.entity.Ad;
import br.com.copal.entity.Avon;
import br.com.copal.util.FileUploadServlet;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Jean
 */

public class TratamentoUploadFN extends FormatacaoValores{
    
    private String linhaGrupo;
    
    public TratamentoUploadFN(){}
    
//    public void salvarRemessa() throws ServletException, IOException{
//        //RelacaoFN relacaoFN = new RelacaoFN();
//        //relacaoFN.instanciarAvonFN();
//        this.terminarUpload(FileUploadServlet.requestStatic,FileUploadServlet.uploadedItemsStatic,FileUploadServlet.uploadStatic,FileUploadServlet.fileItemStatic,FileUploadServlet.filePathStatic);
//    }
    
    
    
    public void terminarUpload(HttpServletRequest request,List uploadedItems,ServletFileUpload upload,FileItem fileItem,String filePath)throws ServletException, IOException{
        RelacaoFN relacaoFN = new RelacaoFN();
        relacaoFN.instanciarAvonFN();
        relacaoFN.instanciarAdFN();
        relacaoFN.instanciarCobradorFN();
        relacaoFN.instanciarSituacaodFN();
        HttpSession session = request.getSession();
        byte[] bytes = new byte[255];
        byte[] bytesPorTab = new byte[255];
        ArrayList<ArrayList> linhas = new ArrayList();
        //ArrayList<String> colunas = new ArrayList();
        
        try{
            //uploadedItems = upload.parseRequest(request);
            Iterator i = uploadedItems.iterator();
            
            while(i.hasNext()){
                fileItem = (FileItem) i.next();
                if(fileItem.isFormField() == false){
                    if(fileItem.getSize() > 0){
                        File uploadedFile = null;
                        String MyFullFileName = fileItem.getName(),
                                MyFileName = "",
                                slashType = (MyFullFileName.lastIndexOf("\\") > 0) ? "\\" : "/"; //Win or Linux
                        int startIndex = MyFullFileName.lastIndexOf(slashType);
                        MyFileName = MyFullFileName.substring(startIndex + 1, MyFullFileName.length());
                        uploadedFile = new File(filePath, MyFileName);
                        fileItem.write(uploadedFile);
                        
                        //joga pra stream o arquivo lido
                        InputStreamReader streamReader = new InputStreamReader(fileItem.getInputStream());
                        //poe no buffer o stream do arquivo lido
                        BufferedReader reader = new BufferedReader(streamReader);
                        
                        //enquando tiver linhas no buffer roda o laço
                        while((linhaGrupo = reader.readLine()) != null){
                            relacaoFN.getAvonFN().getAvonManager().setAvon(new Avon());
                            relacaoFN.getAdFN().getAdManager().setAd(new Ad());
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setRegistro(linhaGrupo.substring(0,5));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setAsc2(linhaGrupo.substring(5,8));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setNome((String) linhaGrupo.substring(8,58));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setSetor(linhaGrupo.substring(58,61));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setNascimento(tratarData(linhaGrupo.substring(61,69)));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setRg((String) linhaGrupo.substring(69,82));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setCpf((String) linhaGrupo.substring(82,96));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setDdd(linhaGrupo.substring(96,100));
                            //avonManager.getAvon().setFone((String) linhaGrupo.substring(100,109));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setEndereco((String) linhaGrupo.substring(109,169));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setNumerocasa((String) linhaGrupo.substring(169,174));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setComplemento((String) linhaGrupo.substring(174,194));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setBairro((String) linhaGrupo.substring(194,224));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setCep((String) linhaGrupo.substring(224,232));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setCidade((String) linhaGrupo.substring(232,252));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setUf((String) linhaGrupo.substring(252,254));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setReferencia((String) linhaGrupo.substring(254,314));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setDataestabelecimento(tratarData(linhaGrupo.substring(314,322)));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setApelido((String)linhaGrupo.substring(322,342));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setNomepai((String) linhaGrupo.substring(342,392));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setNomemae((String) linhaGrupo.substring(392,442));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setModulo(linhaGrupo.substring(442,445));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setSecao(linhaGrupo.substring(445,446));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setTotalcampanhas(Integer.parseInt((String) linhaGrupo.substring(446,449)));
                            //nao precisa desses campos!!!!
                            //avonManager.getAvon().setNagencia((Integer) linhaGrupo.substring(449,453));
                            //avonManager.getAvon().setNomeagencia((String) linhaGrupo.substring(453,503));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setCpenvio((String)linhaGrupo.substring(503,505));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setDataenvio(tratarData(linhaGrupo.substring(505,513)));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setDatadevolucao(tratarData(linhaGrupo.substring(513,521)));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setSituacao("A");
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setStatusaviso("A");
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setSituacaocb((Situacao)relacaoFN.getSituacaoFN().getSituacaoManager().getSDAO().recuperarTodos().get(0));
                            relacaoFN.getAvonFN().getAvonManager().getAvon().setCobrador((Cobrador)relacaoFN.getCobradorFN().getCobradorManager().getCDAO().recuperarTodos().get(0));
                            //Aqui começa o AD 1 da remessa tem que fazer algo pois são 4 ads mas nem sempre vem mais de 1
                            relacaoFN.getAdFN().getAdManager().getAd().setAvisodebito(linhaGrupo.substring(948,953));
                            relacaoFN.getAdFN().getAdManager().getAd().setDataad(tratarData(linhaGrupo.substring(953,961)));
                            relacaoFN.getAdFN().getAdManager().getAd().setDataAtualizacao(relacaoFN.getAvonFN().getAvonManager().getAvon().getDataenvio());
                            relacaoFN.getAdFN().getAdManager().getAd().setCp(linhaGrupo.substring(961,962));
                            relacaoFN.getAdFN().getAdManager().getAd().setPricecp(Integer.parseInt((String) linhaGrupo.substring(962,963)));
                            //relacaoFN.getAdFN().getAdManager().getAd().setSinal(Integer.parseInt((String) linhaGrupo.substring(963,963)));
                            relacaoFN.getAdFN().getAdManager().getAd().setValordebito(Double.valueOf((String) linhaGrupo.substring(967,975)));
                            
                            relacaoFN.getAdFN().getAdManager().getAd().setNotafiscal(linhaGrupo.substring(975,982));
                            relacaoFN.getAdFN().getAdManager().getAd().setDatanf(tratarData(linhaGrupo.substring(982,990)));
                            
                            //relacaoFN.getAdFN().getAdManager().getAd().setSinal2(Integer.parseInt((String) linhaGrupo.substring(990,991)));
                            relacaoFN.getAdFN().getAdManager().getAd().setComissao(Double.valueOf((String) linhaGrupo.substring(991,998)));
                            //relacaoFN.getAdFN().getAdManager().getAd().setSinal3(Integer.parseInt((String) linhaGrupo.substring(998,999)));
                            relacaoFN.getAdFN().getAdManager().getAd().setEncargos(Double.valueOf((String) linhaGrupo.substring(999,1006)));
                            relacaoFN.getAdFN().getAdManager().getAd().setAvon(relacaoFN.getAvonFN().getAvonManager().getAvon());
                            relacaoFN.getAdFN().getAdManager().getAd().setPrincipal(relacaoFN.getAdFN().getAdManager().getAd().getValordebito() - (relacaoFN.getAdFN().getAdManager().getAd().getEncargos() + relacaoFN.getAdFN().getAdManager().getAd().getComissao()));
                            
                            relacaoFN.getAvonFN().getAvonManager().getADAO().salvar(relacaoFN.getAvonFN().getAvonManager().getAvon());
                            relacaoFN.getAdFN().getAdManager().getADAO().salvar(relacaoFN.getAdFN().getAdManager().getAd());
                            
                            //essa é a logica para ser usada com os ads 2,3,4 porem tem q ajeitar a variavel de referencia
                                    if(!linhaGrupo.substring(1006,1011).equals("00000")){
                                relacaoFN.getAdFN().getAdManager().setAd(new Ad());
                                //Aqui começa o AD 2 da remessa tem que fazer algo pois são 4 ads mas nem sempre vem mais de 1
                                relacaoFN.getAdFN().getAdManager().getAd().setAvisodebito(linhaGrupo.substring(1006,1011));
                                relacaoFN.getAdFN().getAdManager().getAd().setDataad(tratarData(linhaGrupo.substring(1011,1019)));
                                relacaoFN.getAdFN().getAdManager().getAd().setCp(linhaGrupo.substring(1019,1021));
                                relacaoFN.getAdFN().getAdManager().getAd().setPricecp(Integer.parseInt((String) linhaGrupo.substring(1021,1023)));
                                //relacaoFN.getAdFN().getAdManager().getAd().setSinal(Integer.parseInt((String) linhaGrupo.substring(1023,1024)));
                                relacaoFN.getAdFN().getAdManager().getAd().setValordebito(Double.valueOf((String) linhaGrupo.substring(1024,1033)));
                                relacaoFN.getAdFN().getAdManager().getAd().setNotafiscal(linhaGrupo.substring(1033,1040));
                                relacaoFN.getAdFN().getAdManager().getAd().setDatanf(tratarData(linhaGrupo.substring(1040,1048)));
                                relacaoFN.getAdFN().getAdManager().getAd().setDataAtualizacao(relacaoFN.getAvonFN().getAvonManager().getAvon().getDataenvio());
                                //relacaoFN.getAdFN().getAdManager().getAd().setSinal2(Integer.parseInt((String) linhaGrupo.substring(1048,1049)));
                                relacaoFN.getAdFN().getAdManager().getAd().setComissao(Double.valueOf((String) linhaGrupo.substring(1049,1056)));
                                //relacaoFN.getAdFN().getAdManager().getAd().setSinal3(Integer.parseInt((String) linhaGrupo.substring(1056,1057)));
                                relacaoFN.getAdFN().getAdManager().getAd().setEncargos(Double.valueOf((String) linhaGrupo.substring(1057,1064)));
                                relacaoFN.getAdFN().getAdManager().getAd().setPrincipal(relacaoFN.getAdFN().getAdManager().getAd().getValordebito() - (relacaoFN.getAdFN().getAdManager().getAd().getEncargos() + relacaoFN.getAdFN().getAdManager().getAd().getComissao()));
                                relacaoFN.getAdFN().getAdManager().getAd().setAvon(relacaoFN.getAvonFN().getAvonManager().getAvon());
                                relacaoFN.getAdFN().getAdManager().getADAO().salvar(relacaoFN.getAdFN().getAdManager().getAd());
                                    }
                            
                            if(!linhaGrupo.substring(1064,1069).equals("00000")){
                                relacaoFN.getAdFN().getAdManager().setAd(new Ad());
                                //Aqui começa o AD 2 da remessa tem que fazer algo pois são 4 ads mas nem sempre vem mais de 1
                                relacaoFN.getAdFN().getAdManager().getAd().setAvisodebito(linhaGrupo.substring(1063,1069));
                                relacaoFN.getAdFN().getAdManager().getAd().setDataad(tratarData(linhaGrupo.substring(1069,1077)));
                                relacaoFN.getAdFN().getAdManager().getAd().setCp(linhaGrupo.substring(1077,1078));
                                relacaoFN.getAdFN().getAdManager().getAd().setPricecp(Integer.parseInt((String) linhaGrupo.substring(1079,1081)));
                                //relacaoFN.getAdFN().getAdManager().getAd().setSinal(Integer.parseInt((String) linhaGrupo.substring(1081,1082)));
                                relacaoFN.getAdFN().getAdManager().getAd().setValordebito(Double.valueOf((String) linhaGrupo.substring(1082,1091)));
                                relacaoFN.getAdFN().getAdManager().getAd().setNotafiscal(linhaGrupo.substring(1091,1098));
                                relacaoFN.getAdFN().getAdManager().getAd().setDatanf(tratarData(linhaGrupo.substring(1098,1106)));
                                relacaoFN.getAdFN().getAdManager().getAd().setDataAtualizacao(relacaoFN.getAvonFN().getAvonManager().getAvon().getDataenvio());
                                //relacaoFN.getAdFN().getAdManager().getAd().setSinal2(Integer.parseInt((String) linhaGrupo.substring(1106,1107)));
                                relacaoFN.getAdFN().getAdManager().getAd().setComissao(Double.valueOf((String) linhaGrupo.substring(1107,1114)));
                                //relacaoFN.getAdFN().getAdManager().getAd().setSinal3(Integer.parseInt((String) linhaGrupo.substring(1114,1115)));
                                relacaoFN.getAdFN().getAdManager().getAd().setEncargos(Double.valueOf((String) linhaGrupo.substring(1115,1122)));
                                relacaoFN.getAdFN().getAdManager().getAd().setPrincipal(relacaoFN.getAdFN().getAdManager().getAd().getValordebito() - (relacaoFN.getAdFN().getAdManager().getAd().getEncargos() + relacaoFN.getAdFN().getAdManager().getAd().getComissao()));
                                relacaoFN.getAdFN().getAdManager().getAd().setAvon(relacaoFN.getAvonFN().getAvonManager().getAvon());
                                relacaoFN.getAdFN().getAdManager().getADAO().salvar(relacaoFN.getAdFN().getAdManager().getAd());
                            }
                            if(!linhaGrupo.substring(1122,1127).equals("00000")){
                                relacaoFN.getAdFN().getAdManager().setAd(new Ad());
                                //Aqui começa o AD 2 da remessa tem que fazer algo pois são 4 ads mas nem sempre vem mais de 1
                                relacaoFN.getAdFN().getAdManager().getAd().setAvisodebito(linhaGrupo.substring(1122,1127));
                                relacaoFN.getAdFN().getAdManager().getAd().setDataad(tratarData(linhaGrupo.substring(1127,1135)));
                                relacaoFN.getAdFN().getAdManager().getAd().setCp(linhaGrupo.substring(1135,1137));
                                relacaoFN.getAdFN().getAdManager().getAd().setPricecp(Integer.parseInt((String) linhaGrupo.substring(1137,1139)));
                                //relacaoFN.getAdFN().getAdManager().getAd().setSinal(Integer.parseInt((String) linhaGrupo.substring(1139,1140)));
                                relacaoFN.getAdFN().getAdManager().getAd().setValordebito(Double.valueOf((String) linhaGrupo.substring(1140,1149)));
                                relacaoFN.getAdFN().getAdManager().getAd().setNotafiscal(linhaGrupo.substring(1149,1156));
                                relacaoFN.getAdFN().getAdManager().getAd().setDatanf(tratarData(linhaGrupo.substring(1156,1164)));
                                relacaoFN.getAdFN().getAdManager().getAd().setDataAtualizacao(relacaoFN.getAvonFN().getAvonManager().getAvon().getDataenvio());
                                //relacaoFN.getAdFN().getAdManager().getAd().setSinal2(Integer.parseInt((String) linhaGrupo.substring(1164,1165)));
                                relacaoFN.getAdFN().getAdManager().getAd().setComissao(Double.valueOf((String) linhaGrupo.substring(1165,1172)));
                                //relacaoFN.getAdFN().getAdManager().getAd().setSinal3(Integer.parseInt((String) linhaGrupo.substring(1172,1173)));
                                relacaoFN.getAdFN().getAdManager().getAd().setEncargos(Double.valueOf((String) linhaGrupo.substring(1173,1180)));
                                relacaoFN.getAdFN().getAdManager().getAd().setPrincipal(relacaoFN.getAdFN().getAdManager().getAd().getValordebito() - (relacaoFN.getAdFN().getAdManager().getAd().getEncargos() + relacaoFN.getAdFN().getAdManager().getAd().getComissao()));
                                relacaoFN.getAdFN().getAdManager().getAd().setAvon(relacaoFN.getAvonFN().getAvonManager().getAvon());
                                relacaoFN.getAdFN().getAdManager().getADAO().salvar(relacaoFN.getAdFN().getAdManager().getAd());
                            }
                        }
                    }
                }
            }
        } catch (FileUploadException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
