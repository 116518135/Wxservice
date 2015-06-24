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
  function ret(){
     var form=document.forms[0];
     form.method.value='list';
     form.submit();
  }
  
  function addLine(){
     var form=document.forms[0];
     var action=form.action;
     var title=form.moduleTitle.value;
     var querystring='?method=addLine&tsysexcelid='+document.all.item("tsysexcelid").value;
     var url=action+querystring;
     openSubWindow600(url,title);
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     var action=form.action;
     var querystring='?method=viewLine&tsysexcelid='+document.all.item("tsysexcelid").value+'&tsysexceldtlid='+pkid;
     var url=action+querystring;

     openSubWindow600(url,title);
  }
   
</script>
<body style="margin:10px;">
<html:form  action="/tsysexcel.do"  method="post">	
<html:hidden property="method"/>
<html:hidden property="tsysexcelid"/>
<html:hidden property="findwhere"/>
<html:hidden property="moduleTitle"/>
<html:hidden property="code"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		    <ucard:Button icon="icon-copy" invoke="isEdit" onclick="javascript:submitForm('copy')" value="复制"/>&nbsp;
		    <ucard:Button invoke="isEdit" onclick="javascript:addLine()" icon="icon-edit"  hotkey="F2,113" value="增加明细"/>&nbsp;
		    
		    <ucard:Button invoke="isEdit" onclick="javascript:update()" icon="icon-edit"  hotkey="F4,115" value="修改"/>&nbsp;
               
            <ucard:Button invoke="isEdit" onclick="javascript:del()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
            
            <ucard:Button  onclick="javascript:submitForm('xsl')"  icon="icon-excel" value="下载模板"/>&nbsp;
                  
		    <ucard:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27" onclick="javascript:ret()"/>
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
            编号
        </td>
        <td width="30%" class="input">
            <html:text property="code" maxlength="20" onblur=""   disabled="true"   size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
           名称
        </td>
        <td width="30%" class="input">
			<html:text property="name"  maxlength="30" onblur=""    disabled="true"  size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
          处理类
        </td>
        <td width="30%" class="input">
             <html:select property="processclass"  disabled="true" >
                      <html:option value=""></html:option>
	        	      <html:optionsCollection name="org.apache.struts.taglib.html.BEAN" property="processclasslist"/>
             </html:select>   
                    
        </td>
                      <td width="20%" class="label">
            业务对象名
        </td>
        <td width="30%" class="input" >
            <html:text property="springname"  maxlength="30"  disabled="true"  size="20"   styleClass="field"/>        
        </td>
             

              </tr>		  
          <tr>
        <td width="20%" class="label">
            表名
        </td>
        <td width="30%" class="input">
            <html:text property="tablename" maxlength="30"   disabled="true"   size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            检查字段
        </td>
        <td width="30%" class="input">
			<html:text property="tablekey"  maxlength="30"    disabled="true" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
                        <tr>
         <td width="20%" class="label">
            检查标志
        </td>
        <td width="30%" class="input" colspan="3">
            <html:checkbox property="checkflag"  disabled="true"  value="1"/>        
        </td>
        
              </tr>		
     </table>
</div>	

  <ec:table items="LISTS"  action="${ctx}/tsysexcel.do"  title="" width="100%" form="TsysexcelForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column pkid="tsysexceldtlid" property="fieldname" title="字段名称" filterable="true" />
          		  <ec:column  property="fieldtypename" title="字段类型" filterable="true" />
          		  <ec:column  property="fieldtitle" title="字段标题" filterable="true" />
          		  <ec:column  property="fieldprocessname" title="字段处理器" filterable="true" />
          		  <ec:column  property="colindex" title="列数" filterable="true" />
          		  <ec:column  property="colwidth" title="列宽" filterable="true" />
          		  <ec:column  property="fieldvalue" title="变量/常量值" filterable="true" />
          	  </ec:row>
  </ec:table>
  

</html:form>
</body>
</html>	
