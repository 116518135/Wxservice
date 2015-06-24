<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/ext2js.jsp"%>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
        return true;
  }
  function save(){
     var form=document.forms[0];
     if(check()){
        form.method.value="editSave";
        form.submit();
     }
  }
 
  function selectTable(tablename, mapping, code) {
		var url = '${ctx}/select.do?forward=main&action=selectTaskTable&table='
				+ tablename + "&mapping=" + mapping + "|code,code";
		OpenImportWindow(url, mapping, 700, 500);
	}
	function selectTask() {
		selectTable('Txdtask', 'txdtaskid,tableid|taskcode,code');
	}
  
  
</script>
<body style="margin:10px;">
<html:form  action="/tsyscmpparam.do"  method="post">	
  <html:hidden property="method"/>
  <html:hidden property="moduleTitle"/>
  <html:hidden property="tsyscmpparamid"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
    <tr class="titleRow" >
		<td align="right">
		    	<ucard:Button  invoke="isEdit" value="保存" icon="icon-save" hotkey='F10,121' onclick="javascript:save()"/>
		</td>
	</tr>
</table>	
<div class="eXtremeTable">
	   <div id="tabs1">
	
	<div id="tab" class="x-hide-display">
		<%@ include file="/pages/system/tsyscmpparam_cmp_inc.jsp"%>
	</div>
	</div>
	
	</div>

</html:form>
</body>
<script language="javascript">
Ext.onReady(function(){
    var tabs = new Ext.TabPanel({
             renderTo: 'tabs1',
             activeTab: 0,
             autoWidth:true,
             height:500,
             defaults:{autoScroll: true},
             items:[
               {contentEl:'tab', title:'参数管理'}
             ]
    });
})
</script>

</html>
