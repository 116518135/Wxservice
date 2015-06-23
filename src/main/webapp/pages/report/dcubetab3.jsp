<%@ page contentType="text/html;charset=UTF-8"%>
<div class="eXtremeTable">
	<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
		class="tableRegion" width="100%">
		<tr>
			<td width="45%" class="label">
				<div style="width: 100%; height: 100%; text-align: right">
					<div>基础数据区</div>
					<div>
						<html:select property="totalband" size="20" multiple="multiple"
							style="width:150px;font-size:15px" ondblclick="selectMovesub('totalband');">
							<html:optionsCollection name="struts_form" property="subbandlist" />
						</html:select>
					</div>
				</div></td>
			<td width="55%" class="label" colspan="2">
				<table border="0" cellspacing="0" cellpadding="0" width="100%"
					height="100%">
					<tr>
						<td width="20%" class="label" height="100%">
							<div
								style="width: 100%; height: 100%; text-align: center; margin-top: 50%">
								<input type="button" value="=》"
									onclick="selectMovesubAll('totalband');"> <br>
								<input type="button" value="=〉"
									onclick="selectMovesub('totalband');"> <br>
								<input type="button" value="《="
									onclick="subRemoveAll();">

							</div></td>
						<td width="80%" class="label">
							<div style="text-align: left; width: 100%; height: 100%">
								<div>合计区</div>
								<div>
									<html:select property="subband" size="20" multiple="multiple"
										style="width:150px;font-size:15px" ondblclick="subRemove();">
										<html:optionsCollection name="struts_form"
											property="subbandlist" />
									</html:select>
								</div>
							</div></td>
					</tr>
				</table></td>
		</tr>
	</table>