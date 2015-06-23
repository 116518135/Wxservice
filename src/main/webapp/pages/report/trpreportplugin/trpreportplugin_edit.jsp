<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     

          if(''==document.all.item("name").value){
         alert("插件名称"+"不能为空!");
         document.all.item("name").focus();
         return false;
     }
        
          return true;
  }
  function save(){
     var form=document.forms[0];
     if(check()){
        form.submit();
     }
  }
  function saveagain(){
	     var form=document.forms[0];
	     form.method.value='addSaveagain';
	     if(check()){
	        form.submit();
	     }
	  }

  function plgEnter1(){
		 var mapping="trppluginid,trppluginid|fieldalias,fieldalias|name,name|template,template|springname,springname";
		 plgEnter2('trpplugin',mapping,0);
	}
	function plgEnter2(tablename,mapping,limitcmpflag){
	    var querystring="table="+tablename;
	    codeEnter1('tableEnter',mapping,querystring,'');
	}

	 function selectplg1(){
	   var mapping="trppluginid,tableid|name,name";
	   selectplg2('trpplugin',mapping,0);
	 }
	 function selectplg2(tablename,mapping,limitcmpflag){
	   var url ='${ctx}/select.do?forward=main&action=selectplg&table='+tablename+"&mapping="+mapping;
	   OpenImportWindow(url,mapping,700,500);
	 }

	 function ImportData(rtObject) {               //导入数据
		 
		  var arGroup   = rtObject.substring(0,rtObject.length).split("|");
		  var arMap;
		  var strName = '';
		  for (i = 0; i < arGroup.length; i++) {
		    arMap = arGroup[i].split(",");
		    
		    for (j = 0; j < arMap.length; j=j+2) {
					var srcnodeName = arMap[j];
					document.all.item(srcnodeName).value=arMap[j+1];
					strName = srcnodeName;
					break;
		     }
		   jQuery.noConflict();
		    var tableid = arMap[1];
		    var htmlobj=jQuery.ajax({url:"trpplugin.do?method=getInfo&trppluginid="+tableid,async:false});
		    var responseText = htmlobj.responseText;
		    responseText=eval('('+responseText+')');
		    document.all.item("fieldalias").value=responseText.fieldalias;
		    document.all.item("template").value=responseText.template;
		    document.all.item("springname").value=responseText.springname;
		  }
			

		} 
</script>
<body style="margin:10px;">
<html:form  action="/trpreportplugin.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="trpreportpluginid"/>
   <html:hidden property="trpreportid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
		    	<mytag:Button  value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
		    	<c:if test="${methodflag=='addSave'}">
		    	<mytag:Button  value="继续增加" icon="icon-save" hotkey='F11,122' onclick="javascript:saveagain()"/></c:if>
		     	<mytag:Button  value="返回" icon="icon-edit" hotkey='ESC,27' onclick="javascript:parent.closeDialog()" />
		</td>
	</tr>
</table>	

<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td colspan="4" ><span><bean:write name="org.apache.struts.taglib.html.BEAN" property="moduleTitle"/></span></td>
	</tr>
	      <tr>
        <td width="20%" class="label"><input type="hidden" name="trppluginid" />
              <font color='red'>*</font>插件名称
        </td>
        <td width="30%" class="input">
            <html:text property="name" size="20"  styleClass="field" onkeydown="plgEnter1()"/>
            <button  class="selectButton" onclick="javascript:selectplg1()"/>
<img src="${ctx}/images/select.gif" width="12"/></button>      
        </td>
                 <td width="20%" class="label">
           插件字段别名
        </td>
        <td width="30%" class="input">
			<html:text property="fieldalias"  maxlength="50" onblur=""   size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
       
                 <td width="20%" class="label">
           对象名
        </td>
        <td width="30%" class="input">
			<html:text property="springname"  maxlength="30" onblur=""    size="20"   styleClass="field" />
       </td>  
         
        
        <td width="20%" class="label">
           &nbsp;
        </td>
        <td width="30%" class="input">
			&nbsp;
        </td>     
              </tr>	
              <tr>
              <td width="20%" class="label">
            插件Sql模板
        </td>
              <td width="30%" class="input" colspan="3">
            <html:textarea  property="template"  cols="83" rows="15" 
					styleClass="field" />       
        </td>   </tr>	  
     </table>
</div>
</html:form>
</body>
<script language="javascript">
var element=getFirstElement();
if(element!=null){
  element.focus();
}
</script>
</html>	
