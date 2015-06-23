<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.trphtmlcateid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.trphtmlcateid.value=pkid;
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
<html:form  action="/trphtmlcate.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="trphtmlcateid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<mytag:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		<mytag:Button invoke="isEdit" onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加"/>
		  
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/trphtmlcate.do"  title="${struts_form.moduleTitle}" width="100%" form="TrphtmlcateForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column  property="code" title="编号" filterable="true" pkid="trphtmlcateid"/>
          		  <ec:column  property="name" title="名称" filterable="true" />
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>