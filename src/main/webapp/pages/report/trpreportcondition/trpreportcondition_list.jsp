<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.trpreportconditionid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.trpreportconditionid.value=pkid;
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
<html:form  action="/trpreportcondition.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="trpreportconditionid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<mytag:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		<mytag:Button invoke="isEdit" onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加"/>
		  
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/trpreportcondition.do"  title="${struts_form.moduleTitle}" width="100%" form="TrpreportconditionForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column  property="name" title="名称" filterable="true" />
          		  <ec:column  property="jsname" title="JS名称" filterable="true" />
          		  <ec:column  property="rowindex" title="行" filterable="true" />
          		  <ec:column  property="colindex" title="列" filterable="true" />
          		  <ec:column  property="width" title="宽度" filterable="true" />
          		  <ec:column  property="processclass" title="处理类" filterable="true" />
          		  <ec:column  property="processclass" title="处理类" filterable="true" />
          		  <ec:column  property="trpreportpluginid" title="插件" filterable="true" />
          		  <ec:column  property="wheres" title="where语句" filterable="true" />
          		  <ec:column  property="htmlcontent" title="Html内容" filterable="true" />
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>