<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function add(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.tsysjobplanid.value='';
     form.method.value="add";
     form.submit();
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     form.tsysjobplanid.value=pkid;
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
<html:form  action="/tsysjobplan.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsysjobplanid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<mytag:Button onclick="javascript:finds()" icon='icon-search'  hotkey="F3,114"  value="查询"/>
		<mytag:Button invoke="isEdit" onclick="javascript:add()" icon='icon-add'  hotkey="F2,113"  value="增加"/>
		  
		   
		</td>
	</tr>
</table>	


  <ec:table items="LISTS"  action="${ctx}/tsysjobplan.do"  title="${struts_form.moduleTitle}" width="100%" form="TsysjobplanForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column pkid="tsysjobplanid"  property="code" title="计划编号" filterable="true" />
          		  <ec:column  property="name" title="计划名称" filterable="true" />
          		  <ec:column  property="fnflagname" title="发生频率" filterable="true" />
          		  <ec:column  property="jobname" title="调度对象" filterable="true" />
          		  <ec:column  property="indexno" title="优先级" filterable="true" />
          		  <ec:column  property="memo" title="备注" filterable="true" />
                  <ec:column  property="null" title="停止使用"  viewsAllowed="html">
 	               <html:checkbox name="item" property="stopflag"   disabled="true" value="1"/>
		          </ec:column>		          		  
          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>