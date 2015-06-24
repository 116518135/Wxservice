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
         buf=buf+ " and entity.code like '%"+colvalue+"%'";
      }
           var colvalue=document.all.item("name").value;
      if(''!=colvalue){
         buf=buf+ " and entity.name like '%"+colvalue+"%'";
      }
     
	window.callerOnReturn(buf,window);
   }
</script>
<body style="margin:10px;">
<html:form  action="/tsysbillnorule.do"  method="post">	
<html:hidden property="method"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
    	     <ucard:Button value="确定" icon="icon-apply" hotkey='Enter,13' onclick="javascript:find()"/>
	    	<ucard:Button value="取消" icon="icon-cancel" hotkey='ESC,27' onclick="javascript:window.close()"/>
		</td>
	</tr>
</table>	
<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
          <tr>
        <td width="20%" class="label">
           编号
        </td>
        <td width="30%" class="input">
			<html:text property="code"  size="15"   styleClass="field"/>
        </td>
        
                  <td width="20%" class="label">
            名称
        </td>
        <td width="30%" class="input">
			<html:text property="name"  size="15"   styleClass="field"/>
        </td>   
        
          
    </tr>		  
        
     </table>
</div>	

</html:form>
</body>
</html>	
