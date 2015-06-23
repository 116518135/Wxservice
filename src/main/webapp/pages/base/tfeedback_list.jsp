<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.tfeedbackid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.tfeedbackid.value=pkid;
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
<html:form  action="/tfeedback.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tfeedbackid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<ucard:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		  
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/tfeedback.do"  title="${struts_form.moduleTitle}" width="100%" form="TfeedbackForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	          <ec:row>
	              <ec:column pkid="tfeedbackid" property="usercode" title="帐号" filterable="true" />
          		  <ec:column  property="content" title="反馈内容" filterable="true" />
          		  <ec:column  property="time" title="反馈日期" filterable="true" />

		  	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>