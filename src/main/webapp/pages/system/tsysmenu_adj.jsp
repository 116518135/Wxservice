<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function adj(){
     var form=document.forms[0] ;
     var src=form.item;
	 var buf="";
	 for(i=0;i<src.options.length;i++){
        if(i==0){
		  buf=src.options[i].value;
        }else{
          buf=buf+","+src.options[i].value;
        }
     }
     form.menuorder.value=buf;
	 form.method.value="adjSave";
     form.submit();
  }

  function LabelValue(){
      var label;
	  var value;
   }

   function up(){
     var form=document.forms[0];
	 var src=form.item;
     
     var index = src.selectedIndex;
     if(index==0) return;
	 var srcOption=src.options[index];
	 var targetOption=src.options[index-1];
	 var label=srcOption.text;
	 var value=srcOption.value;
	 srcOption.text=targetOption.text;
	 srcOption.value=targetOption.value;
     targetOption.text=label;
	 targetOption.value=value;
	 targetOption.selected=true;
	 src.selectedIndex=index-1;

   }

   function down(){
     var form=document.forms[0];
	 var src=form.item;

     var index = src.selectedIndex;
      if(src.selectedIndex==src.length-1) return;
	 var srcOption=src.options[index];
	 var targetOption=src.options[index+1];
	 var label=srcOption.text;
	 var value=srcOption.value;
	 srcOption.text=targetOption.text;
	 srcOption.value=targetOption.value;
     targetOption.text=label;
	 targetOption.value=value;
	 targetOption.selected=true;
	 src.selectedIndex=index+1;

   }

  function closeDialog(){
     parent.closeDialog();
  }
</script>
<body style="margin:25px;">
<html:form  action="/tsysmenu.do"  method="post">	
<html:hidden property="method"/>
<html:hidden property="parentid"/>
<html:hidden property="menuorder"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="center">
			<mytag:Button  onclick="javascript:up()"     value="上移"/>
		    <mytag:Button  onclick="javascript:down()"   value="下移"/>
     		<mytag:Button  value="确认" icon="icon-save" onclick="javascript:adj()"/>
		    <mytag:Button  value="关闭" icon="icon-cancel" onclick="javascript:closeDialog()"/>
		    
		</td>
	</tr>
	<tr>
	   <td height="20">
	</tr>
</table>	
 <div align="center">

                         <html:select property="item" size="15"  multiple="multiple" style="width:270px;" >
						   <html:optionsCollection name="org.apache.struts.taglib.html.BEAN" property="adjOptions"/>
                       </html:select>
	
 </div>

	  </table>
</html:form>
<script language="javascript">
var form=document.forms[0];
form.item.selectedIndex=0;
</script>
</body>
</html>	
