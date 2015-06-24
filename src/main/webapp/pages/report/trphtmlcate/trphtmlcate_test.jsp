<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head>

<script>
   function testSql(){
     var form=document.forms[0];
     form.method.value='testSql';
     form.submit();
  }

</script>
</head>
<body style="margin:10px;">
<html:form  action="/trphtmlcate.do"  method="post">	
<html:hidden property="method"/>
<html:hidden property="trphtmlctrlid"/>
<html:hidden property="moduleTitle"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
	   <td align="right">
          <input type="button" value="测试SQL" class="btn_2k3" onclick="javascript:testSql()"/>
	   </td>
	</tr>
</table>	
</br>
<table width="100%" bgcolor="gray" width="100%" style="table-layout:fixed;" border="0" cellspacing="1" cellpadding="1">
    <tr>
	   <td class="input" colspan="2">
            <img src="${ctx}/images/d.jpg" width="15" height="15" align="absmiddle">生成的Html
	   </td>
    </tr>	
    <tr>

             ${struts_form.content}

    </tr>
    <tr>
	   <td class="input" colspan="2">
            <img src="${ctx}/images/d.jpg" width="15" height="15" align="absmiddle">where语段
	   </td>
    </tr>	
    <tr>
       <td width="35%" class="input" colspan="2">
             ${struts_form.sql}
       </td>
    </tr>   
</table>   
</html:form>
</body>
</html>	
