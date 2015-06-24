<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
function check(){
    var form=document.forms[0];
    

         if(''==document.all.item("codeflag").value){
        alert("来源报表编码"+"不能为空!");
        document.all.item("codeflag").focus();
        return false;
    }
        
         return true;
 }
 function save(){
	 var form=document.forms[0];
     var action=form.action
     var title=form.moduleTitle.value;
     form.method.value="copySave";
     if(check()){
    	 form.submit();
     }
 }

</script>
<body style="margin:10px;">
<html:form  action="/trpreport.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="findwhere"/>
  <html:hidden property="moduleTitle"/>
<html:hidden property="trpreportid"/>
  
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		<mytag:Button value="保存" icon="icon-save" hotkey='F10,121'
							onclick="javascript:save()" />
		 <mytag:Button  value="返回" icon="icon-edit" hotkey='ESC,27' onclick="javascript:parent.closeDialog()" />
		   
		</td>
	</tr>
</table>	

	<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td  colspan="2"><span><bean:write name="org.apache.struts.taglib.html.BEAN" property="moduleTitle"/></span></td>
	</tr>
	      <tr >
        <td width="50%" class="label">
            <font color='red'>*</font>来源报表编码
      </td><td width="50%">
        <html:text property="codeflag"  maxlength="40" onblur=""   size="20"   styleClass="field"/>
    </td></tr>
   <tr ><td align="right">
   <input type="checkbox" name="check"  value="datasource"/>
            数据源
   <input type="checkbox" name="check"  value="condition"/>
            条件
   <input type="checkbox" name="check"  value="plugin"/>        
			 插件
     </td><td>
    <input type="checkbox" name="check"  value="trpreporthead"/>
           表头
    <input type="checkbox" name="check"  value="trpreportline"/>      
			 明细
    <input type="checkbox" name="check"  value="trpreporttail"/>  
            表尾
            
        </td>     
          </tr>
          
      </table>
</div>
</html:form>	
</body>
</html>