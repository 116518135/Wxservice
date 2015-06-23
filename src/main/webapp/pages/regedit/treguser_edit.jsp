<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<html>
<head></head>
<script language="javascript">
	function check() {
		var form = document.forms[0];

		if ('' == document.all.item("code").value) {
			alert("帐号" + "不能为空!");
			document.all.item("code").focus();
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
	function ret() {
		var form = document.forms[0];
		if (form.method.value == 'addSave') {
			form.method.value = 'list';
		} else {
			form.method.value = 'view';
		}
		form.submit();
	}
</script>
<body style="margin: 10px;">
	<html:form action="/treguser.do" method="post">
		<html:hidden property="method" />
		<html:hidden property="moduleTitle" />
		<html:hidden property="treguserid" />
		<table border="0" cellspacing="0" cellpadding="0" class="tableRegion"
			width="100%">
			<tr class="titleRow">
				<td align="right"><ucard:Button value="保存" icon="icon-save"
						hotkey='F10,121' onclick="javascript:save()" /> <ucard:Button
						value="返回" icon="icon-back" hotkey='ESC,27'
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
					<td width="20%" class="label"><font color='red'>*</font>帐号</td>
					<td width="30%" class="input"><html:text property="code"
							maxlength="50" onblur="" onkeydown="nextFocus('passwd')"
							size="20" styleClass="field" /></td>
					<td width="20%" class="label">姓名</td>
					<td width="30%" class="input"><html:text property="name"
							maxlength="50" onblur="" onkeydown="nextFocus('email')" size="20"
							styleClass="field" /></td>

				</tr>
				<tr>
					<td width="20%" class="label">email</td>
					<td width="30%" class="input"><html:text property="email"
							maxlength="50" onkeydown="nextFocus('mobilephone')" size="20"
							styleClass="field" /></td>
					<td width="20%" class="label">手机</td>
					<td width="30%" class="input"><html:text
							property="mobilephone" maxlength="20"
							onkeydown="nextFocus('qqflag')" size="20" styleClass="field" />
					</td>

				</tr>
				<tr>
					<td width="20%" class="label">停用标志</td>
					<td width="30%" class="input"><html:checkbox
							property="stopflag" value="1" /></td>
					<td width="20%" class="label">备注</td>
					<td width="30%" class="input"><html:text property="memo"
							maxlength="50" size="20" styleClass="field" /></td>
				</tr>
				<tr>
					<td width="20%" class="label"><html:checkbox
							property="passwdflag" value="1" />密码</td>
					<td width="30%" class="input"><html:password property="passwd"
							maxlength="50" onblur="" size="20" styleClass="field" /></td>
					<td width="20%" class="label">职务</td>
					<td width="30%" class="input"><input type="hidden"
						name="roleids" /> <input type="text" name="rolenames" size="20"
						readonly="true" class="field" />
						<button class="selectButton" type="button"
							onclick="selectSubMulti()" />
						<img src="${ctx}/images/select.gif" width="12" />
					</button> <script language='javascript'>
						function selectSubMulti() {
							var url = '${ctx}/select.do?forward=main&action=selectAppUserTableMulti';
							var callWindow = winOpen(url, 600, 550, '_blank');
							callWindow.multivalue = document.all
									.item("roleids");
							callWindow.multiname = document.all
									.item("rolenames");
							callWindow.focus();
						}
					</script></td>


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
