<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head>
<script src="<c:url value="/scripts/base/jquery-1.4.min.js"/>" type="text/javascript"></script>
</head>
<script language="javascript">
 function query(){
   var form=document.forms[0];
   form.submit();
 }
 $(function(){
 $('tbody>tr').click(function() {
			$(this).find(':radio').attr('checked',true);
		});
	})
</script>
<body style="margin:10px;">
<html:form  action="/select.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="object"/>
  <html:hidden property="objecttype"/>
  <html:hidden property="table" />
    <html:hidden property="cmpid"/>
  <html:hidden property="limitcmpflag"/>
   <html:hidden property="querystring"/>
  <table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
    	<ucard:Button value="查询" icon="icon-search" hotkey='F3,114' onclick="javascript:query()"/>
	    <ucard:Button value="选择返回" icon="icon-apply" hotkey='F2,113' onclick="javascript:returnSelect('chk')"/>		
		</td>
	</tr>
</table>
<%@ include file="/pages/select/selectTrpreportconditionTable_condition.jsp" %>
	
	<ec:table items="LISTS"  action="${ctx}/select.do"   width="100%" form="SelectForm"  rowsDisplayed="15"
            filterable="false"  locale="zh_cn"  autoIncludeParameters="false"  var="item">
	  <ec:row>
    	  <ec:column  width="10%" property="null" title="选择" sortable="false"  viewsAllowed="html">
    		  <input type="radio" name="chk" onDblClick="javascript:returnSelect('chk')" />
    		  <html:hidden name="item" property="tableid"/>
    		  <html:hidden name="item" property="name" />
		  </ec:column>
		  
		  <ec:column  property="name" title="名称" filterable="true" />
       	  <ec:column  property="jsname" title="js名称" filterable="true" />
          	  </ec:row>
     </ec:table>
</html:form>
</body>

</html>	
