<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<style>
.menu-title {
	font-family: "宋体", "Arial", "sans-serif";
    BACKGROUND-COLOR:#418212;
    BORDER-BOTTOM: grey 1px solid;
    BORDER-LEFT: grey 1px solid;
    BORDER-RIGHT: grey 1px solid;
    BORDER-TOP: grey 1px solid;
	font-size:9pt;
    TEXT-ALIGN: right
}
</style>
<script language="javascript">
  function LabelValue(label,ivalue){
    this.label=label;
    this.ivalue=ivalue;
  }
  
  var checkboxlist=new Array();
  <logic:iterate id="list" name="struts_form" property="checkboxList" indexId="index">
  checkboxlist[<%=index%>]=new LabelValue('<bean:write name="list" property="label"/>','<bean:write name="list" property="ivalue"/>');
  </logic:iterate>
  
  
  function beforeSave(){
     var form=document.forms[0];
     var buf="";
     for(var i=0;i<checkboxlist.length;i++){
       if(i>0){
         buf=buf+";";
       }
       var lv=checkboxlist[i];
       buf=buf+lv.ivalue;
       buf=buf+",";
       var chks=document.all.item(lv.label);
       for(var j=0;j<chks.length;j++){
         var chk=chks[j];
         if(chk.type=='checkbox'){
            if(chk.checked){
              buf=buf+"1";
            }else{
              buf=buf+"0";
            }
         }else{
           buf=buf+"2";
         }
       }
     }
     form.authbuf.value=buf;
  }
  function save(){
     var form=document.forms[0];
     beforeSave();
     form.method.value="updateAuthSave";
     form.submit();
  }
  function hotkey(){
     var key=window.event.keyCode; 
     if((key==83)&&(event.ctrlKey)) {
        save();
        event.keyCode=0;
        event.returnValue=false;
      }
  }    
  document.onkeydown = hotkey; 
  function ret(){
      var form=document.forms[0];
      form.method.value='view';
      form.submit();
  }
  
  function allSelect(chk,prex){
    var form=document.forms[0];
    var select;
    if(chk.checked){
       select=true;
    }else{
       select=false;
    }
    var now1= new Date(); 
	var inputs =document.all.item(prex);
	for (var i = 0; i < inputs.length; i++) {
			var input = inputs[i];
			if (input.type == "checkbox" && input.name.indexOf(prex)>=0){
                input.checked=select;
			}
    }
    var now2= new Date();
  }
  
    function allSelect2(chk,prex){
    var form=document.forms[0];
    var select;
    if(chk.checked){
       select=true;
    }else{
       select=false;
    }
	var inputs = form.getElementsByTagName('input');
	for (var i = 0; i < inputs.length; i++) {
			var input = inputs[i];
			if (input.type == "checkbox" && input.name.indexOf(prex)>=0){
                input.checked=select;
			}
    }
  }
  
   function allSelect1(flag){
    var form=document.forms[0];
    var select;
    if(flag==1){
       select=true;
    }else{
       select=false;
    }
	var inputs = form.getElementsByTagName('input');
	for (var i = 0; i < inputs.length; i++) {
			var input = inputs[i];
            input.checked=select;
    }

  }
 
</script>
<body style="margin:10px;">
<html:form  action="/tsysrole.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsysroleid"/>
  <html:hidden property="authbuf"/>
  <html:hidden property="name"/>
  <html:hidden property="code"/> 
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
		      <mytag:Button value="全选"   icon="icon-quanxuan" styleClass="btn_2k3" onclick="javascript:allSelect1(1)"/>
		      <mytag:Button value="取消全选"   icon="icon-quxiaoquanxuan" styleClass="btn_2k3" onclick="javascript:allSelect1(0)"/>
		    	<mytag:Button  value="保存" icon="icon-save" styleClass="btn_2k3" onclick="javascript:save()"/>
		     	<mytag:Button  value="返回"  icon="icon-cancel" styleClass="btn_2k3" onclick="javascript:ret()"/>
		</td>
	</tr>
</table>	

<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<thead>
	<tr class="titleRow" >
		<td colspan="0" ><span><bean:write name="org.apache.struts.taglib.html.BEAN" property="moduleTitle"/></span></td>
	</tr>
</table>
</div>
<bean:write name="struts_form" property="html" filter="false"/>	

</html:form>
</body>
<script language="javascript">

</script>
</html>	
