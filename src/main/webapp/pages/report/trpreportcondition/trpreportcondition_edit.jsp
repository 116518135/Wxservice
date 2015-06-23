<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<html>
<head></head>
<script type="text/javascript" src="/scripts/jquery-1.4min.js"></script>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     

          if(''==document.all.item("name").value){
         alert("名称"+"不能为空!");
         document.all.item("name").focus();
         return false;
     }
          if(''==document.all.item("jsname").value){
         alert("JS名称"+"不能为空!");
         document.all.item("jsname").focus();
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
  function conEnter1(){
		 var mapping="trphtmlcateid,trphtmlcateid|name,name";
		 conEnter2('trphtmlcate',mapping,0);
	}
	function conEnter2(tablename,mapping,limitcmpflag){
	    var querystring="table="+tablename;
	    codeEnter1('tableEnter',mapping,querystring,'');
	}

	 function selectcon1(){
	   var mapping="trphtmlcateid,tableid|name,name";
	   selectcon2(mapping,0);
	 }
	
	 function selectcon2(mapping,limitcmpflag){
	   var url ='${ctx}/select.do?forward=main&action=selectTrpreportcondition&mapping='+mapping;  
	   OpenImportWindow(url,mapping,700,500);
	 }
	 function ImportData(rtObject) {               //导入数据
		  var arGroup   = rtObject.substring(0,rtObject.length).split("|");
		  var arMap;
		  var strName = '';
		  
		    arMap = arGroup[1].split(",");
		    
		    for (j = 0; j < arMap.length; j=j+2) {
					var srcnodeName = arMap[j];
					document.all.item(srcnodeName).value=arMap[j+1];
					strName = srcnodeName;
					break;
		     }
		    var arMapid=arGroup[0].split(",");
		     
		   jQuery.noConflict();
		    var tableid = arMapid[1];
		    var htmlobj=jQuery.ajax({url:"trphtmlcate.do?method=getInfo&trphtmlctrlid="+tableid,async:false});
		    var responseText = htmlobj.responseText;
		    responseText=eval('('+responseText+')');
		    jQuery("#jsname").val(responseText.jsname);
		    jQuery("#wheres").val(responseText.wheres);
		    jQuery("#htmlcontent").val(responseText.content);
		    jQuery("#processclass").val(responseText.processclass);

		} 
	 
		 

	 
</script>
<body style="margin: 10px;">
	<html:form action="/trpreportcondition.do" method="post">
		<html:hidden property="method" />
		<html:hidden property="moduleTitle" />
		<html:hidden property="trpreportconditionid" />
		<html:hidden property="trpreportid" />
		<table border="0" cellspacing="0" cellpadding="0" class="tableRegion"
			width="100%">
			<tr class="titleRow">
				<td align="right"><mytag:Button value="保存" icon="icon-save"
						hotkey='F10,121' onclick="javascript:save()" /> <c:if
						test="${methodflag=='addSave'}">
						<mytag:Button value="继续增加" icon="icon-save" hotkey='F11,122'
							onclick="javascript:saveagain()" />
					</c:if> <mytag:Button value="返回" icon="icon-back" hotkey='ESC,27'
						onclick="javascript:parent.closeDialog()" /></td>
			</tr>
		</table>

		<div class="eXtremeTable">
			<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
				class="tableRegion" width="100%">
				<tr class="titleRow">
					<td colspan="4"><span><bean:write
								name="org.apache.struts.taglib.html.BEAN" property="moduleTitle" />
					</span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="label"><font color='red'>*</font>名称<input
						type="hidden" name="trphtmlcateid" id="getInfo"></td>
					<td width="30%" class="input"><html:text property="name"
							size="20" styleClass="field" />
						 <button   class="selectButton" type="button"  onclick="javascript:selectcon1()"/>
						
						<img src="${ctx}/images/select.gif" width="12" />
					</button></td>
					<td width="20%" class="label"><font color='red'>*</font>JS名称</td>
					<td width="30%" class="input"><html:text property="jsname"
							styleId="jsname" maxlength="50" onblur=""
							onkeydown="nextFocus('rowindex')" size="20" styleClass="field" />
					</td>

				</tr>
				<tr>
					<td width="20%" class="label">行</td>
					<td width="30%" class="input"><html:text property="rowindex"
							maxlength="20" onblur="return isInt();"
							onkeydown="nextFocus('colindex')" size="20" styleClass="field" />
					</td>
					<td width="20%" class="label">列</td>
					<td width="30%" class="input"><html:text property="colindex"
							maxlength="20" onblur="return isInt();"
							onkeydown="nextFocus('width')" size="20" styleClass="field" /></td>

				</tr>
				<tr>
					<td width="20%" class="label">宽度</td>
					<td width="30%" class="input"><html:text property="width"
							maxlength="20" onblur="return isInt();"
							onkeydown="nextFocus('processclass')" size="20"
							styleClass="field" /></td>
					<td width="20%" class="label">处理类</td>
					<td width="30%" class="input"><html:select
							property="processclass" style="width:140 " styleId="processclass">
							<html:option value="">&nbsp;</html:option>
							<html:optionsCollection name="org.apache.struts.taglib.html.BEAN"
								property="processclasslist" value="value" />
						</html:select></td>

				</tr>
				<tr>
					<td width="20%" class="label">插件</td>
					<td width="30%" class="input"><html:select
							property="trpreportpluginid" style="width:130 ">
							<html:option value="">&nbsp;</html:option>
							<html:optionsCollection name="org.apache.struts.taglib.html.BEAN"
								property="pluginlist" value="value" />
						</html:select></td>
					<td width="20%" class="label">where语句</td>
					<td width="30%" class="input"><html:text property="wheres" styleId="wheres"
							maxlength="512" onblur="" onkeydown="nextFocus('htmlcontent')"
							size="20" styleClass="field" /></td>
				</tr>
				<tr>

					<td width="20%" class="label">Html内容</td>
					<td width="30%" class="input" colspan="3"><html:textarea styleId="htmlcontent"
							property="htmlcontent" cols="83" rows="15" styleClass="field" />

					</td>

				</tr>
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
