<%@ page contentType="text/html;charset=UTF-8" %>
<div class="eXtremeTable" >
<table   border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<thead>
	<tr class="titleRow" >
		<td colspan="0" ><span>选择</span></td>
	</tr>
</table>

<table width="100%" bgcolor="gray" width="100%" style="table-layout:fixed;" border="0" cellspacing="1" cellpadding="1">
       <tr>
        <td width="20%" class="label">
            编号
        </td>
        <td width="30%" class="input">
             <html:text property="condition1" maxlength="12" onblur="isCode()"  size="20"   styleClass="field"/>
        <td width="20%" class="label">
            报表控件
        </td>
        <td width="30%" class="input">
             <html:select property="condition2" style= "width:140 " onchange="javascript:query()">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="list1" value="value"/>
							</html:select>
        </td>
       </tr>		  
</table>
</div>
	
	