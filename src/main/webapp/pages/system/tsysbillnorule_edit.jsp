<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<html>
<head></head>
<script language="javascript">
  function check(){
     var form=document.forms[0];
     

          if(''==document.all.item("code").value){
         alert("单据编号"+"不能为空!");
         document.all.item("code").focus();
         return false;
     }
          if(''==document.all.item("name").value){
         alert("单据名称"+"不能为空!");
         document.all.item("name").focus();
         return false;
     }
          if(''==document.all.item("mark").value){
         alert("单据标识"+"不能为空!");
         document.all.item("mark").focus();
         return false;
     }
          if(''==document.all.item("numlength").value){
         alert("流水号长度"+"不能为空!");
         document.all.item("numlength").focus();
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
  function ret(){
      var form=document.forms[0];
      if(form.method.value=='addSave'){
         form.method.value='list';
      }else{
        form.method.value='view';
      }
      form.submit();
  }
  
  
</script>
<body style="margin: 10px;">
	<html:form action="/tsysbillnorule.do" method="post">
		<html:hidden property="method" />
		<html:hidden property="moduleTitle" />
		<html:hidden property="tsysbillnoruleid" />
		<table border="0" cellspacing="0" cellpadding="0" class="tableRegion"
			width="100%">
			<tr class="titleRow">
				<td align="right"><ucard:Button value="保存" icon="icon-save"
						hotkey='F10,121' onclick="javascript:save()" /> <ucard:Button
						value="返回" icon="icon-edit" hotkey='ESC,27'
						onclick="javascript:ret()" /></td>
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
					<td width="20%" class="label"><font color='red'>*</font>单据编号</td>
					<td width="30%" class="input"><html:text property="code"
							maxlength="50" onblur="" onkeydown="nextFocus('name')" size="20"
							styleClass="field" /></td>

					<td width="20%" class="label"><font color='red'>*</font>名称</td>
					<td width="30%" class="input"><html:text property="name"
							maxlength="50" onblur="" onkeydown="nextFocus('mark')" size="20"
							styleClass="field" /></td>

				</tr>
				<tr>
					<td width="20%" class="label"><font color='red'>*</font>单据标识</td>
					<td width="30%" class="input"><html:text property="mark"
							maxlength="6" onblur="" onkeydown="nextFocus('billtype')"
							size="20" styleClass="field" /></td>
					<td width="20%" class="label"><font color='red'>*</font>构成方式</td>
					<td width="30%" class="input"><html:select property="billtype">
							<html:optionsCollection name="org.apache.struts.taglib.html.BEAN"
								property="billtypeOptions" value="ivalue" />
						</html:select></td>

				</tr>
				<tr>
					<td width="20%" class="label"><font color='red'>*</font>流水号长度
					</td>
					<td width="30%" class="input"><html:text property="numlength"
							maxlength="20" onblur="" onkeydown="nextFocus('counttype')"
							size="20" styleClass="field" /></td>
					<td width="20%" class="label"><font color='red'>*</font>重新计数方式
					</td>
					<td width="30%" class="input"><html:select
							property="counttype">
							<html:optionsCollection name="org.apache.struts.taglib.html.BEAN"
								property="counttypeOptions" value="ivalue" />
						</html:select></td>

				</tr>
				<tr>
					<td width="20%" class="label">当前计数</td>
					<td width="30%" class="input"><html:text property="curcount"
							maxlength="20" size="20" styleClass="field" /></td>
					<td width="20%" class="label">计数日期</td>
					<td width="30%" class="input"><html:text property="editdate"
							button="date" onblur="checkDate(this,true)"
							onkeydown="nextFocus('oldno')" size="20" styleClass="field" /></td>
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
