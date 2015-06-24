<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.tsysbillnoruleid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.tsysbillnoruleid.value=pkid;
     form.method.value="view";
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
<html:form  action="/tsysbillnorule.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsysbillnoruleid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<ucard:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		<ucard:Button  onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加"/>
		  
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/tsysbillnorule.do"  title="${struts_form.moduleTitle}" width="100%" form="TsysbillnoruleForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column pkid="tsysbillnoruleid" property="code" title="编号" filterable="true" />
          		  <ec:column pkid="tsysbillnoruleid" property="name" title="名称" filterable="true" />
          		  <ec:column  property="mark" title="单据标识" filterable="true" />
          		  <ec:column  property="numlength" title="流水号长度" filterable="true" />
          		  <ec:column  property="curcount" title="当前计数" filterable="true" />
          		  <ec:column  property="editdate" title="计数日期" filterable="true" />
          		 
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>