<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function update(){
     var form=document.forms[0];
     form.method.value='update';
     form.submit();
  }
  function del(){
     if(confirm('确认删除资料？')){
        var form=document.forms[0];
        form.method.value='delete';
        form.submit();
     }
  }
  function ret(){
     var form=document.forms[0];
     form.method.value='list';
     form.submit();
  }
</script>
<body style="margin:10px;">
<html:form  action="/tsysmenu.do"  method="post">	
<html:hidden property="method"/>
<html:hidden property="tsysmenuid"/>
<html:hidden property="ec_p"/>
<html:hidden property="ec_crd"/>
<html:hidden property="findwhere"/>
<html:hidden property="parentid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		    <mytag:Button onclick="javascript:update()" icon="icon-edit"  hotkey="F4,115" value="修改"/>&nbsp;
               
            <mytag:Button onclick="javascript:del()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
                  
		    <mytag:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27" onclick="javascript:ret()"/>
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
            <font color='red'>*</font>菜单名称
        </td>
        <td width="30%" class="input">
            <html:text property="name" maxlength="50"  disabled='true'  size="30"   styleClass="field"/>        
        </td>
    
        <td width="20%" class="label">
            显示顺序
        </td>
        <td width="30%" class="input">
            <html:text property="indexno" maxlength="20"  disabled='true'  size="30"   styleClass="field"/>        
        </td>
              </tr>		  
          <tr>

                 <td width="20%" class="label">
            权限字符串
        </td>
        <td width="30%" class="input">
			<html:text property="rightvalue"  maxlength="30"   disabled='true' size="30"   styleClass="field"/>
        </td>     
                <td width="20%" class="label">
            组织权限字符串
        </td>
        <td width="30%" class="input">
            <html:text property="cmprightvalue" maxlength="12"    disabled='true'  size="30"   styleClass="field"/>        
        </td>
              </tr>		  
  
              <tr>
        <td width="20%" class="label">
            资源URL
        </td>
        <td width="30%" class="input" colspan='3'>
			<html:text property="accessurl"  maxlength="50"  disabled='true'   size="100"   styleClass="field"/>
        </td>  
              </tr>
          <tr>
   
        <td width="20%" class="label">
            WEB服务器
        </td>
        <td width="30%" class="input">
            <html:text property="webserver" maxlength="50" disabled='true'     size="30"   styleClass="field"/>        
        </td>  
        <td width="20%" class="label">
            备注
        </td>
        <td width="30%" class="input">
			<html:text property="memo"  maxlength="50"  disabled='true' size="30"   styleClass="field"/>
        </td>               
              </tr>		  
     </table>
</div>	

</html:form>
</body>
</html>	
