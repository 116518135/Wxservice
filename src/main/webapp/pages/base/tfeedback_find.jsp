<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
   function find(){
      var form=document.forms[0];
      var buf='';
      var colvalue=document.all.item("begindate").value;
      if(''!=colvalue){
         buf=buf+ " and entity.time >='"+colvalue+"'";
      }
      var colvalue=document.all.item("enddate").value;
      if(''!=colvalue){
         buf=buf+ " and entity.time <='"+colvalue+"'";
      }
      var colvalue=document.all.item("usercode").value;
      if(''!=colvalue){
         buf=buf+ " and entity.usercode like '%"+colvalue+"%'";
      }
       
	window.callerOnReturn(buf,window);
   }
</script>
<body style="margin:10px;">
<html:form  action="/tfeedback.do"  method="post">	
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
            开始日期
         </td>
         <td width="30%" class="input">
			<input type="text" name="begindate" size="15" value="" onblur="checkDate(this,true)" class="field"><button type="button"  class="selectButton" onclick="return showCalendar('begindate', '%Y/%m/%d');"/><img src="/ucard/images/calendar.gif" width="12"/></button>
         </td>
         <td width="20%" class="label">
           结束日期
         </td>
         <td width="30%" class="input">
			<input type="text" name="enddate" size="15" value="" onblur="checkDate(this,true)" class="field"><button type="button"  class="selectButton" onclick="return showCalendar('enddate', '%Y/%m/%d');"/><img src="/ucard/images/calendar.gif" width="12"/></button>
         </td>
      </tr>	
       <tr>
      
       <td width="20%" class="label">
           帐号
        </td>
        <td width="30%" class="input" >
			<input type="text" name="usercode" size="20" value="" class="field">
        </td>
        </tr>
     </table>
</div>	

</html:form>
</body>
</html>	
