<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function updateLine(){
     var form=document.forms[0];
     form.method.value='updateLine';
     form.submit();
  }
  function delLine(){
     if(confirm('是否确定删除?')){
        var form=document.forms[0];
        form.method.value='deleteLine';
        form.submit();
     }
     }
  function copy(){
	var form = document.forms[0];
	form.method.value = 'copy';
	form.submit();
  }  
    function test(){
     var form=document.forms[0];
     var action=form.action;
     var querystring='?method=test&trphtmlctrlid='+form.trphtmlctrlid.value;
     var url=action+querystring;
     winOpen(url);
  }   
</script>
<body style="margin:10px;">
<html:form  action="/trphtmlcate.do"  token="TOKEN_LINE" method="post">	
<html:hidden property="method"/>
<html:hidden property="trphtmlcateid"/>
<html:hidden property="trphtmlctrlid"/>
<html:hidden property="ec_p"/>
<html:hidden property="ec_crd"/>
<html:hidden property="findwhere"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		    <mytag:Button invoke="isEdit" onclick="javascript:updateLine()" icon="icon-edit"  hotkey="F4,115" value="修改"/>&nbsp;
               
            <mytag:Button invoke="isEdit" onclick="javascript:delLine()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
            <mytag:Button  onclick="javascript:test()" icon="icon-apply"  value="测试"/>&nbsp;
            <mytag:Button invoke="isEdit" onclick="javascript:copy()" icon="icon-copy"  hotkey="F5,116" value="复制"/>&nbsp;     
		    <mytag:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27" onclick="javascript:parent.closeDialog()"/>
		</td>
	</tr>

</table>	

<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<thead>
	<tr class="titleRow" >
		<td colspan="4" ><span><bean:write name="org.apache.struts.taglib.html.BEAN" property="moduleTitle"/></span></td>
	</tr>
	                 <tr>
              <td width="20%" class="label">
                 名称
              </td>
              <td width="30%" class="input">
		      <html:text property="name" disabled="true" size="20"   styleClass="field"/>
             </td>
                 <td width="20%" class="label">
            JS名称
        </td>
        <td width="30%" class="input">
			<html:text property="jsname" disabled="true" size="20"   styleClass="field"/>
        </td>            
    </tr>		
    <tr>
        <td width="20%" class="label">
                 where语句
              </td>
             <td width="30%" class="input" colspan=3>
            <html:text property="wheres" disabled="true" style="width:412.5px" maxlength="512" onblur=""  onkeydown="nextFocus('content')"  size="20"   styleClass="field"/>        
        </td>
    </tr>  
     <tr>
              <td width="20%" class="label">
                 处理类
              </td>
              <td width="30%" class="input" colspan=3>
		        <html:select property="processclass" style="width:150px"  disabled="true">
								<<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN"
									property="processclasslist" value="value" />
							</html:select>
             </td>
          
    </tr>		  
    
                <tr>
                 <td width="20%" class="label">
            内容
        </td>
        <td width="30%" class="input" colspan="3">
							<html:textarea  property="content"  disabled="true"  cols="78" rows="15"
								styleClass="field" />
								
						</td>            
    </tr>		  
               
     </table>
</div>	



</html:form>
</body>
</html>	
