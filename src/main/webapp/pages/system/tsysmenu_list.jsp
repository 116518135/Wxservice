<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.tsysmenuid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.tsysmenuid.value=pkid;
     form.method.value="view";
     form.submit();
  }
  function finds(){
     var form=document.forms[0];
     var action=form.action
     var url=action+'?method=find';
	 OpenFindWindow(url,520,300);
  }
  
  function adj(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     var url=action+'?method=adj&parentid='+form.parentid.value;
     openSubWindow600(url,title);
  }
  
</script>
<body style="margin:10px;">
<html:form  action="/tsysmenu.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsysmenuid"/>
  <html:hidden property="parentid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<mytag:Button onclick="javascript:adj()" icon='icon-edit' value="调整"  hotkey="F4,115"/>
		<mytag:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		<mytag:Button onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加"/>
		
		</td>
	</tr>
</table>	
  <ec:table items="LISTS"  action="${ctx}/tsysmenu.do"  title="${struts_form.moduleTitle}" width="100%" form="TsysmenuForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column  property="name" pkid="tsysmenuid" title="菜单名称" filterable="true" />
          		  <ec:column  property="accessurl" title="资源URL" filterable="true" />
          		  <ec:column  property="indexno" title="显示顺序" filterable="true" />
          		  <ec:column  property="rightvalue" title="权限字符串" filterable="true" />
          		  <ec:column  property="cmprightvalue" title="组织权限字符串" filterable="true" />
          		  <ec:column  property="webserver" title="WEB服务器" filterable="true" />
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>