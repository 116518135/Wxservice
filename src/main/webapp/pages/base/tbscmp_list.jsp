<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.tbscmpid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.tbscmpid.value=pkid;
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
<html:form  action="/tbscmp.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tbscmpid"/>
    <html:hidden property="createtbscmpid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<ucard:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		<ucard:Button invoke="isEdit" onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加" />
		  
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/tbscmp.do"  title="${struts_form.moduleTitle}" width="100%" form="TbscmpForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column pkid="tbscmpid" property="code" title="编号" filterable="true" />
          		  <ec:column  property="name" title="名称" filterable="true" />
          		  <ec:column  property="cmptypename" title="类型" filterable="true" />
          		  <ec:column  property="groupname" title="卡分组" filterable="true" />
          		  <ec:column  property="createtbscmpname" title="所属商家" filterable="true" />
          		  <ec:column  property="linkman" title="联络人" filterable="true" />
          		  <ec:column  property="linktel" title="联络人电话" filterable="true" />
          		  <ec:column  property="username" title="业务员" filterable="true" />
          		   <ec:column  property="null" title="停止使用"  viewsAllowed="html">
 	               <html:checkbox name="item" property="stopflag"   disabled="true" value="1"/>
		          </ec:column>
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>