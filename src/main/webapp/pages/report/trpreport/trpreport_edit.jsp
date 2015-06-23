<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     

          if(''==document.all.item("code").value){
         alert("报表编码"+"不能为空!");
         document.all.item("code").focus();
         return false;
     }
          if(''==document.all.item("name").value){
         alert("报表名称"+"不能为空!");
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
<html:form  action="/trpreport.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="trpreportid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
		    	<mytag:Button  value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
		     	<mytag:Button  value="返回" icon="icon-back" hotkey='ESC,27' onclick="javascript:ret()"/>
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
            <font color='red'>*</font>报表编码
        </td>
        <td width="30%" class="input">
            <html:text property="code" maxlength="30" onblur="isCode()"  onkeydown="nextFocus('name')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            <font color='red'>*</font>报表名称
        </td>
        <td width="30%" class="input">
			<html:text property="name"  maxlength="30" onblur=""   onkeydown="nextFocus('processclass')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            处理类
        </td>
        <td width="30%" class="input">
        <html:select property="processclass" style= "width:150 ">
								
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="processclasslist" value="value"/>
							</html:select>
            
        </td>
                 <td width="20%" class="label">
            显示处理类
        </td>
        <td width="30%" class="input">
		  <html:select property="dispprocessor" style= "width:150 ">
								
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="dispprocessoroptions"/>
							</html:select>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            server地址
        </td>
        <td width="30%" class="input">
            <html:text property="serverurl" maxlength="50" onblur=""    size="20"   styleClass="field"/>        
        </td>
        <td width="20%" class="label">
           监听类
        </td>
        <td width="30%" class="input">
			<html:text property="listenclass"  maxlength="50" onblur=""   onkeydown="nextFocus('serverurl')" size="20"   styleClass="field"/>
        </td>           
              </tr>	
                   <tr>
          <td width="20%" class="label">
          上一级菜单
        </td>
        <td width="30%" class="input">
			<html:select property="parentid">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="parentlist" value="ivalue"/>
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
