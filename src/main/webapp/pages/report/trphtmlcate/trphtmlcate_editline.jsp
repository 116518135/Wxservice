<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<html>
<head></head>
<script language="javascript">
	function check() {
		var form = document.forms[0];

		if ('' == document.all.item("name").value) {
			alert("名称" + "不能为空!");
			document.all.item("name").focus();
			return false;
		}
		if ('' == document.all.item("jsname").value) {
			alert("JS名称" + "不能为空!");
			document.all.item("jsname").focus();
			return false;
		}
		if ('' == document.all.item("processclass").value) {
			alert("处理类" + "不能为空!");
			document.all.item("processclass").focus();
			return false;
		}
		return true;
	}
	function save() {
		var form = document.forms[0];
		if (check()) {
			form.submit();
		}
	}

	function saveAdd() {
		var form = document.forms[0];
		if (check()) {
			form.method.value = "saveAdd"
			form.submit();
		}
	}
</script>
<body style="margin: 10px;">
	<html:form action="/trphtmlcate.do" token="TOKEN_LINE" method="post">
		<html:hidden property="method" />
		<html:hidden property="moduleTitle" />
		<html:hidden property="trphtmlctrlid" />
		<html:hidden property="trphtmlcateid" />
		<table border="0" cellspacing="0" cellpadding="0" class="tableRegion"
			width="100%">
			<tr class="titleRow">
				<td align="right"><mytag:Button value="保存" icon="icon-save"
						hotkey='F10,121' onclick="javascript:save()" /> <c:if
						test="${methodflag=='addSave'}">
						<mytag:Button value="继续增加" icon="icon-save" hotkey='F11,122'
							onclick="javascript:saveAdd()" />
					</c:if> <mytag:Button value=" 关闭 " icon="icon-edit" hotkey='ESC,27'
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
					<td width="20%" class="label"><font color='red'>*</font>名称</td>
					<td width="30%" class="input"><html:text property="name"
							maxlength="30" onblur="" onkeydown="nextFocus('jsname')"
							size="20" styleClass="field" /></td>
					<td width="20%" class="label"><font color='red'>*</font>JS名称</td>
					<td width="30%" class="input"><html:text property="jsname"
							maxlength="50" onblur="" onkeydown="nextFocus('wheres')"
							size="20" styleClass="field" /></td>

				</tr>
				<tr>
					<td width="20%" class="label">where语句</td>
					<td width="30%" class="input" colspan=3><html:text
							property="wheres" style="width:412.5px" maxlength="512" onblur=""
							onkeydown="nextFocus('content')" size="20" styleClass="field" />
					</td>


				</tr>
				<tr>
					<td width="20%" class="label"><font color='red'>*</font>处理类</td>
					<td width="30%" class="input" colspan=3><html:select
							property="processclass" style="width:180px">
								<<html:option value="">&nbsp;</html:option>
							<html:optionsCollection name="org.apache.struts.taglib.html.BEAN"
								property="processclasslist" value="value" />
						</html:select></td>

				</tr>
				<tr>
					<td width="20%" class="label"><font color='red'>*</font>内容</td>
					<td width="30%" class="input" colspan="3"><html:textarea
							property="content" cols="78" rows="15" styleClass="field" /></td>
				</tr>

			</table>
		</div>
	</html:form>
</body>
<script language="javascript">
	var element = getFirstElement();
	if (element != null) {
		element.focus();
	}
</script>
</html>
