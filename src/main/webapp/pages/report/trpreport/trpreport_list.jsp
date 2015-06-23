<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.trpreportid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.trpreportid.value=pkid;
     form.method.value="view";
     form.ec_p.value="1";
     form.submit();
  }
  function finds(){
     var form=document.forms[0];
     var action=form.action
     var url=action+'?method=find';
	 OpenFindWindow(url,520,300);
  }
</script>
<body style="margin:10px;">
<html:form  action="/trpreport.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="parentid"/>
  <html:hidden property="trpreportid"/>
  
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<mytag:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		<mytag:Button invoke="isEdit" onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加"/>
		  
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/trpreport.do"  title="${struts_form.moduleTitle}" width="100%" form="TrpreportForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column  property="code" title="报表编码" filterable="true" pkid="trpreportid"/>
          		  <ec:column  property="name" title="报表名称" filterable="true" />
          		  <ec:column  property="processclass" title="处理类" filterable="true" />
          		  <ec:column  property="listenclass" title="监听类" filterable="true" />
          		  <ec:column  property="serverurl" title="server地址" filterable="true" />
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>