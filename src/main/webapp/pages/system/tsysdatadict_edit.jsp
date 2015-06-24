<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     

          if(''==document.all.item("code").value){
         alert("编号"+"不能为空!");
         document.all.item("code").focus();
         return false;
     }
          if(''==document.all.item("name").value){
         alert("名称"+"不能为空!");
         document.all.item("name").focus();
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
<html:form  action="/tsysdatadict.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsysdatadictid"/>
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
            <font color='red'>*</font>编号
        </td>
        <td width="30%" class="input">
            <html:text property="code" maxlength="20" onblur=""  onkeydown="nextFocus('name')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            <font color='red'>*</font>名称
        </td>
        <td width="30%" class="input">
			<html:text property="name"  maxlength="50" onblur=""   onkeydown="nextFocus('memo')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            备注
        </td>
        <td width="30%" class="input">
            <html:text property="memo" maxlength="50"      size="20"   styleClass="field"/>        
        </td>
                 <td  width="20%" class="label" >&nbsp;</td>
         <td  width="30%"  class="input">&nbsp;</td>          
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
