<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function update(){
     var form=document.forms[0];
     form.method.value='update';
     form.submit();
  }
  function del(){
     if(confirm('确认删除资料？')){
        var form=document.forms[0];
        form.method.value='delete';
        form.submit();
     }
  }

</script>
<body style="margin:10px;">
<html:form  action="/trpreportcondition.do"  method="post">	
<html:hidden property="method"/>
<html:hidden property="trpreportconditionid"/>
<html:hidden property="ec_p"/>
<html:hidden property="ec_crd"/>
<html:hidden property="findwhere"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		    <mytag:Button onclick="javascript:update()" icon="icon-edit"  hotkey="F4,115" value="修改"/>&nbsp;
               
            <mytag:Button  onclick="javascript:del()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
                  
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
                 行
              </td>
              <td width="30%" class="input">
		      <html:text property="rowindex" disabled="true" size="20"   styleClass="field"/>
             </td>
                 <td width="20%" class="label">
            列
        </td>
        <td width="30%" class="input">
			<html:text property="colindex" disabled="true" size="20"   styleClass="field"/>
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
            处理类
        </td>
        <td width="30%" class="input">
			<html:select property="processclass" style= "width:140 " disabled="true">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="processclasslist" value="value"/>
							</html:select>
        </td>            
 
          
    </tr>		  
                <tr>
              
                 <td width="20%" class="label">
            插件
        </td>
        <td width="30%" class="input">
			<html:select property="trpreportpluginid" style= "width:130 " disabled="true">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="pluginlist" value="value"/>
							</html:select>
        </td>            
  <td width="20%" class="label">
                 where语句
              </td>
              <td width="30%" class="input">
		      <html:text property="wheres" disabled="true" size="20"   styleClass="field"/>
             </td>
          
    </tr>		  
                <tr>
             
                 <td width="20%" class="label">
            Html内容
        </td>
        <td width="30%" class="input" colspan="3">
			<html:textarea  property="htmlcontent"  cols="83" rows="15" disabled="true"
					styleClass="field" />
        </td>            
 
          
    </tr>		  
     </table>
</div>	

</html:form>
</body>
</html>	
