<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     

          if(''==document.all.item("fieldname").value){
         alert("字段名称"+"不能为空!");
         document.all.item("fieldname").focus();
         return false;
     }
          if(''==document.all.item("fieldtype").value){
         alert("字段类型"+"不能为空!");
         document.all.item("fieldtype").focus();
         return false;
     }
          if(''==document.all.item("fieldtitle").value){
         alert("字段标题"+"不能为空!");
         document.all.item("fieldtitle").focus();
         return false;
     }
          if(''==document.all.item("fieldprocess").value){
         alert("字段处理器"+"不能为空!");
         document.all.item("fieldprocess").focus();
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
  
  function saveAdd(){
     var form=document.forms[0];
     if(check()){
        form.method.value="saveAdd"
        form.submit();
     }
  }

</script>
<body style="margin:10px;">
<html:form  action="/tsysexcel.do"  token="TOKEN_LINE" method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsysexceldtlid"/>
  <html:hidden property="tsysexcelid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
           <ucard:Button  value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
           <logic:equal name="struts_form" property="method" value="addSaveLine">
              <ucard:Button  value="继续新增" icon="icon-save" hotkey='F2,113' onclick="javascript:saveAdd()"/>
    	   </logic:equal>		     
      	  <ucard:Button  value=" 关闭 " icon="icon-edit" hotkey='ESC,27' onclick="javascript:parent.closeDialog()"/>
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
            <font color='red'>*</font>字段名称
        </td>
        <td width="30%" class="input">
            <html:text property="fieldname" maxlength="20" onblur=""  onkeydown="nextFocus('fieldtype')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            <font color='red'>*</font>字段类型
        </td>
        <td width="30%" class="input">
                    <html:select property="fieldtype">
                      <html:option value=""></html:option>
	        	      <html:optionsCollection name="org.apache.struts.taglib.html.BEAN" property="fieldtypelist" value="ivalue"/>
             </html:select>   
             			
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            <font color='red'>*</font>字段标题
        </td>
        <td width="30%" class="input">
            <html:text property="fieldtitle" maxlength="30" onblur=""  onkeydown="nextFocus('fieldprocess')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            <font color='red'>*</font>字段处理器
        </td>
        <td width="30%" class="input">
                <html:select property="fieldprocess">
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
            <html:text property="colindex" maxlength="20"   onblur="isInt()"  onkeydown="nextFocus('colwidth')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            列宽
        </td>
        <td width="30%" class="input">
			<html:text property="colwidth"  maxlength="20"  onblur="isInt()"   onkeydown="nextFocus('fieldvalue')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            变量/常量值
        </td>
        <td width="30%" class="input">
            <html:text property="fieldvalue" maxlength="30"      size="20"   styleClass="field"/>        
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
