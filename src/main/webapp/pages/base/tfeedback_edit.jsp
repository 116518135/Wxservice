<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     

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
<html:form  action="/tfeedback.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tfeedbackid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
		    	<ucard:Button  value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
		     	<ucard:Button  value="返回" icon="icon-back" hotkey='ESC,27' onclick="javascript:ret()"/>
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
            帐号
        </td>
        <td width="30%" class="input">
            <html:text property="usercode" size="20"   styleClass="field"/>        
        </td>
        </tr>
    
              
          <tr>
        <td width="20%" class="label">
            详细内容
        </td>
        <td width="80%" class="input">
            <html:textarea property="content" rows="15" cols="110"  styleClass="field"/>        
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
