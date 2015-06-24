<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head>
<script src="<c:url value="/scripts/base/selectMulti.js"/>" type="text/javascript"></script>
</head>
<script language="javascript">

</script>
<body style="margin:10px;">
<html:form  action="/select.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="object"/>
  <html:hidden property="objecttype"/>
   <html:hidden property="multiname"/>
  <html:hidden property="multivalue"/>  
  <html:hidden property="querystring"/>
  <table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
    	<ucard:Button value="全选"  onclick="javascript:allSelect(1)"/>
		<ucard:Button value="取消"  onclick="javascript:javascript:allSelect(0)"/>
        &nbsp;		
    	<ucard:Button value="查询" icon="icon-search" hotkey='F3,114' onclick="javascript:query()"/>
	    <ucard:Button value="选择返回" icon="icon-apply" hotkey='F2,113' onclick="javascript:callReturn()"/>
	      <ucard:Button value="追加返回" icon="icon-apply" hotkey='F4,115' onclick="javascript:appendReturn()"/>		
	    <ucard:Button value="关闭" icon="icon-apply" hotkey='ESC,27' onclick="javascript:colseReturn()"/>				
		</td>
	</tr>
</table>
<%@ include file="/pages/select/selectTable_condition.jsp" %>
  <ec:table items="LISTS"  action="${ctx}/select.do"   width="100%" form="SelectForm"  rowsDisplayed="15"
            filterable="false"  locale="zh_cn"  autoIncludeParameters="false"  var="item">
	  <ec:row>
		  <ec:column  width="10%" property="null" title="选择" sortable="false"  viewsAllowed="html">
    		  <input type="checkbox" name="checks" dataflag="<bean:write name='item' property='name'/>"  value="<bean:write name='item' property='tableid'/>"/>
		  </ec:column>
		  <ec:column  property="code" title="编码" filterable="true" />
		  <ec:column  property="name" title="名称" filterable="true" />
          	  </ec:row>
  </ec:table>

	
</html:form>
</body>
<script language="javascript">
var element=getFirstElement();
if(element!=null){
  element.focus();
}
</script>
</html>	
