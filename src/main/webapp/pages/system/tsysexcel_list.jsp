<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.tsysexcelid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.tsysexcelid.value=pkid;
     form.ec_p.value=1;
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
<html:form  action="/tsysexcel.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsysexcelid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<ucard:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		<ucard:Button invoke="isEdit" onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加"/>
		  
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/tsysexcel.do"  title="${struts_form.moduleTitle}" width="100%" form="TsysexcelForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column  property="code" pkid="tsysexcelid" title="编号" filterable="true" />
          		  <ec:column  property="name" title="名称" filterable="true" />
          		  <ec:column  property="excelprocessname" title="处理类" filterable="true" />
          		  <ec:column  property="tablename" title="表名" filterable="true" />
          		  <ec:column  property="tablekey" title="主键" filterable="true" />
          		  <ec:column  property="springname" title="业务对象名" filterable="true" />
          		  <ec:column  property="null" title="检查标志"  viewsAllowed="html">
 	               <html:checkbox name="item" property="checkflag"   disabled="true" value="1"/>
		          </ec:column>	
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>