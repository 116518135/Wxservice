<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ include file="/include/ext2js.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/widgets/ext2/resources/css/xtheme-gray.css?v=22" />
<html>
<head>
<title>${webName}</title>
  <script src="<c:url value="/scripts/dcube2.js?v=13"/>" type="text/javascript"></script>
<!-- EXAMPLES -->
<SCRIPT LANGUAGE="JavaScript">
Ext.onReady(function(){
    var tabs = new Ext.TabPanel({
          renderTo: 'tabs1',
          activeTab: 0,
          autoWidth:true,
          height:window.screen.availHeight-100,
          defaults:{autoScroll: true},
          items:[
            {contentEl:'dcubetable', title:'${struts_form.moduleTitle}'}
          ]
 });

 
var Dcube2 = document.getElementById("Dcube2");

Dcube2.GridColor=0;
Dcube2.BackColor=15319718;

Dcube2.FieldsForeColor= 0;
Dcube2.FieldsBackColor= 15319718;	

Dcube2.HeadingsBackColor=0;
Dcube2.HeadingsForeColor=156;	
			
Dcube2.FooterBackColor=0;
Dcube2.FooterForeColor=255;
Dcube2.HeaderBackColor=0;
Dcube2.HeaderForeColor=255;
Dcube2.FilteredFieldBackColor=3316172;

var innerHtml="";
if(Dcube2.Fields==undefined){
   innerHtml="<span style='FONT-SIZE: 10pt;color: blue;'>&nbsp;&nbsp;未注册维度控件!</span>";
}else{
  if(datas.length>0){
     if(isSetDimValue()){
       show();
     }else{
       innerHtml="<span style='FONT-SIZE: 10pt;color: blue;'>&nbsp;&nbsp;报表未设置度量字段!</span>";
     }
  }else{
       innerHtml="<span style='FONT-SIZE: 10pt;color: blue;'>&nbsp;&nbsp;此次查询无数据!</span>";
  }
}

if(innerHtml!=''){
    var dcubetable = document.all.item("dcubetable"); 
    dcubetable.innerHTML=innerHtml;
}
})
	//首次启动时
	function reg() {
		DCube2.style.visibility = "visible";
		var thisField;
		DCube2.DCConnectType = DCCT_UNBOUND;
		DCube2.Fields.DeleteAll();
		for ( var i = 0; i < header.length; i++) {
			var dd = header[i];
			if(dd.dimvalueflag == 3){
				alldata+=dd.field;
				alldata+=',';
			}
		}
		alldata = alldata.substring(0, alldata.length-1);
		var footerVisible = true;
		for ( var i = 0; i < header.length; i++) {
			var dd = header[i];
			if (dd.dimvalueflag == 3) {//数据
				if(dd.field.indexOf('zb')!=-1){
					thisField = DCube2.Fields.Add(dd.field, dd.title, DCData);
					thisField.NumberFormat="##0.00%"; 
				}else{
					thisField = DCube2.Fields.Add(dd.field, dd.title, DCData);
					if(dd.field.indexOf('amt')!=-1||dd.field.indexOf('price')!=-1){
						thisField.NumberFormat="##0.00"; 
					}else{
						thisField.NumberFormat="##0"; 
					}
				}
			} else {
				thisField = DCube2.Fields.Add(dd.field, dd.title, DCRow);
				thisField.GroupFooterCaption = dd.title + "合计:";
				if((dd.dimvalueflag == 2||dd.dimvalueflag == 0)&&footerVisible){
					thisField.GroupFooterVisible = true;
					footerVisible = false;
				}else{
					thisField.GroupFooterVisible = false;
				}
			}
		}
		document.all.item("showsubflag").value='true';
		DCube2.RefreshData();
		DCube2.AutoDataRefresh = true;
	}
