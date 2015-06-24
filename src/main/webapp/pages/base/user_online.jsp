<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function del(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.method.value="deleteOnlineUser";
     form.submit();
  }
</script>
<body style="margin:10px;">
<html:form  action="/online.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		     <esk:Button invoke="isEdit" onclick="javascript:del()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
		</td>
	</tr>
</table>	

  <ec:table items="LISTS" showPagination="false"  sortable="false"
            filterable="false"    autoIncludeParameters="false"  var="item">           
	  <ec:row>
	  	          <ec:column  width="3%" property="null" title="选择" sortable="false"  viewsAllowed="html">
    		          <input type="checkbox" name="checks"   value="<bean:write name='item' property='sessionid'/>"/>
		          </ec:column>	
		  		  <ec:column  width="15%" property="sessionid" title="会话ID" filterable="true" />
          		  <ec:column   width="10%" property="username" title="操作员" filterable="true" />
          		  <ec:column   width="10%" property="cmpname" title="所属公司" filterable="true" />
          		  <ec:column   width="10%" property="clientno" title="客户端序列号" filterable="true" />
     </ec:row>
  </ec:table>
</html:form>	
</body>
</html>