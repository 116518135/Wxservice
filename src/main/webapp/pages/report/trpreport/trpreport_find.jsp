<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
   function find(){
      var form=document.forms[0];
      var buf=''
           var colvalue=document.all.item("code").value;
      if(''!=colvalue){
         buf = buf + " and nls_LOWER(entity.code) like nls_LOWER('%" + colvalue + "%')";
      }
           var colvalue=document.all.item("name").value;
      if(''!=colvalue){
         buf=buf+ " and entity.name like '%"+colvalue+"%'";
      }
           var colvalue=document.all.item("processclass").value;
      if(''!=colvalue){
         buf=buf+ " and entity.processclass like '%"+colvalue+"%'";
      }
           var colvalue=document.all.item("listenclass").value;
      if(''!=colvalue){
         buf=buf+ " and entity.listenclass like '%"+colvalue+"%'";
      }
           var colvalue=document.all.item("serverurl").value;
      if(''!=colvalue){
         buf=buf+ " and entity.serverurl like '%"+colvalue+"%'";
      }
       
	window.callerOnReturn(buf,window);
   }
</script>
<body style="margin:10px;">
<html:form  action="/trpreport.do"  method="post">	
<html:hidden property="method"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
    	    <mytag:Button value="确定" icon="icon-apply" hotkey='F3,114' onclick="javascript:find()"/>
	    	<mytag:Button value="取消" icon="icon-cancel" hotkey='ESC,27' onclick="javascript:window.close()"/>
		</td>
	</tr>
</table>	
<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
          <tr>
        <td width="20%" class="label">
           报表编码
        </td>
        <td width="30%" class="input">
			<html:text property="code"  size="15"   styleClass="field"/>
        </td>
        
                  <td width="20%" class="label">
            报表名称
        </td>
        <td width="30%" class="input">
			<html:text property="name"  size="15"   styleClass="field"/>
        </td>   
        
          
    </tr>		  
          <tr>
        <td width="20%" class="label">
           处理类
        </td>
        <td width="30%" class="input">
			<html:text property="processclass"  size="15"   styleClass="field"/>
        </td>
        
                  <td width="20%" class="label">
            监听类
        </td>
        <td width="30%" class="input">
			<html:text property="listenclass"  size="15"   styleClass="field"/>
        </td>   
        
          
    </tr>		  
          <tr>
        <td width="20%" class="label">
           server地址
        </td>
        <td width="30%" class="input">
			<html:text property="serverurl"  size="15"   styleClass="field"/>
        </td>
        
                 <td width="20%" class="label" >&nbsp;</td>
        <td width="30%" class="input">&nbsp;</td>   
          
    </tr>		  
     </table>
</div>	

</html:form>
</body>
</html>	
