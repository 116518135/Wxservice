<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.trpreportdatasourceid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.trpreportdatasourceid.value=pkid;
     form.method.value="view";
     form.submit();
  }
</script>
<body style="margin:10px;">
<html:form  action="/trpreportdatasource.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="trpreportdatasourceid"/>
  
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<mytag:Button invoke="isEdit" onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加"/>
		   
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/trpreportdatasource.do"  title="${struts_form.moduleTitle}" width="100%" form="TrpreportdatasourceForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column  property="name" title="名称" filterable="true" pkid="trpreportdatasourceid"/>
          		
          		  <ec:column  property="datasourcetype" title="类型" filterable="true" />
          		
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>