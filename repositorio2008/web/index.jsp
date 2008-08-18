<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <h1>JSP Page</h1>
    <br/>   
    <!--<a href="./Templates/templateGeral.jsf">teste testando</a>
    <a href="./template-client.jsf">Teste facelets</a>
    --><a href="./Avon/Listar/listarAvon.jsf">Teste avon</a>
    <a href='./servletweb?acao=RelatorioCobradorTodos'>Relatorio cobrador Todos</a>
    <a href='./servletweb?acao=RelatorioAvisosTodos'>Relatorio avisos Todos</a><!--
    <a href="./Adebito/listarAd.jsf">Teste ad</a>
    <a href="./Caixa/listarCaixa.jsf">Teste caixa</a>
    <a href="./Pagamento/listarPagamento.jsf">Teste pagamento</a>
    <a href="./Cobrador/listarCobrador.jsf">Teste Cobrador</a>
    <a href="./CaixaItemPadrao/cadastrarCaixaItemPadrao.jsf">teste CaixaItemPadrao</a>
    <a href="./Upload/atualizarBase.jsf">Atualizar</a>
    <a href="./FileUpload.html">Upload</a>
    <a href="./Avon/menuSuperiorOpcoesAvon.jsf">Menu opcoes</a>-->
    <a href='./relatorio?classe=ad&pesquisa=SPC'>SPC</a><br>
    <a href='./relatorio?classe=avon&pesquisa=liquidado'>Liquidados</a><br>
    <a href='./relatorio?classe=pagamento&pesquisa=pconta&p1=Agencia'>PConta</a><br/>
    <a href='./relatorio?classe=avon&pesquisa=vencidos&p1=080808&p2=080808&p3=Agencia'>Relatorio de Vencidos</a><br/>
    <a href='./boleto'>Boleto caixa</a><br/>
    </body>
</html>
