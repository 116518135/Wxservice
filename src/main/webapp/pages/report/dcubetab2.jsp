<%@ page contentType="text/html;charset=UTF-8"%>
<div class="eXtremeTable">
	<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
		class="tableRegion" width="100%">
		<tr>
			<td width="45%" class="label">
				<div style="width: 100%; height: 100%; text-align: right">
					<div>数据区</div>
					<div>
						<html:select property="databand" size="20" multiple="multiple"
							style="width:150px;font-size:15px"
							ondblclick="selectMove('databand','hiddenband');">
							<html:optionsCollection name="struts_form"
								property="databandlist" />
						</html:select>
					</div>
				</div>
			</td>
			<td width="55%" class="label" colspan="2">
				<table border="0" cellspacing="0" cellpadding="0" width="100%"
					height="100%">
					<tr>
						<td width="20%" class="label" height="100%">
							<div
								style="width: 100%; height: 100%; text-align: center; margin-top: 50%">
								<input type="button" value="=》"
									onclick="selectMoveAll('databand','hiddenband');"> <br>
								<input type="button" value="=〉"
									onclick="selectMove('databand','hiddenband');"> <br> <input
									type="button" value="〈="
									onclick="selectMove('hiddenband','databand');"><br> <input
									type="button" value="《="
									onclick="selectMoveAll('hiddenband','databand');">

							</div>
						</td>
						<td width="80%" class="label">
							<div style="text-align: left; width: 100%; height: 100%">
								<div>隐藏区</div>
								<div>
									<html:select property="hiddenband" size="20" multiple="multiple"
										style="width:150px;font-size:15px"
										ondblclick="selectMove('hiddenband','databand');">
										<html:optionsCollection name="struts_form"
											property="hiddenbandlist" />
									</html:select>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>