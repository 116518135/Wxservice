<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">

  function check(){
     var form=document.forms[0];
     

          if(''==document.all.item("name").value){
         alert("名称"+"不能为空!");
         document.all.item("name").focus();
         return false;
     }
         
          if(''==document.all.item("datasourcetype").value){
         alert("类型"+"不能为空!");
         document.all.item("datasourcetype").focus();
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
  function saveagain(){
	     var form=document.forms[0];
	     form.method.value='addSaveagain';
	     if(check()){
	        form.submit();
	     }
	  }

    
</script>
<body style="margin:10px;">
<html:form  action="/trpreportdatasource.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="trpreportdatasourceid"/>
  <html:hidden property="trpreportid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
		    	<mytag:Button  value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
		    	<c:if test="${methodflag=='addSave'}">
		    	<mytag:Button  value="继续增加" icon="icon-save" hotkey='F11,122' onclick="javascript:saveagain()"/></c:if>
		     	<mytag:Button  value="返回" icon="icon-back" hotkey='ESC,27' onclick="javascript:parent.closeDialog()" />
		     	
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
            <font color='red'>*</font>名称
        </td>
        <td width="30%" class="input">
            <html:text property="name" maxlength="30" onblur=""  onkeydown="nextFocus('datasourcetype')"  size="20"   styleClass="field"/>        
        </td>
                
        <td width="20%" class="label">
            <font color='red'>*</font>类型
        </td>
        <td width="30%" class="input">
            <html:select property="datasourcetype" style= "width:150 ">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="datasourcetypelist" value="ivalue"/>
							</html:select>    
        </td>
              </tr>		  
          <tr>
        
         <td width="20%" class="label">
           内容
        </td>
        <td width="30%" class="input" colspan="3">
			<html:textarea  property="content"  cols="83" rows="25" 
					styleClass="field" />
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
