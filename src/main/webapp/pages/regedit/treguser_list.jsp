<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.treguserid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.treguserid.value=pkid;
     form.method.value="view";
     form.submit();
  }
  function finds(){
     var form=document.forms[0];
     var action=form.action
     var url=action+'?method=find';
	 OpenFindWindow(url,520,300);
  }
  function email(){
	     var form=document.forms[0];
	     var action=form.action
	     var title=form.moduleTitle.value;
	     form.treguserid.value='';
	     form.method.value="sendMail";
	     form.submit();
	  }
</script>
<body style="margin:10px;">
<html:form  action="/treguser.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="treguserid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<ucard:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		<ucard:Button invoke="isEdit" onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加"/>
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/treguser.do"  title="${struts_form.moduleTitle}" width="100%" form="TreguserForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column  pkid="treguserid" property="code" title="帐号" filterable="true" />
          		  <ec:column  property="name" title="姓名" filterable="true" />
          		  <ec:column  property="email" title="邮箱" filterable="true" />
          		  <ec:column  property="mobilephone" title="手机" filterable="true" />
          		  <ec:column  property="memo" title="备注" filterable="true" />
		           <ec:column  property="ccc" title="停用标志"  viewsAllowed="html">
 	               <html:checkbox name="item" property="stopflag"   disabled="true" value="1"/>
		          </ec:column>
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>