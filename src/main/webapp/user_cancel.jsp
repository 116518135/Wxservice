<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script type="text/javascript">
function nextFocus(next){
    var key=window.event.keyCode;
	if(key==13){
	   var form=document.forms[0];
       var obj=document.getElementsByName(next)[0];
       obj.focus();
    }
}


 function submitForm(){
  var form=document.forms[0];
     if(document.all.item("usercode").value==""){
        alert("用户名不能为空!");
		document.all.item("usercode").focus();
		return;
     }
     
    if(document.all.item("randomcode").value==""){
        alert("验证码不能为空!");
		document.all.item("randomcode").focus();
		return;
     }
     form.method.value="cancelSave";
     form.submit();
  }
  function nextSubmit(){
    var key=window.event.keyCode;
	if(key==13){
		submitForm();
    }
  }
  function ret(){
      var form=document.forms[0];
      form.method.value="exit";
      form.submit();
  }
 </script> 
<body style="margin:10px;">
<html:form  action="/login.do"  method="post">	
  <html:hidden property="method"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="left">
		    	  <ucard:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27" onclick="javascript:ret()"/>
		    	  <ucard:Button  value="注销" icon="icon-apply"  onclick="javascript:submitForm()"/>
		</td>
	</tr>
</table>	

<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td colspan="4" ><span>在线用户注销</span></td>
	</tr>
          <tr>
        <td width="20%" class="label">
            用户
        </td>
        <td width="80%" class="input" colspan="3">
            <html:text property="usercode" onkeydown="nextFocus('passwd')"  onfocus="select()"  styleClass="field" />
        </td>
              </tr>	
          <tr>
        <td width="20%" class="label">
            密码
        </td>
        <td width="80%" class="input" colspan="3">
            <html:password property="passwd" onkeydown="nextFocus('randomcode')"  onfocus="select()"  styleClass="field" />    
        </td>
              </tr>		  
             <tr>
        <td width="20%" class="label">
            验证码
        </td>
        <td width="80%" class="input" colspan="3">
             <html:text property="randomcode" onkeydown="nextSubmit()"  onfocus="select()"  styleClass="field" />
   <img border="0" src="${ctx}/login.do?method=image" 
             style="cursor:pointer;" alt="点击这里换一个"
             onclick="this.src='${ctx}/login.do?method=image'"/> 
        </td>
              </tr>	         
     </table>
</div>
</html:form>
</body>
<script language="javascript">
var usercode=document.all.item("usercode");
usercode.focus();
</script>
</html>	