function startRefresh() {
	// 重新刷新,默认从数据库取
	var colstr = '';
	var pagestr = '';
	var rowstr = '';
	for ( var i = 0; i < header.length; i++) {
		var dd = header[i];
		if(dd.dimvalueflag == 1){
			colstr+=dd.field;
			colstr+=',';
		}
		if(dd.dimvalueflag == 2||dd.dimvalueflag == 0){
			if(dd.field.indexOf("field_")!=-1){
				if (dd.title == '大类' || dd.title == '季节') {
					rowstr += dd.field;
					rowstr += ',';
				} else {
					pagestr += dd.field;
					pagestr += ',';
				}
			}else{
				rowstr+=dd.field;
				rowstr+=',';
			}
		}
		if(dd.dimvalueflag == 4){
			pagestr+=dd.field;
			pagestr+=',';
		}

	}
	rowstr = rowstr.substring(0, rowstr.length - 1);
	pagestr = pagestr.substring(0, pagestr.length - 1);
	colstr = colstr.substring(0, colstr.length - 1);
	resetFields(rowstr, DCRow);
	resetFields(colstr, DCColumn);
	resetFields(pagestr, DCPage);
	// 隐藏
	// 先全部显示
	showAll();
	DCube2.Refresh();
}
</SCRIPT>
<script for="DCube2" event="Pivot">
	for ( var i = 0; i < DCube2.RowFields.count(); i++) {
		//  alert(DCube2.RowFields.item(i).Name);
	}
</script>
<script for="DCube2" event="FetchData">
	for ( var i = 0; i < datas.length; i++) {
		//var arrValue = new Array();
		//for ( var j = 0; j < datas[i].length; j++) {
		//	arrValue[j] = datas[i][j];
		//}
		DCube2.AddRowEx(GetVBArray(datas[i]));
	}
</script>
<script language="vbscript">
			Function GetVBArray(arrValue)
			   Dim Temp
				Dim vbArray
				Temp=arrValue.join("&#^&")
				vbArray=Split(Temp,"&#^&")
				GetVBArray = vbArray
			End Function
			
		</script>
<script language="javascript">
   var header=<%=request.getAttribute("header_key")%>;
	var datas =
<%=request.getAttribute("datas_key")%>
	;
</script>
</head>
<body style="margin: 10px;" id="docs">
	<html:form action="/report.do" method="post">
		<input type="hidden" name="method" value="cacheHtml" />
		<html:hidden property="reportid" />
		<html:hidden property="moduleTitle" />
		<html:hidden property="name" />
		<html:hidden property="showsubflag" />
		<html:hidden property="trpdcubetplid" />
		<input type="hidden" id="filesave">
		<input type="hidden" name="colbands">
		<input type="hidden" name="rowbands">
		<input type="hidden" name="pagebands">
		<input type="hidden" name="databands">
		<input type="hidden" name="subbands">
		<input type="hidden" name="hiddenbands">
		<input type="hidden" name="allbands" onpropertychange="reFresh()">
		<input type="file" id="fileselect" style="display: none">
		<table border="0" cellspacing="0" cellpadding="0" class="tableRegion"
			width="100%">
			<tr class="titleRow">
				<td align="right"><ucard:Button
						onclick="javascript:setTemplate()" icon='icon-edit' value="设置模板" />
					<ucard:Button invoke="" onclick="javascript:saveXsl()"
						icon='icon-excel' hotkey="F3,114" value="下载" /> <ucard:Button
						onclick="javascript:loadSelect()" icon='icon-add-row' value="加载格式" />
				</td>
			</tr>
		</table>
	</html:form>
	<div id="tabs1">
		<div id="dcubetable" class="x-hide-display"></div>
		<OBJECT id="DCube2"
			style="position: absolute; float: left; WIDTH: 100%; HEIGHT: 100%; VISIBILITY: hidden"
			codeBase="${ctx}/dcube/Dcube3.Cab#version=3.2.1.1177"
			classid="clsid:6D63F73D-3688-3000-9C0F-00A0C90F29FC" name="DCube2">
		</OBJECT>
	</div>
</body>
</html>
