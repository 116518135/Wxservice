<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<html>
<head></head>
<script language="javascript">
  function update(){
     var form=document.forms[0];
     form.method.value='update';
     form.submit();
  }
  function del(){
     if(confirm('确认删除资料？')){
        var form=document.forms[0];
        form.method.value='delete';
        form.submit();
     }
  }
  function ret(){
     var form=document.forms[0];
     form.method.value='list';
     form.submit();
  }
</script>
<body style="margin: 10px;">
<html:form action="/tsysbillnorule.do" method="post">
	<html:hidden property="method" />
	<html:hidden property="tsysbillnoruleid" />
	<html:hidden property="ec_p" />
	<html:hidden property="ec_crd" />
	<html:hidden property="findwhere" />
	<table border="0" cellspacing="0" cellpadding="0" width="100%">
		<tr class="titleRow">
			<td align="right"><ucard:Button invoke="isEdit"
				onclick="javascript:update()" icon="icon-edit" hotkey="F4,115"
				value="修改" />&nbsp; <ucard:Button invoke="isEdit"
				onclick="javascript:del()" icon="icon-delete" hotkey="F5,116"
				value="删除" />&nbsp; <ucard:Button value="返回" icon="icon-cancel"
				hotkey="ESC,27" onclick="javascript:ret()" /></td>
		</tr>

	</table>

	<div class="eXtremeTable">
	<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
		class="tableRegion" width="100%">
		<thead>
			<tr class="titleRow">
				<td colspan="4"><span><bean:write
					name="org.apache.struts.taglib.html.BEAN" property="moduleTitle" /></span></td>
			</tr>
			<tr>
				<td width="20%" class="label">单据编号</td>
					<td width="30%" class="input"><html:text property="code"
					disabled="true" size="20" styleClass="field" /></td>
				<td width="20%" class="label">名称</td>
				<td width="30%" class="input"><html:text property="name"
					disabled="true" size="20" styleClass="field" /></td>


			</tr>
			<tr>
				<td width="20%" class="label">单据标识</td>
				<td width="30%" class="input"><html:text property="mark"
					disabled="true" size="20" styleClass="field" /></td>
				<td width="20%" class="label">构成方式</td>
				<td width="30%" class="input"><html:select property="billtype"
					disabled="true">
					<html:optionsCollection name="org.apache.struts.taglib.html.BEAN"
						property="billtypeOptions" value="ivalue" />
				</html:select></td>


			</tr>
			<tr>
				<td width="20%" class="label">流水号长度</td>
				<td width="30%" class="input"><html:text property="numlength"
					disabled="true" size="20" styleClass="field" /></td>
				<td width="20%" class="label">重新计数方式</td>
				<td width="30%" class="input"><html:select property="counttype"
					disabled="true">
					<html:optionsCollection name="org.apache.struts.taglib.html.BEAN"
						property="counttypeOptions" value="ivalue" />
				</html:select></td>


			</tr>
			<tr>
				<td width="20%" class="label">当前计数</td>
				<td width="30%" class="input"><html:text property="curcount"
					disabled="true" size="20" styleClass="field" /></td>
				<td width="20%" class="label">计数日期</td>
				<td width="30%" class="input"><html:text property="editdate"
					disabled="true" size="20" styleClass="field" /></td>
			</tr>
	</table>
	</div>

</html:form>
</body>
</html>
