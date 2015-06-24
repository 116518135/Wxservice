<%@ page contentType="text/html;charset=UTF-8"%>
<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
	class="tableRegion" width="100%">


	<tr>
		<td width="30%" class="label">店铺的附近距离定义(千米)</td>
		<td width="70%" class="input"><html:text
				property="cmp_neardistance" maxlength="5" name="form"
				onblur="isInt()" size="5" styleClass="field" />
		</td>
	</tr>
	<tr>
		<td width="30%" class="label"><font color='red'>*</font>任务单编号</td>
		<td width="70%" class="input"><html:hidden property="txdtaskid" />
			<html:text property="taskcode" maxlength="20" size="20"
				styleClass="field" name="form" />
			<button class="selectButton" type="button"
				onclick="javascript:selectTask()" /> <img
			src="${ctx}/images/select.gif" width="12" />
			</button></td>

	</tr>
	<tr>
		<td width="30%" class="label">巡店结果合格率定义(如:60)</td>
		<td width="70%" class="input"><html:text
				property="ifpass" maxlength="5" name="form"
				onblur="isInt()" size="5" styleClass="field" />
		</td>
	</tr>
	
</table>


