<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<html>
<head></head>
<script language="javascript">
	function update() {
		var form = document.forms[0];
		form.method.value = 'update';
		form.submit();
	}
	function del() {
		if (confirm('确认删除资料？')) {
			var form = document.forms[0];
			form.method.value = 'delete';
			form.submit();
		}
	}
	function ret() {
		var form = document.forms[0];
		form.method.value = 'list';
		form.submit();
	}
	function routemap() {
		var code = document.all.item("code").value;
		var action = "/checkshop/chart.do";
		var url = action + '?method=routemap&txdtaskid=-1&usercode=' + code;
		OpenFindWindow(url, 520, 500);
	}
	function allrecordchart() {
		var code = document.all.item("code").value;
		var action = "/checkshop/chart.do";
		var url = action + '?method=allrecordchart&usercode=' + code;
		OpenFindWindow(url, 520, 500);
	}
</script>
<body style="margin: 10px;">
	<html:form action="/treguser.do" method="post">
		<html:hidden property="method" />
		<html:hidden property="treguserid" />
		<html:hidden property="ec_p" />
		<html:hidden property="ec_crd" />
		<html:hidden property="findwhere" />
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr class="titleRow">
				<td align="right"><ucard:Button
						onclick="javascript:allrecordchart()" icon="icon-edit"
						value="个人巡店报表" />&nbsp; <ucard:Button
						onclick="javascript:routemap()" icon="icon-edit" value="巡店轨迹" />&nbsp;
					<ucard:Button invoke="isEdit" onclick="javascript:update()"
						icon="icon-edit" hotkey="F4,115" value="修改" />&nbsp; <ucard:Button
						invoke="isEdit" onclick="javascript:del()" icon="icon-delete"
						hotkey="F5,116" value="删除" />&nbsp; <ucard:Button value="返回"
						icon="icon-cancel" hotkey="ESC,27" onclick="javascript:ret()" />
				</td>
			</tr>

		</table>

		<div class="eXtremeTable">
			<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
				class="tableRegion" width="100%">
				<tr class="titleRow">
					<td colspan="4"><span><bean:write
								name="org.apache.struts.taglib.html.BEAN" property="moduleTitle" />
					</span></td>
				</tr>
				<tr>
					<td width="20%" class="label">帐号</td>
					<td width="30%" class="input"><html:text property="code"
							disabled="true" size="20" styleClass="field" />
					</td>
					<td width="20%" class="label">姓名</td>
					<td width="30%" class="input"><html:text property="name"
							disabled="true" size="20" styleClass="field" />
					</td>

				</tr>
				<tr>
					<td width="20%" class="label">email</td>
					<td width="30%" class="input"><html:text property="email"
							disabled="true" size="20" styleClass="field" />
					</td>
					<td width="20%" class="label">手机</td>
					<td width="30%" class="input"><html:text
							property="mobilephone" disabled="true" size="20"
							styleClass="field" />
					</td>

				</tr>
				<tr>
					<td width="20%" class="label">停用标志</td>
					<td width="30%" class="input"><html:checkbox
							property="stopflag" disabled="true" value="1" />
					</td>
					<td width="20%" class="label">备注</td>
					<td width="30%" class="input"><html:text property="memo"
							disabled="true" size="20" styleClass="field" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="label">密码</td>
					<td width="30%" class="input"><html:password property="passwd"
							disabled="true" size="20" styleClass="field" />
					</td>

					<td width="20%" class="label">职务</td>
					<td width="30%" class="input"><html:hidden property="roleids" />
						<html:text property="rolenames" size="20" disabled="true"
							styleClass="field" /></td>
				</tr>
			</table>
		</div>

	</html:form>
</body>
</html>
