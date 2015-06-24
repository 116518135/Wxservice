<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     if(''==$("name").value){
         alert("菜单名称"+"不能为空!");
         $("name").focus();
         return false;
     }
          return true;
  }
  function save(){
     var form=document.forms[0];
     if(check()){
        form.submit();
     }
  }
  function ret(){
      var form=document.forms[0];
      if(form.method.value=='addSave'){
         form.method.value='list';
      }else{
        form.method.value='view';
      }
      form.submit();
  }
</script>
<body style="margin:10px;">
<html:form  action="/tsysmenu.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsysmenuid"/>
  
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
		    	<mytag:Button  value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
		     	<mytag:Button  value="返回" icon="icon-edit" hotkey='ESC,27' onclick="javascript:ret()"/>
		</td>
	</tr>
</table>	

<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td colspan="4" ><span><bean:write name="org.apache.struts.taglib.html.BEAN" property="moduleTitle"/></span></td>
	</tr>
	      <tr>
        <td width="20%" class="label">
            <font color='red'>*</font>菜单名称
        </td>
        <td width="30%" class="input">
            <html:text property="name" maxlength="50" onblur=""  onkeydown="nextFocus('indexno')"  size="30"   styleClass="field"/>        
        </td>
    
        <td width="20%" class="label">
            显示顺序
        </td>
        <td width="30%" class="input">
            <html:text property="indexno" maxlength="20" onblur="isInt()"  onkeydown="nextFocus('rightvalue')"  size="30"   styleClass="field"/>        
        </td>
              </tr>		  
          <tr>

                 <td width="20%" class="label">
            权限字符串
        </td>
        <td width="30%" class="input">
			<html:text property="rightvalue"  maxlength="30"     onkeydown="nextFocus('cmprightvalue')" size="30"   styleClass="field"/>
        </td>     
                <td width="20%" class="label">
            组织权限字符串
        </td>
        <td width="30%" class="input">
            <html:text property="cmprightvalue" maxlength="12"    onkeydown="nextFocus('accessurl')"  size="30"   styleClass="field"/>        
        </td>
              </tr>		  
  
              <tr>
        <td width="20%" class="label">
            资源URL
        </td>
        <td width="30%" class="input" colspan='3'>
			<html:text property="accessurl"  maxlength="100"     onkeydown="nextFocus('indexno')" size="100"   styleClass="field"/>
        </td>  
              </tr>
          <tr>
   
        <td width="20%" class="label">
            WEB服务器
        </td>
        <td width="30%" class="input">
            <html:text property="webserver" maxlength="50"      size="30"   styleClass="field"/>        
        </td>  
        <td width="20%" class="label">
            备注
        </td>
        <td width="30%" class="input">
			<html:text property="memo"  maxlength="50"  size="30"   styleClass="field"/>
        </td>               
              </tr>		  
              <tr>
               <td width="20%" class="label">
            父菜单
        </td>
                      <td width="30%" class="input" colspan="3">
			   <html:select property="parentid" >
	        	   <html:option value="">&nbsp;&nbsp;&nbsp;
	        	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:option>
                  <html:optionsCollection name="org.apache.struts.taglib.html.BEAN" property="menuOptions"/>
               </html:select>        
        </td>     
              </tr>
     </table>
</div>
</html:form>
</body>
<script language="javascript">
var element=getFirstElement();
if(element!=null){
  element.focus();
}
</script>
</html>	
