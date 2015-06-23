<%@ page contentType="text/html;charset=UTF-8"%>
<div class="eXtremeTable">
	<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
		class="tableRegion" width="100%">
		<tr>
			<td width="45%" class="label">
				<div style="width: 100%; height: 100%; text-align: right">
					<div style="text-align: right">行区</div>
					<div>
						<html:select property="rowband" size="20" multiple="multiple"
							style="width:150px;font-size:15px"
							ondblclick="selectMove('rowband','colband');">
							<html:optionsCollection name="struts_form" property="rowbandlist" />
						</html:select>
					</div>
				</div></td>
			<td width="55%" class="label" colspan="2">
				<table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
					<tr>
						<td width="20%" class="label" height="100%">
							<div style="width: 100%; height: 100%; text-align: center;margin-top: 50%">
								<input type="button" value="=》"
									onclick="selectMoveAll('rowband','colband');"> <br> <input
									type="button" value="=〉"
									onclick="selectMove('rowband','colband');"> <br> <input
									type="button" value="〈="
									onclick="selectMove('colband','rowband');"><br> <input
									type="button" value="《="
									onclick="selectMoveAll('colband','rowband');">

							</div></td>
						<td width="80%" class="label">
							<div style="text-align: left; width: 100%; height: 100%">
									<div>列区</div>
									<div>
										<html:select property="colband" size="10" multiple="multiple"
											style="width:150px;font-size:15px"
											ondblclick="selectMove('colband','rowband');">
											<html:optionsCollection name="struts_form"
												property="colbandlist" />
										</html:select>
									</div>
							</div></td>
					</tr>
					<tr>
						<td width="20%" class="label">
							<div style="width: 100%; height: 100%; text-align: center;margin-top: 50%">
								<input type="button" value="=》"
									onclick="selectMoveAll('rowband','pageband');">
								<input type="button" value="=〉"
									onclick="selectMove('rowband','pageband');"> <br>
								<input type="button" value="〈="
									onclick="selectMove('pageband','rowband');"><br> <input
									type="button" value="《="
									onclick="selectMoveAll('pageband','rowband');">
							</div></td>
						<td width="80%" class="label">
							<div style="text-align: left; width: 100%; height: 100%">
								<div style="width: 50%; height: 50%">
									<div>页头区</div>
									<div>
										<html:select property="pageband" size="9" multiple="multiple"
											style="width:150px;font-size:15px"
											ondblclick="selectMove('pageband','rowband');">
											<html:optionsCollection name="struts_form"
												property="pagebandlist" />
										</html:select>
									</div>
								</div>
							</div></td>
					</tr>
				</table></td>
		</tr>
	</table>