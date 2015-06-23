<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     

          if(''==document.all.item("title").value){
         alert("标题"+"不能为空!");
         document.all.item("title").focus();
         return false;
     }
          if (''==document.all.item("fieldtype").value)
          {
        	  document.all.item("fieldtype").value=null;
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
<html:form  action="/trpreport.do"  token="TOKEN_LINE" method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="trpreportdtlid"/>
  <html:hidden property="trpreportid"/>
  <html:hidden property="reportarea"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
           <mytag:Button  value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
           <logic:equal name="struts_form" property="method" value="addSaveLine">
              <mytag:Button  value="继续新增" icon="icon-save" hotkey='F2,113' onclick="javascript:saveAdd()"/>
    	   </logic:equal>		     
      	  <mytag:Button  value=" 返回 " icon="icon-edit" hotkey='ESC,27'  onclick="javascript:parent.closeDialog()" />
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
            <font color='red'>*</font>标题
        </td>
        <td width="30%" class="input">
            <html:text property="title" maxlength="50" onblur=""  onkeydown="nextFocus('field')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            字段
        </td>
        <td width="30%" class="input">
			<html:text property="field"  maxlength="30" onblur=""   onkeydown="nextFocus('width')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            宽度
        </td>
        <td width="30%" class="input">
            <html:text property="width" maxlength="20" onblur="return isInt();"  onkeydown="nextFocus('fieldtype')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            字段类型
        </td>
        <td width="30%" class="input">
        <html:select property="fieldtype" style= "width:140 ">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="fieldtypelist" value="ivalue"/>
							</html:select>
		
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            列
        </td>
        <td width="30%" class="input">
            <html:text property="colindex" maxlength="20" onblur="return isInt();"  onkeydown="nextFocus('rowindex')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            行
        </td>
        <td width="30%" class="input">
			<html:text property="rowindex"  maxlength="20" onblur="return isInt();"  onkeydown="nextFocus('align')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            对齐方式
        </td>
        <td width="30%" class="input">
         <html:select property="align" style= "width:140 ">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="alignlist" value="ivalue"/>
							</html:select>
        </td>
                 <td width="20%" class="label">
           插件
        </td>
        <td width="30%" class="input">
        <html:select property="trpreportpluginid" style= "width:130 ">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="trpreportpluginidlist" value="value"/>
							</html:select>
			
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
           链接标志
        </td>
        <td width="30%" class="input">
        <html:checkbox property="linkflag"  value="1"
								onkeydown="nextFocus('linkurl')" />
          
        </td>
                 <td width="20%" class="label">
            链接url
        </td>
        <td width="30%" class="input">
			<html:text property="linkurl"  maxlength="50" onblur=""   onkeydown="nextFocus('javacontent')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
              
                 <tr>
        <td width="20%" class="label">
           维度类型
        </td>
        <td width="30%" class="input">
        <html:radio property="dimvalueflag"  value="2"
								 />行
        <html:radio property="dimvalueflag"  value="1"
								 />列
        <html:radio property="dimvalueflag"  value="4"
								 />页面	
        <html:radio property="dimvalueflag"  value="3"
								 />数据									 							 
        </td>
                 <td width="20%" class="label">
        
        </td>
        <td width="30%" class="input">
		
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
            脚本内容
        </td>
        <td width="30%" class="input" colspan="3">
        <html:textarea  property="javacontent"  cols="70" rows="6"
					styleClass="field" />
          
        </td>
                
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
