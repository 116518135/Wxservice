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
<html:form  action="/tsysexcel.do"  token="TOKEN_LINE" method="post">	
<html:hidden property="method"/>
<html:hidden property="tsysexcelid"/>
<html:hidden property="tsysexceldtlid"/>
<html:hidden property="ec_p"/>
<html:hidden property="ec_crd"/>
<html:hidden property="findwhere"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		    <ucard:Button invoke="isEdit" onclick="javascript:updateLine()" icon="icon-edit"  hotkey="F4,115" value="修改"/>&nbsp;
               
            <ucard:Button invoke="isEdit" onclick="javascript:delLine()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
                  
		    <ucard:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27" onclick="javascript:parent.closeDialog()"/>
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
           字段名称
        </td>
        <td width="30%" class="input">
            <html:text property="fieldname" maxlength="20" onblur=""  disabled="true"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
          字段类型
        </td>
        <td width="30%" class="input">
                    <html:select property="fieldtype" disabled="true" >
                      <html:option value=""></html:option>
	        	      <html:optionsCollection name="org.apache.struts.taglib.html.BEAN" property="fieldtypelist" value="ivalue"/>
             </html:select>   
             			
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
           字段标题
        </td>
        <td width="30%" class="input">
            <html:text property="fieldtitle" maxlength="30" onblur="" disabled="true"   size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
          字段处理器
        </td>
        <td width="30%" class="input">
                <html:select property="fieldprocess" disabled="true" >
                      <html:option value=""></html:option>
	        	      <html:optionsCollection name="org.apache.struts.taglib.html.BEAN" property="fieldprocesslist"/>
             </html:select>   
             
			
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            列数
        </td>
        <td width="30%" class="input">
            <html:text property="colindex" maxlength="20" disabled="true"   size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            列宽
        </td>
        <td width="30%" class="input">
			<html:text property="colwidth"  maxlength="20"   disabled="true"  size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            变量/常量值
        </td>
        <td width="30%" class="input">
            <html:text property="fieldvalue" maxlength="30" disabled="true"     size="20"   styleClass="field"/>        
        </td>
                 <td  width="20%" class="label" >&nbsp;</td>
         <td  width="30%"  class="input">&nbsp;</td>          
              </tr>		  
     </table>
</div>	



</html:form>
</body>
</html>	
