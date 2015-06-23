<%@ page contentType="text/html;charset=UTF-8" %>
<div class="eXtremeTable" >
<table   border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<thead>
	<tr class="titleRow" >
		<td colspan="0" ><span>选择</span></td>
	</tr>
</table>

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
       <tr>
        <td width="20%" class="label">
            编号
        </td>
        <td width="30%" class="input">
             <html:text property="condition1" maxlength="12" onblur="isCode()"  size="20"   styleClass="field"/>
        <td width="20%" class="label">
            名称
        </td>
        <td width="30%" class="input">
             <html:text property="condition2"  maxlength="50"    size="20"   styleClass="field"/>
        </td>
        </tr>
        <tr>
         <td width="20%" class="label">
                     所属公司
        </td>
        <td width="30%" class="input" colspan="3">
             <html:hidden property="condition5"/>
             <html:text property="condition6"  maxlength="50"    size="20"   styleClass="field"/>
			 <button   class="selectButton" type="button"  onclick="javascript:selectSubcmp()"/>
			<img src="${ctx}/images/select.gif" width="12"/></button>
			<script language='javascript'>
			 function selectSubcmp(){
			   var url ='${ctx}/select.do?forward=main&action=selectSubcmp';
			   var map="condition5,tbscmpid|condition6,name";
			   OpenImportWindow(url,map,700,500);
			 }
			</script>
        </td>   
        </tr>
       
</table>
</div>
	
	