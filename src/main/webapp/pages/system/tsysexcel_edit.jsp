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
          if(''==document.all.item("processclass").value){
         alert("处理类"+"不能为空!");
         document.all.item("processclass").focus();
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
<html:form  action="/tsysexcel.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsysexcelid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
		    	<ucard:Button  value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
		     	<ucard:Button  value="返回" icon="icon-edit" hotkey='ESC,27' onclick="javascript:ret()"/>
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
			<html:text property="name"  maxlength="30" onblur=""   onkeydown="nextFocus('processclass')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            <font color='red'>*</font>处理类
        </td>
        <td width="30%" class="input">
             <html:select property="processclass">
                      <html:option value=""></html:option>
	        	      <html:optionsCollection name="org.apache.struts.taglib.html.BEAN" property="processclasslist"/>
             </html:select>   
                    
        </td>
              

                <td width="20%" class="label">
            业务对象名
        </td>
        <td width="30%" class="input" >
            <html:text property="springname"       size="20"   styleClass="field"/>        
        </td>
              </tr>		  
          <tr>
        <td width="20%" class="label">
            表名
        </td>
        <td width="30%" class="input">
            <html:text property="tablename" maxlength="30"    onkeydown="nextFocus('tablekey')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            检查字段
        </td>
        <td width="30%" class="input">
			<html:text property="tablekey"  maxlength="30"     onkeydown="nextFocus('checkflag')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>

                      <td width="20%" class="label">
            检查标志
        </td>
        <td width="30%" class="input" colspan="3">
            <html:checkbox property="checkflag"  value="1"/>        
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
