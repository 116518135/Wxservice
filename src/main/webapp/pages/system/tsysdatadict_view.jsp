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
     var querystring='?method=addLine&tsysdatadictid='+document.all.item("tsysdatadictid").value;
     var url=action+querystring;
     openSubWindow600(url,title);
  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     var action=form.action;
     var querystring='?method=viewLine&tsysdatadictid='+document.all.item("tsysdatadictid").value+'&tsysdatadictdtlid='+pkid;
     var url=action+querystring;

     openSubWindow600(url,title);
  }
   
</script>
<body style="margin:10px;">
<html:form  action="/tsysdatadict.do"  method="post">	
<html:hidden property="method"/>
<html:hidden property="tsysdatadictid"/>
<html:hidden property="findwhere"/>
<html:hidden property="moduleTitle"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		    <mytag:Button invoke="isEdit" onclick="javascript:addLine()" icon="icon-edit"  hotkey="F2,113" value="增加明细"/>&nbsp;
		    
		    <mytag:Button invoke="isEdit" onclick="javascript:update()" icon="icon-edit"  hotkey="F4,115" value="修改"/>&nbsp;
               
            <mytag:Button invoke="isEdit" onclick="javascript:del()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
                  
		    <mytag:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27" onclick="javascript:ret()"/>
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
		      <html:text property="code" disabled="true" size="20"   styleClass="field"/>
             </td>
                 <td width="20%" class="label">
            名称
        </td>
        <td width="30%" class="input">
			<html:text property="name" disabled="true" size="20"   styleClass="field"/>
        </td>            
 
          
    </tr>		  
                <tr>
              <td width="20%" class="label">
                 备注
              </td>
              <td width="30%" class="input">
		      <html:text property="memo" disabled="true" size="20"   styleClass="field"/>
             </td>
                   <td  width="20%" class="label">&nbsp;</td>
         <td  width="30%"  class="input">&nbsp;</td>                
          
    </tr>		  
     </table>
</div>	

  <ec:table items="LISTS"  action="${ctx}/tsysdatadict.do"  width="100%" form="TsysdatadictForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column pkid="tsysdatadictdtlid"  property="code" title="编号" filterable="true" />
          		  <ec:column  property="name" title="值" filterable="true" />
          		  <ec:column  property="memo" title="备注" filterable="true" />
          	  </ec:row>
  </ec:table>
  

</html:form>
</body>
</html>	
