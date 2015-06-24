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
</script>
<body style="margin:10px;">
<html:form  action="/trpreport.do"  token="TOKEN_LINE" method="post">	
<html:hidden property="method"/>
<html:hidden property="trpreportid"/>
<html:hidden property="trpreportdtlid"/>
<html:hidden property="ec_p"/>
<html:hidden property="ec_crd"/>
<html:hidden property="findwhere"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		    <mytag:Button  onclick="javascript:updateLine()" icon="icon-edit"  hotkey="F4,115" value="修改"/>&nbsp;
               
            <mytag:Button  onclick="javascript:delLine()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
                  
		    <mytag:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27"  onclick="javascript:parent.closeDialog()" />
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
                 标题
              </td>
              <td width="30%" class="input">
		      <html:text property="title" disabled="true" size="20"   styleClass="field"/>
             </td>
                 <td width="20%" class="label">
            字段
        </td>
        <td width="30%" class="input">
			<html:text property="field" disabled="true" size="20"   styleClass="field"/>
        </td>            
 
          
    </tr>		  
                <tr>
              <td width="20%" class="label">
                 宽度
              </td>
              <td width="30%" class="input">
		      <html:text property="width" disabled="true" size="20"   styleClass="field"/>
             </td>
                 <td width="20%" class="label">
            字段类型
        </td>
        <td width="30%" class="input">
			 <html:select property="fieldtype" style= "width:140 " disabled="true">
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
		      <html:text property="colindex" disabled="true" size="20"   styleClass="field"/>
             </td>
                 <td width="20%" class="label">
            行
        </td>
        <td width="30%" class="input">
			<html:text property="rowindex" disabled="true" size="20"   styleClass="field"/>
        </td>            
 
          
    </tr>		  
                <tr>
              <td width="20%" class="label">
                 对齐方式
              </td>
              <td width="30%" class="input">
		      <html:select property="align" style= "width:140 " disabled="true">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="alignlist" value="ivalue"/>
							</html:select>
             </td>
                 <td width="20%" class="label">
            插件
        </td>
        <td width="30%" class="input">
			<html:select property="trpreportpluginid" style= "width:130 "  disabled="true">
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
		     <html:checkbox property="linkflag"  value="1" disabled="true"
								onkeydown="nextFocus('linkurl')" />
             </td>
                 <td width="20%" class="label">
            链接url
        </td>
        <td width="30%" class="input">
			<html:text property="linkurl" disabled="true" size="20"   styleClass="field"/>
        </td>            
 
          
    </tr>		  
           <tr>
        <td width="20%" class="label">
           维度标志
        </td>
        <td width="30%" class="input">
               <html:radio property="dimvalueflag"   value="2"
								 />行
        <html:radio property="dimvalueflag"   value="1"
								 />列
        <html:radio property="dimvalueflag"   value="4"
								 />页面	
        <html:radio property="dimvalueflag"   value="3"
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
					styleClass="field" disabled="true" />
          
        </td>               
          
    </tr>		  
     </table>
</div>	



</html:form>
</body>
</html>	
