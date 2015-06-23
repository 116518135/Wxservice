<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     if(form.passwd.value!=form.passwd1.value){
       alert('两次输入的密码不一致!');
       return false;;
     }
          return true;
  }
  function save(){
     var form=document.forms[0];
     if(check()){
        form.method.value="updateSavePassword";
        form.submit();
     }
  }
</script>
<body style="margin:10px;">
<html:form  action="/tbsuser.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tbsuserid"/>

<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
		    	<mytag:Button  value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
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
            原密码
        </td>
        <td width="80%" class="input" colspan="3">
            <html:password property="oldpasswd"   size="20"   styleClass="field"/>        
        </td>
              </tr>		 
          <tr>
        <td width="20%" class="label">
            新密码
        </td>
        <td width="80%" class="input" colspan="3">
            <html:password property="passwd"   size="20"   styleClass="field"/>        
        </td>
              </tr>	
          <tr>
        <td width="20%" class="label">
            再次输入密码
        </td>
        <td width="80%" class="input" colspan="3">
            <html:password property="passwd1"   size="20"   styleClass="field"/>        
        </td>
              </tr>		              	               
  
     </table>
</div>
</html:form>
</body>
<script language="javascript">

</script>
</html>	
