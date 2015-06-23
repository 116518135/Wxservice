<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function save(){
     var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.method.value="updateall";
     form.submit();
  }

</script>
<body style="margin:10px;">
<html:form  action="/trpreport.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="reportarea"/>
  <html:hidden property="trpreportid"/>


  
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<mytag:Button value="保存" icon="icon-save" hotkey='F10,121'
							onclick="javascript:save()" />
		 <mytag:Button  value="返回" icon="icon-edit" hotkey='ESC,27' onclick="javascript:parent.closeDialog()" />
		   
		</td>
	</tr>
</table>	

	<ec:table items="LISTS" action="${ctx}/trpreport.do"
				title="${struts_form.moduleTitle}" width="100%" form="TrpreportForm"
				rowsDisplayed="15" filterable="false" autoIncludeParameters="false"
				var="item" showPagination="true">
		  <ec:row>
		  		  <ec:column  property="label" title="标题" filterable="true" />
		  		   <ec:column  property="null" title="行"  viewsAllowed="html">
          		    <html:text name="item"  property="rowindex"  onblur="return isInt();"/>
          		    <html:hidden name="item"  property="trpreportdtlid"  />
          		  </ec:column> 
          		   <ec:column  property="null" title="列"  viewsAllowed="html">
          		    <html:text  name="item" property="colindex"  onblur="return isInt();"/>
          		  </ec:column> 
          		   <ec:column  property="null" title="宽度"  viewsAllowed="html">
          		    <html:text  name="item" property="width"  onblur="return isInt();"/>
          		  </ec:column>       		 
          		 

          	  </ec:row>
  </ec:table>
</html:form>	
</body>
</html>