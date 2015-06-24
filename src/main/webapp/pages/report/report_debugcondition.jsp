<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head>

<script>
   function htmlquery(){
     var form=document.forms[0];
     var action=form.action;
     if(form.server_url.value!=''){
       action=form.server_url.value+"/report.do";
       form.anonymous.value="1";
     } 
     form.action=action;
     form.method.value="debughtml";
     form.submit();
  }
  
  function dcubequery(){
     var form=document.forms[0];
     var action=form.action;
     if(form.server_url.value!=''){
       action=form.server_url.value+"/report.do";
       form.anonymous.value="1";
     } 
     form.action=action;
     form.method.value="dcube";
     form.submit();
  }
  
  function xslquery(){
     var form=document.forms[0];
     var action=form.action;
     if(form.server_url.value!=''){
       action=form.server_url.value+"/report.do";
       form.anonymous.value="1";
     } 
     form.action=action;
     form.method.value="xsl";
     form.submit();
  }
  
  function help(){
     var form=document.forms[0];
     var action=form.action;
     var title=form.moduleTitle.value;
     var querystring='?method=help&reportid='+form.reportid.value;
     var url=action+querystring;
     openWindow(url,title);
  }
  

</script>
</head>
<body style="margin:10px;">
<html:form  action="/report.do"  method="post" target="_blank">	
<html:hidden property="method"/>
<html:hidden property="reportid"/>
<html:hidden property="service"/>
<html:hidden property="anonymous"/>

<html:hidden property="server_url"/>

<html:hidden property="userid"/>
<html:hidden property="cmpid"/>
<html:hidden property="cmpkind"/>
<html:hidden property="layerno"/>
<html:hidden property="layerindex"/>
<html:hidden property="storeid"/>
<html:hidden property="amtRound"/>
<html:hidden property="priceRound"/>
<html:hidden property="discountRound"/>
<html:hidden property="qtyRound"/>
<html:hidden property="moduleTitle"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
	   <td align="right">
	        &nbsp;
            <ucard:Button invoke="isExport"  onclick="javascript:htmlquery()"  icon='icon-search'  hotkey="F2,113"  value="查询"/>
            <ucard:Button invoke="isExport"  onclick="javascript:xslquery()" icon='icon-excel'  hotkey="F3,114"  value="Excel"/>
	   </td>
	</tr>
</table>
<html:hidden property="auth_string"/>	
${struts_form.condition}



</html:form>
</body>
</html>	
