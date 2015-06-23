<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function updateLine(){
     var form=document.forms[0];
     form.method.value='updateLine';
     form.submit();
  }
  function delLine(){
     if(confirm('是否确定删除?')){
        var form=document.forms[0];
        form.method.value='deleteLine';
        form.submit();
     }
  }  
</script>
<body style="margin:10px;">
<html:form  action="/tsysdatadict.do"  token="TOKEN_LINE" method="post">	
<html:hidden property="method"/>
<html:hidden property="tsysdatadictid"/>
<html:hidden property="tsysdatadictdtlid"/>
<html:hidden property="ec_p"/>
<html:hidden property="ec_crd"/>
<html:hidden property="findwhere"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		    <mytag:Button invoke="isEdit" onclick="javascript:updateLine()" icon="icon-edit"  hotkey="F4,115" value="修改"/>&nbsp;
               
            <mytag:Button invoke="isEdit" onclick="javascript:delLine()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
                  
		    <mytag:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27" onclick="javascript:parent.closeDialog()"/>
		</td>
	</tr>

</table>	

<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<thead>
	<tr class="titleRow" >
		<td colspan="4" ><span><bean:write name="org.apache.struts.taglib.html.BEAN" property="moduleTitle"/></span></td>
	</tr>
	                 <tr>
              <td width="20%" class="label">
                 编号
              </td>
              <td width="30%" class="input">
		      <html:text property="code" disabled="true" size="20"   styleClass="field"/>
             </td>
                 <td width="20%" class="label">
            值
        </td>
        <td width="30%" class="input">
			<html:text property="name" disabled="true" size="20"   styleClass="field"/>
        </td>            
 
          
    </tr>		  
                <tr>
              <td width="20%" class="label">
                 备注
              </td>
              <td width="30%" class="input">
		      <html:text property="memo" disabled="true" size="20"   styleClass="field"/>
             </td>
                   <td  width="20%" class="label">&nbsp;</td>
         <td  width="30%"  class="input">&nbsp;</td>                
          
    </tr>		  
     </table>
</div>	



</html:form>
</body>
</html>	
